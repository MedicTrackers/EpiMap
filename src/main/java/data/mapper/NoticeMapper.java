package data.mapper;

import data.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    public void insertNotice(NoticeDto dto);

    public void updateNotice(NoticeDto dto);

    public void deleteNotice(int boards_id);

    public int getTotalCount();

    public List<NoticeDto> getPagingList(int start, int perpage);

    public NoticeDto getSelectById(int boards_id);
}
