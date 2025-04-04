package data.mapper;

import data.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {
    public void insertNotice(NoticeDto dto);

    public void updateNotice(NoticeDto dto);

    public void deleteNotice(int boards_id);

    public int getTotalCount();

    public List<NoticeDto> getPagingList(@Param("startNum")int startNum, @Param("perPage") int perPage);

    public NoticeDto getSelectById(int boards_id);

    public List<NoticeDto> getLastestNotices();
}
