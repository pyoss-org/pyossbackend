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

}
