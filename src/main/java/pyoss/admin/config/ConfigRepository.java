package pyoss.admin.config;

import java.util.Optional;

public interface ConfigRepository {

    Optional<SlotConfiguration> getFor(String ownerName);


    void update(SlotConfiguration config, String ownerName);
}
