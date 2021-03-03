package platform;

import java.time.LocalDateTime;

public class Code {
    private String code;
    private LocalDateTime date;

    public void setCode(String code) {
        this.code = code;
        date = LocalDateTime.now();
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
