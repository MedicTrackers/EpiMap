package data.controller.latest;

import data.dto.NoticeDto;
import data.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class latestController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/noticelist")
    @ResponseBody
    public List<NoticeDto> getLatestNotices() {
        return noticeService.getLastestNotices();
    }
}
