package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller  // needs to be @Controller not @RestController or else templates won't load
@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
public class WebController {

    @Autowired
    private CodeSnippetService codeSnippetService;

    @GetMapping(path = "/code")
    public ModelAndView getCodeHtml(ModelAndView mav) {
        Code code = codeSnippetService.getCodeSnippet();
        mav.setViewName("code");
        mav.addObject("date", code.getFormattedDate());
        mav.addObject("code", code.getCode());
        return mav;
    }

    @GetMapping(path = "/code/new")
    public String getCreateHtml() {
        return "code_new";
    }
}
