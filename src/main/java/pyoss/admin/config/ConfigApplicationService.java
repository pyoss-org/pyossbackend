package pyoss.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pyoss.ContextProvider;

@Component
public class ConfigApplicationService {


    @Autowired
    private ConfigRepository configRepository;
    @Autowired
    private ContextProvider contextProvider;


    public void updateConfig(ChangeConfigCommand command) {
        SlotConfiguration config = SlotConfiguration.createFor(command.getOpeningHour(), command.getClosingHour(), command.getMinutesPerSlot());
        configRepository.update(config, contextProvider.getOwnerName());
    }

    public SlotConfiguration getConfig() {
        return configRepository.getFor(contextProvider.getOwnerName());
    }
}
