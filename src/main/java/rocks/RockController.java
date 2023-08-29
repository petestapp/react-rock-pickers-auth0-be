package rocks;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class RockController {
    private RockService rockService;

    public RockController(RockService rockService) {
        this.rockService = rockService;
    }
}
