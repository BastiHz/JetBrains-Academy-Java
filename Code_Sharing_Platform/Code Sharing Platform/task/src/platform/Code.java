package platform;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Code {

    private static final DateTimeFormatter dateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String code;
    private String date;
    // The exercise tests require the date to be the same on the website
    // and in the json for the api. That's why I simply use a String
    // instead of LocalDateTime to store the date.

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
