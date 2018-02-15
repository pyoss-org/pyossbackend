package pyoss;

import org.springframework.stereotype.Component;
import pyoss.agenda.TimeSlot;

import java.util.List;

@Component
public class TimeSlotRepository {

    public List<TimeSlot> available() {
        throw new RuntimeException("WIP");
    }
}
