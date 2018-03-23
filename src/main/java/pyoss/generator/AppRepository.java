package pyoss.generator;

import java.util.Optional;

public interface AppRepository {

    void insert(App app);

    Optional<App> findByOwner(String ownerName);

}
