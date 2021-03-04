package platform;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Code {

    private static final DateTimeFormatter dateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Type(type = "text")  // otherwise the column is varchar(255) which is too small
    private String code;
    // The exercise tests require the date to be the same on the website
    // and in the json for the api. That's why I simply use a String
    // instead of LocalDateTime to store the date.
    private String date;

    public int getId() {
        return id;
    }

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
