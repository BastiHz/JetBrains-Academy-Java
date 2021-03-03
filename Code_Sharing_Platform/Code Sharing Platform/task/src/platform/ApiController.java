package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    @Autowired
    private CodeSnippetService codeSnippetService;

    @GetMapping(path = "/code")
    private Code getCode() {
        return codeSnippetService.getCodeSnippet();
    }

    @PostMapping(path = "/code/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    private String updateCode(@RequestBody Code newCode) {
        codeSnippetService.setCodeSnippet(newCode);
        return "{}";
    }
}
