package pyoss.agenda;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TimeSlot {


    private LocalDateTime from;
    private LocalDateTime to;
    private boolean available;

    public TimeSlot(LocalDateTime from, LocalDateTime to, boolean available) {
        this.from = from;
        this.to = to;
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSlot timeSlot = (TimeSlot) o;

        if (available != timeSlot.available) return false;
        if (from != null ? !from.equals(timeSlot.from) : timeSlot.from != null) return false;
        return to != null ? to.equals(timeSlot.to) : timeSlot.to == null;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (available ? 1 : 0);
        return result;
    }
}
