package pyoss.agenda;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    public static TimeSlot createFor(LocalDateTime from, int durationInMinutes){
        return new TimeSlot(from, from.plusMinutes(durationInMinutes), true);
    }


    public LocalDateTime getTo() {
        return to;
    }

    public boolean endsAfter(int closingHour) {
        return closingHour < to.getHour() ||
                (closingHour == to.getHour() && to.getMinute() > 0) ;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void book() {
        this.available = false;
    }

    public boolean isAvailable() {
        return available;
    }

    public int lengthInMinutes() {
        return (int) ChronoUnit.MINUTES.between(from, to);
    }
}
