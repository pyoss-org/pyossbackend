package pyoss.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pyoss.exception.NotFoundException;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@RestController
@RequestMapping(DayController.PATH)
public class DayController {

    static final String PATH = "days";

    private final AgendaApplicationService agendaApplicationService;

    @Autowired
    public DayController(AgendaApplicationService agendaApplicationService) {
        this.agendaApplicationService = agendaApplicationService;
    }

    @GetMapping
    public Page<Day> nextDayWithAvailableSlot(@RequestParam("next") Boolean showNextAvailable, @RequestParam("page") Integer page) {
        if (!isNull(showNextAvailable) && showNextAvailable) {
            return agendaApplicationService.dayPageWithAvailabilityAfter(LocalDateTime.now(), page);
        }
        throw new NotFoundException();
    }

}
