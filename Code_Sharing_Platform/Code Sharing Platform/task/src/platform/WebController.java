package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
public class WebController {

    @Autowired
    private CodeSnippetService codeSnippetService;
    private static final String CODE_HTML = ResourceReader.readFileToString("code.html");
    private static final String CREATE_HTML = ResourceReader.readFileToString("create.html");
    private static final DateTimeFormatter dateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping(path = "/code")
    public String getCodeHtml() {
        Code code = codeSnippetService.getCodeSnippet();
        return String.format(
            CODE_HTML,
            code.getDate().format(dateTimeFormatter),
            code.getCode()
        );
    }

    @GetMapping(path = "/code/new")
    public String getCreateHtml() {
        return CREATE_HTML;
    }
}
