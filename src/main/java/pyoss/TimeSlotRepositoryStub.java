package pyoss;

import org.springframework.stereotype.Component;
import pyoss.agenda.TimeSlot;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TimeSlotRepositoryStub extends TimeSlotRepository {

    public List<TimeSlot> available() {
        LocalDateTime threeOClock = LocalDateTime.of(2018, 3, 3, 3, 0);
        LocalDateTime fourOclock = LocalDateTime.of(2018, 3, 3, 4, 0);
        TimeSlot availableSlot = new TimeSlot(threeOClock, fourOclock, true);
        return List.of(availableSlot);
    }
}
