package pyoss.admin.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SlotConfiguration {

    private int openingTime;
    private int closingTime;
    private int minutesPerSlot;

    public SlotConfiguration(int openingTime, int closingTime, int minutesPerSlot) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.minutesPerSlot = minutesPerSlot;
    }

    public static SlotConfiguration createDefault() {
        return new SlotConfiguration(9, 17, 30);
    }

    public static SlotConfiguration createFor(int openingHour, int closingHour, int minutesPerSlot) {
        return new SlotConfiguration(openingHour, closingHour, minutesPerSlot);
    }

    public int getOpeningTime() {
        return openingTime;
    }

    public int getClosingTime() {
        return closingTime;
    }

    public void setOpeningTime(int openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(int closingTime) {
        this.closingTime = closingTime;
    }

    @JsonIgnore
    public boolean isAfterClosing(LocalDateTime check) {
        return check.getHour() >=
                closingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlotConfiguration that = (SlotConfiguration) o;
        return openingTime == that.openingTime &&
                closingTime == that.closingTime &&
                minutesPerSlot == that.minutesPerSlot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(openingTime, closingTime, minutesPerSlot);
    }
}
