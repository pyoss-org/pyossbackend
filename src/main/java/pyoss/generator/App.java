package pyoss.generator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import static java.util.UUID.randomUUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class App {

    private String ownerName;
    private String identifier;

    private App(String ownerName) {
        this.ownerName = ownerName;
        this.identifier = randomUUID().toString();
    }

    public App() {
    }

    static App create(String ownerName) {
        return new App(ownerName);
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getIdentifier() {
        return identifier;
    }
}
