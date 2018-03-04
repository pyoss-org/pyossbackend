package pyoss.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pyoss.ContextProvider;

@Component
public class ConfigApplicationService {


    private final ConfigRepository configRepository;

    private final ContextProvider contextProvider;

    @Autowired
    public ConfigApplicationService(ConfigRepository configRepository, ContextProvider contextProvider) {
        this.configRepository = configRepository;
        this.contextProvider = contextProvider;
    }

    public void updateConfig(ChangeConfigCommand command) {
        SlotConfiguration config = SlotConfiguration.createFor(command.getOpeningHour(), command.getClosingHour(), command.getMinutesPerSlot());
        configRepository.update(config, contextProvider.getOwnerName());
    }

    public SlotConfiguration getConfig() {
        return configRepository.getFor(contextProvider.getOwnerName()).orElseThrow(NoConfigForOwner::new);
    }

    private class NoConfigForOwner extends RuntimeException {
    }
}
