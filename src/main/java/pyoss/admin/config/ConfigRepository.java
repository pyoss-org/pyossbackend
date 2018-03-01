package pyoss.admin.config;

public interface ConfigRepository {

    SlotConfiguration getFor(String ownerName);


    void update(SlotConfiguration config, String ownerName);
}
