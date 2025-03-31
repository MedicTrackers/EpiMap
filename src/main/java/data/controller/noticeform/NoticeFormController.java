package data.controller.noticeform;

import data.dto.NoticeDto;
import data.service.NoticeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import naver.storage.NcpObjectStorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public abstract class NoticeFormController {
    final NoticeService noticeService;
    /*final UsersService usersService;*/
    final NcpObjectStorageService storageService;

    private String bucketName = "epimap";

    @GetMapping("/noticeform")
    public String noticeform(@RequestParam(value = "boards_id", defaultValue = "0") int boards_id, Model model) {
        return "page4/noticeform";
    }

    @PostMapping("/insert")
    public String insert(
            @ModelAttribute NoticeDto dto,
            @RequestParam int pageNum,
            @RequestParam("upload") List<MultipartFile> upload,
            HttpSession session) {
        String users_id = (String) session.getAttribute("loginUser");
        String writer;
        return "redirect:/notice";
    }
}