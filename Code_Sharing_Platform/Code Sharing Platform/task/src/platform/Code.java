package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
public class Code {

    private static final DateTimeFormatter dateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Id
    @JsonIgnore
    private final String id = UUID.randomUUID().toString();
    @Type(type = "text")  // otherwise the column is varchar(255) which is too small
    private String code;
    private final LocalDateTime date = LocalDateTime.now();
    private int views;  // visible for this many views
    private int time;  // visible for this many seconds
    @JsonIgnore
    private boolean viewRestricted = false;
    @JsonIgnore
    private boolean timeRestricted = false;

    public String getId() {
        return id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date.format(dateTimeFormatter);
    }

    public void setViews(int views) {
        this.views = views;
        if (views > 0) {
            viewRestricted = true;
        }
    }

    public int getViews() {
        return views;
    }

    public boolean isViewRestricted() {
        return viewRestricted;
    }

    void updateViews() {
        if (viewRestricted) {
            views--;
        }
    }

    public void setTime(int time) {
        this.time = time;
        if (time > 0) {
            timeRestricted = true;
        }
    }

    public long getTime() {
        if (timeRestricted) {
            LocalDateTime timeLimit = date.plusSeconds(time);
            return LocalDateTime.now().until(timeLimit, ChronoUnit.SECONDS);
        }
        return 0;
    }

    public boolean isTimeRestricted() {
        return timeRestricted;
    }

    boolean isInaccessible() {
        return viewRestricted && views <= 0
            || timeRestricted && getTime() <= 0;
    }
}
