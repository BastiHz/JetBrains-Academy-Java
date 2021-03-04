package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller  // needs to be @Controller not @RestController or else templates won't load
@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
public class WebController {

    @Autowired
    private CodeService codeService;

    @GetMapping(path = "/code/{id}")
    public ModelAndView getCodeById(@PathVariable int id) {
        ModelAndView mav = new ModelAndView("code");
        Code code = codeService.getCodeById(id);
        mav.addObject("code", code);
        return mav;
    }

    @GetMapping(path = "/code/latest")
    public ModelAndView getLatestCodes() {
        ModelAndView mav = new ModelAndView("code_latest");
        List<Code> latestCodes = codeService.getLatestCodes();
        mav.addObject("codes", latestCodes);
        return mav;
    }

    @GetMapping(path = "/code/new")
    public String getCodeNew() {
        return "code_new";
    }
}
