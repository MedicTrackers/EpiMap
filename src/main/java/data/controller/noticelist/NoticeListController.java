package data.controller.noticelist;

import data.dto.NoticeDto;
import data.dto.UsersDto;
import data.service.NoticeService;
import data.service.UsersService;
import jakarta.servlet.http.HttpSession;
import naver.storage.NcpObjectStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NoticeListController {
    @Autowired
    NoticeService noticeService;
    @Autowired
    NcpObjectStorageService storageService;
    @Autowired
    UsersService userService;

    private String bucketName="epimap";
    private String imagePath= "https://kr.object.ncloudstorage.com/epimap/notice/";
    private String filePath="https://kr.object.ncloudstorage.com/epimap/notice/";
    @Autowired
    private UsersService usersService;
    // 1️⃣ **공지사항 리스트 (페이징)**
    @GetMapping("/notice")
    public String noticeList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             Model model,
                             HttpSession session)
    {
        int perPage = 5; // 한 페이지당 출력할 글 개수
        int perBlock = 5; // 한 블럭당 출력할 페이지 개수
        int totalCount = noticeService.getTotalCount(); // 전체 글 개수
        int totalPage = (int) Math.ceil((double) totalCount / perPage); // 총 페이지 수
        int startNum = (pageNum - 1) * perPage; // 각 페이지에서 가져올 시작번호

        int startPage = ((pageNum - 1) / perBlock) * perBlock + 1;
        int endPage = Math.min(startPage + perBlock - 1, totalPage);

        UsersDto loginUser = (UsersDto) session.getAttribute("loginUser");
        int users_id = (loginUser != null) ? loginUser.getUsers_id() : 0;

        Map<String, Object> map = new HashMap<>();
        map.put("startNum", startNum);
        map.put("perPage", perPage);
        map.put("users_id", users_id);

        List<NoticeDto> noticeList = noticeService.getPagingList(map);


        model.addAttribute("noticeList", noticeList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("imagePath", imagePath);
        model.addAttribute("filePath", filePath);

        return "page4/notice"; // HTML 파일 경로
    }


}
