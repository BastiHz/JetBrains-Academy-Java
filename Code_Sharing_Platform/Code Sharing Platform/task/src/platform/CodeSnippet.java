package platform;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CodeSnippet {
    private String code;
    private String date;
    private static final DateTimeFormatter dateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void setCode(String code) {
        this.code = code;
        date = LocalDateTime.now().format(dateTimeFormatter);
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }
}
