package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
public class WebController {

    @Autowired
    private CodeSnippetService codeSnippetService;
    private static final String CODE_HTML = ResourceReader.readFileToString("code.html");
    private static final String CREATE_HTML = ResourceReader.readFileToString("create.html");

    @GetMapping(path = "/code")
    private String getCodeHtml() {
        CodeSnippet codeSnippet = codeSnippetService.getCodeSnippet();
        return String.format(
            CODE_HTML,
            codeSnippet.getDate(),
            codeSnippet.getCode()
        );
    }

    @GetMapping(path = "/code/new")
    private String getCreateHtml() {
        return CREATE_HTML;
    }
}
