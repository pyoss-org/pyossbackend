package pyoss.admin.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ChangeConfigRequest {
    private final Integer closingTime;
    private final Integer openingTime;
    private final Integer minutesPerSlot;

    @JsonCreator
    public ChangeConfigRequest(@JsonProperty("closingTime") Integer closingTime,
                               @JsonProperty("openingTime") Integer openingTime,
                               @JsonProperty("minutesPerSlot") Integer minutesPerSlot
    ) {
        this.closingTime = closingTime;
        this.openingTime = openingTime;
        this.minutesPerSlot = minutesPerSlot;
    }

    public int getClosingHour() {
        return closingTime;
    }

    public int getOpeningHour() {
        return openingTime;
    }

    public int getMinutesPerSlot() {
        return minutesPerSlot;
    }
}
