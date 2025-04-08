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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
            @RequestParam("imageUpload") MultipartFile imageUpload,  // 이미지 파일 처리
            @RequestParam("fileUpload") MultipartFile fileUpload,    // 일반 파일 처리
            HttpSession session
    )
    {
        // 로그인 여부 체크
        UsersDto dto = (UsersDto) session.getAttribute("loginUser");
        if (dto == null) {
            return "redirect:/login";  // 로그인되지 않았다면 로그인 페이지로 리다이렉트
        }
        int users_id = dto.getUsers_id();
         // 세션에서 사용자 ID 가져오기

        noticeDto.setUsers_id(users_id);

        // 이미지 파일이 비어있지 않으면 업로드
        if (imageUpload != null && !imageUpload.isEmpty()) {
            String nphoto = storageService.uploadFile(bucketName, "notice", imageUpload);
            noticeDto.setNphoto(nphoto);  // 이미지 파일 URL을 DTO에 설정
            System.out.println("이미지 업로드 실행됨: " + imageUpload.getOriginalFilename());
        } else {
            noticeDto.setNphoto(null);  // 이미지 파일이 없으면 null 처리
        }

        // 일반 파일이 비어있지 않으면 업로드
        if (fileUpload != null && !fileUpload.isEmpty()) {
            String nfile = storageService.uploadFile(bucketName, "notice", fileUpload);
            noticeDto.setNfile(nfile);  // 일반 파일 URL을 DTO에 설정
        } else {
            noticeDto.setNfile(null);  // 파일이 없으면 null 처리
        }

        // 공지사항 데이터 삽입
        noticeService.insertNotice(noticeDto);
        // 공지사항을 저장하고 리다이렉트
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
            @RequestParam("imageUpload") MultipartFile imageUpload,  // 이미지 파일 처리
            @RequestParam("fileUpload") MultipartFile fileUpload,     // 일반 파일 처리
            @RequestParam(value = "boards_id", required = false, defaultValue = "0") int boards_id

    ) {
        // 기존 공지사항 업데이트
        noticeService.updateNotice(dto);

        // 이미지 파일이 업로드된 경우
        if (imageUpload != null && !imageUpload.isEmpty()) {
            // 네이버 클라우드 스토리지에 이미지 파일 업로드
            String imageFilename = storageService.uploadFile(bucketName, "notice", imageUpload);
            // 이미지 파일 경로를 DTO에 설정
            dto.setNphoto(imageFilename);
        } else {
            // 이미지 파일이 없으면 null 처리 (이미지 제거)
            dto.setNphoto(null);
        }

        // 일반 파일이 업로드된 경우
        if (fileUpload != null && !fileUpload.isEmpty()) {
            // 네이버 클라우드 스토리지에 일반 파일 업로드
            String fileFilename = storageService.uploadFile(bucketName, "notice", fileUpload);
            // 일반 파일 경로를 DTO에 설정
            dto.setNfile(fileFilename);
        } else {
            // 일반 파일이 없으면 null 처리 (파일 제거)
            dto.setNfile(null);
        }

        // 파일 정보 업데이트 (이미지와 파일 경로 업데이트)
        noticeService.updateNotice(dto);

        // 업데이트 후 공지사항 목록 페이지로 리다이렉트
        return "redirect:/notice?pageNum=" + pageNum;
    }

    @GetMapping("notice/delete")
    public String delete(@RequestParam("boards_id") int boards_id)
    {
        //저장된 사진 지우기
        String nPhoto=noticeService.getSelectById(boards_id).getNphoto();
        String nfile=noticeService.getSelectById(boards_id).getNfile();
        storageService.deleteFile(bucketName,"sawon",nPhoto);
        storageService.deleteFile(bucketName,"sawon",nfile);
        noticeService.deleteNotice(boards_id);

        return "redirect:/notice";
    }

    @GetMapping("/notice/detail")
    public String detail(@RequestParam ("boards_id") int boards_id,
                         @RequestParam (defaultValue = "1") int pageNum, Model model,
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

        model.addAttribute("dto", dto);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("imagePath", imagePath);
        model.addAttribute("filePath", filePath);
        return"page4/noticedetail";
    }
}
