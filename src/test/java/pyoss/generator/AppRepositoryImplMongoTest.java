package pyoss.generator;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pyoss.MongoTest;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class AppRepositoryImplMongoTest extends MongoTest {

    @Autowired
    private AppRepository repository;

    @Test
    public void insert_insertsANewApp() {
        String expectedName = presetApp();
        App actual = repository.findByOwner(expectedName).get();

        assertEquals(expectedName, actual.getOwnerName());
        assertEquals(36, actual.getIdentifier().length());
    }


    @Test
    public void findByOwner_isSafeToUseWithUnknownParameter() {
        presetApp();
        Optional<App> actual = repository.findByOwner("Satan");

        assertEquals(Optional.empty(), actual);
    }

    private String presetApp() {
        String expectedName = "Reinout";
        App app = App.create(expectedName);

        repository.insert(app);

        return expectedName;
    }
}