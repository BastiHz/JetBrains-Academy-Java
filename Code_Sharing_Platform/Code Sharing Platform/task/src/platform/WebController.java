package platform;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
public class WebController {

    @GetMapping(path = "/code")
    private String getCode() {
        return new CodePiece().getHtmlCode();
    }
}
