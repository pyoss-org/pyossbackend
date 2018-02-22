package pyoss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pyoss.agenda.Day;

import java.time.LocalDateTime;

@RestController
public class DayController {

    @Autowired
    private AgendaService agendaService;

    @RequestMapping("/day/nextavailable/")
    public Day nextDayWithAvailableSlot() {
        return agendaService.firstDayWithAvailabilityAfter(LocalDateTime.now());
    }

}
