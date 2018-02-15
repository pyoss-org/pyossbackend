package pyoss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pyoss.agenda.TimeSlot;

import java.util.List;

@RestController
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    @RequestMapping("/timeslots")
    public List<TimeSlot> timeslots() {
        return timeSlotService.timeslots();
    }

}
