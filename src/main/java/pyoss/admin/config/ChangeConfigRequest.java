package pyoss.admin.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ChangeConfigRequest {
    private final int closingTime;
    private final int openingTime;
    private final int minutesPerSlot;

    @JsonCreator
    public ChangeConfigRequest(@JsonProperty("closingTime") int closingTime,
                               @JsonProperty("openingTime") int openingTime,
                               @JsonProperty("minutesPerSlot") int minutesPerSlot
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
