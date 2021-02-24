package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(path = "/api/register")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    void register(@RequestBody @Valid User user) {
        userRepository.save(user);
    }
}
