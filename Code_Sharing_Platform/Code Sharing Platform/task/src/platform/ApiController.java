package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    @Autowired
    private CodeService codeService;

    @GetMapping(path = "/code/{id}")
    public Code getCodeById(@PathVariable int id) {
        return codeService.getCodeById(id);
    }

    @GetMapping(path = "/code/latest")
    public List<Code> getLatestCode() {
        return codeService.getLatestCodes();
    }

    @PostMapping(path = "/code/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> addCode(@RequestBody Code newCode) {
        int id = codeService.addCode(newCode);
        // return id as String because the exercise requires it
        return Map.of("id", String.valueOf(id));
    }
}
