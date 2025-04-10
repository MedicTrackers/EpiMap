package data.controller.notice;

import data.dto.NoticeDto;
import data.dto.UsersDto;
import data.service.NoticeService;
import data.service.UsersService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import naver.storage.NcpObjectStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    @Autowired
    NcpObjectStorageService storageService;

    private String bucketName="epimap";
    private String imagePath= "https://kr.object.ncloudstorage.com/epimap/notice/";
    private String filePath="https://kr.object.ncloudstorage.com/epimap/notice/";
    @Autowired
    private UsersService usersService;

    @PostMapping("/notice/insert")
    public String insertNotice(
            @ModelAttribute NoticeDto noticeDto,
            @RequestParam("imageUpload") List<MultipartFile> imageUpload,  // 이미지 파일 여러 개
            @RequestParam("fileUpload") MultipartFile fileUpload,          // 일반 파일
            HttpSession session
    ) {
        // 로그인 여부 체크
        UsersDto dto = (UsersDto) session.getAttribute("loginUser");
        if (dto == null) {
            return "redirect:/login";
        }

        int users_id = dto.getUsers_id();
        noticeDto.setUsers_id(users_id);

        List<String> uploadImagePaths = new ArrayList<>();

        // 이미지 파일들 업로드
        if (imageUpload != null && !imageUpload.isEmpty()) {
            for (MultipartFile file : imageUpload) {
                if (!file.isEmpty()) {
                    String nphoto = storageService.uploadFile(bucketName, "notice", file);
                    uploadImagePaths.add(nphoto);
                }
            }
        }

        // 업로드된 이미지 경로들을 콤마로 이어붙임
        if (!uploadImagePaths.isEmpty()) {
            String joinedPaths = String.join(",", uploadImagePaths);  // "url1,url2,url3"
            noticeDto.setNphoto(joinedPaths);
        } else {
            noticeDto.setNphoto(null);
        }

        // 일반 파일 업로드
        if (fileUpload != null && !fileUpload.isEmpty()) {
            String nfile = storageService.uploadFile(bucketName, "notice", fileUpload);
            noticeDto.setNfile(nfile);
        } else {
            noticeDto.setNfile(null);
        }

        // 공지사항 저장
        noticeService.insertNotice(noticeDto);
        return "redirect:/notice";
    }
    @GetMapping("/notice/updateform")
    public String updateForm(@RequestParam("boards_id") int boards_id,
                             @RequestParam("pageNum") int pageNum,
                             Model model)
    {
        NoticeDto noticeDto=noticeService.getSelectById(boards_id);
        model.addAttribute("noticeDto", noticeDto);
        System.out.println("noticeDto: " + noticeDto);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("imagePath", imagePath);
        model.addAttribute("filePath", filePath);

        return "page4/updateform";
    }



    @PostMapping("/notice/update")
    public String update(
            @ModelAttribute NoticeDto dto,
            @RequestParam int pageNum,
            @RequestParam("imageUpload") List<MultipartFile> imageUpload,  // 이미지 여러 개
            @RequestParam("fileUpload") MultipartFile fileUpload,          // 일반 파일
            @RequestParam(value = "boards_id", required = false, defaultValue = "0") int boards_id
        )
    {
        // 이미지 업로드 처리
        List<String> uploadImagePaths = new ArrayList<>();
        if (imageUpload != null && !imageUpload.isEmpty()) {
            for (MultipartFile file : imageUpload) {
                if (!file.isEmpty()) {
                    String imageUrl = storageService.uploadFile(bucketName, "notice", file);
                    uploadImagePaths.add(imageUrl);
                }
            }
            dto.setNphoto(String.join(",", uploadImagePaths));
        } else {
            dto.setNphoto(null);  // 이미지 없으면 null 처리
        }

        // 일반 파일 업로드 처리
        if (fileUpload != null && !fileUpload.isEmpty()) {
            String fileUrl = storageService.uploadFile(bucketName, "notice", fileUpload);
            dto.setNfile(fileUrl);
        } else {
            dto.setNfile(null);  // 파일 없으면 null 처리
        }

        // 업데이트 실행
        noticeService.updateNotice(dto);

        return "redirect:/notice?pageNum=" + pageNum;
    }


    @GetMapping("notice/delete")
    public String delete(@RequestParam("boards_id") int boards_id)
    {
        //저장된 사진 지우기
        String nPhoto=noticeService.getSelectById(boards_id).getNphoto();
        String nfile=noticeService.getSelectById(boards_id).getNfile();
        storageService.deleteFile(bucketName,"notice",nPhoto);
        storageService.deleteFile(bucketName,"notice",nfile);
        noticeService.deleteNotice(boards_id);

        return "redirect:/notice";
    }

    @GetMapping("/notice/detail")
    public String detail(@RequestParam ("boards_id") int boards_id,
                         @RequestParam (value = "pageNum", defaultValue = "1") int pageNum, Model model,
                         HttpSession session)
    {
        UsersDto loginUser=(UsersDto) session.getAttribute("loginUser");
        if (loginUser != null)
        {
            int users_id = loginUser.getUsers_id();

            // 읽은 기록 있는지 확인
            Map<String, Object> map = new HashMap<>();
            map.put("users_id", users_id);
            map.put("boards_id", boards_id);

            if (noticeService.isBoardRead(map) == 0) {
                noticeService.insertBoardRead(map);
            }
        }

        NoticeDto dto= noticeService.getSelectById(boards_id);

        // ✅ 이미지 리스트로 분리해서 dto에 세팅
        if (dto.getNphoto() != null && !dto.getNphoto().isEmpty()) {
            List<String> photoList = Arrays.asList(dto.getNphoto().split(","));
            dto.setNphotoList(photoList);
        }

        model.addAttribute("dto", dto);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("imagePath", imagePath);
        model.addAttribute("filePath", filePath);
        model.addAttribute("prev",noticeService.getPrevPost(boards_id));
        model.addAttribute("next", noticeService.getNextPost(boards_id));
        return"page4/noticedetail";

    }

}
