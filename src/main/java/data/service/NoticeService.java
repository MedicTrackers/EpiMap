package data.service;

import data.dto.NoticeDto;
import data.mapper.NoticeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class NoticeService {
    final NoticeMapper noticeMapper;

    public void insertNotice(NoticeDto dto) {
        noticeMapper.insertNotice(dto);
    }

    public void deleteNotice(int boards_id) {
        noticeMapper.deleteNotice(boards_id);
    }

    public void updateNotice(NoticeDto dto) {
        noticeMapper.updateNotice(dto);
    }

    public int getTotalCount() {
        return noticeMapper.getTotalCount();
    }

    public List<NoticeDto> getPagingList(Map<String, Object> map)
    {
        List<NoticeDto> noticeList = noticeMapper.getPagingList(map);
        for (NoticeDto dto : noticeList) {
            if (dto.getNphoto() != null) {
                dto.setThumbnail(dto.getNphoto().split(",")[0]);
            }
        }
        return noticeList;
    }

    public NoticeDto getSelectById(int boards_id) {
        return noticeMapper.getSelectById(boards_id);
    }

    public List<NoticeDto> getLastestNotices() {
        return noticeMapper.getLastestNotices();
    }

    public int isBoardRead(Map<String, Object> map) {
        return noticeMapper.isBoardRead(map);
    }

    public void insertBoardRead(Map<String, Object> map) {
        noticeMapper.insertBoardRead(map);
    }

}
