package calculator;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
class ArithmeticRestController {

    @GetMapping(path = "/{operation}")
    public String calculate(@PathVariable String operation, @PathParam("a") int a, @PathParam("b") int b) {
        switch (operation) {
            case "add":
                return String.valueOf(a + b);
            case "subtract":
                return String.valueOf(a - b);
            case "mult":
                return String.valueOf(a * b);
            default:
                return "Unknown operation";
        }
    }
}
