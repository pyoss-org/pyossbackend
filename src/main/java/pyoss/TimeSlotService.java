package pyoss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pyoss.agenda.TimeSlot;

import java.util.List;

@Service
public class TimeSlotService {


    @Autowired
    private TimeSlotRepository timeSlotRepository;


    public List<TimeSlot> timeslots() {
        return timeSlotRepository.available();
    }
}
