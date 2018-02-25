package pyoss.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pyoss.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(DayController.PATH)
public class DayController {

    static final String PATH = "days";

    private final AgendaService agendaService;

    @Autowired
    public DayController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    public List<Day> nextDayWithAvailableSlot(@RequestParam("next") Boolean showNextAvailable) {
        if (!isNull(showNextAvailable) && showNextAvailable) {
            return List.of(agendaService.firstDayWithAvailabilityAfter(LocalDateTime.now()));
        }
        throw new NotFoundException();
    }

}
