package data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.ocpsoft.prettytime.PrettyTime;

import java.sql.Timestamp;

@Data
@Alias("NoticeDto")
public class NoticeDto {
    private int boards_id;
    private int users_id;
    private String subject;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Timestamp writeday;
    private String nphoto;
    private String nfile;
    private String nickname;
    public String getPrettyTime() {
        PrettyTime p = new PrettyTime();
        return p.format(
                this.writeday != null ? this.writeday : new Timestamp(System.currentTimeMillis())
        );
    }
    private boolean isRead;
}
