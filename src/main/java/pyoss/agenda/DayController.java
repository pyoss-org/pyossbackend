package pyoss.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class DayController {

    @Autowired
    private AgendaService agendaService;

    @RequestMapping("/day/nextavailable")
    public Day nextDayWithAvailableSlot() {
        return agendaService.firstDayWithAvailabilityAfter(LocalDateTime.now());
    }

    @PostMapping(value = "/slot/book", consumes = "application/json;charset=UTF-8")
    public void doBooking(@RequestBody() BookingRequest bookingRequest){
        agendaService.doBooking(bookingRequest);
    }

}
