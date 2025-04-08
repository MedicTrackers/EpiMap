package data.mapper;

import data.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;


@Mapper
public interface NoticeMapper {
    public void insertNotice(NoticeDto dto);

    public void updateNotice(NoticeDto dto);

    public void deleteNotice(int boards_id);

    public int getTotalCount();

    List<NoticeDto> getPagingList(Map<String, Object> map);

    public NoticeDto getSelectById(int boards_id);

    public List<NoticeDto> getLastestNotices();

    public int isBoardRead(Map<String, Object> map);

    public void insertBoardRead(Map<String, Object> map);

}
