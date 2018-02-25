package pyoss.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pyoss.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
public class DayController {

    private final AgendaService agendaService;

    @Autowired
    public DayController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @RequestMapping("/days")
    public Day nextDayWithAvailableSlot(@RequestParam("next") Boolean showNextAvailable) {
        if (!isNull(showNextAvailable) && showNextAvailable) {
            return agendaService.firstDayWithAvailabilityAfter(LocalDateTime.now());
        }
        throw new NotFoundException();
    }

    @PostMapping(value = "/slot/book", consumes = "application/json;charset=UTF-8")
    public void doBooking(@RequestBody() BookingRequest bookingRequest){
        agendaService.doBooking(bookingRequest);
    }

}
