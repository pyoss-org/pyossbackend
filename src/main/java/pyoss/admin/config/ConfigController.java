package pyoss.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ConfigController.PATH)
public class ConfigController {

    static final String PATH = "config";

    private final ConfigApplicationService configApplicationService;

    @Autowired
    public ConfigController(ConfigApplicationService configApplicationService) {
        this.configApplicationService = configApplicationService;
    }

    @GetMapping
    public SlotConfiguration getConfig() {
        return configApplicationService.getConfig();

    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public void update(@RequestBody() ChangeConfigRequest changeConfigRequest) {
        //TODO frontend doesnt send changed values
        ChangeConfigCommand command = ChangeConfigCommand.from(changeConfigRequest);
        configApplicationService.updateConfig(command);
    }
}