package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Code {

    private static final DateTimeFormatter dateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String code;
    private LocalDateTime date;
    private String formattedDate;

    public void setCode(String code) {
        this.code = code;
        date = LocalDateTime.now();
        formattedDate = date.format(dateTimeFormatter);
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @JsonIgnore
    public String getFormattedDate() {
        return formattedDate;
    }
}
