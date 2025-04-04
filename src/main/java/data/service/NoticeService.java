package data.service;

import data.dto.NoticeDto;
import data.mapper.NoticeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoticeService {
    final NoticeMapper noticeMapper;

    public void insertNotice(NoticeDto dto){
        noticeMapper.insertNotice(dto);
    }
    public void deleteNotice(int boards_id){
        noticeMapper.deleteNotice(boards_id);
    }
    public  void updateNotice(NoticeDto dto){
        noticeMapper.updateNotice(dto);
    }
    public int getTotalCount(){
        return noticeMapper.getTotalCount();
    }
    public List<NoticeDto> getPagingList(int startNum, int perPage){
        return noticeMapper.getPagingList(startNum, perPage);
    }
    public NoticeDto getSelectById(int boards_id)
    {
        return noticeMapper.getSelectById(boards_id);
    }
    public List<NoticeDto> getLastestNotices(){
        return noticeMapper.getLastestNotices();
    }
}
