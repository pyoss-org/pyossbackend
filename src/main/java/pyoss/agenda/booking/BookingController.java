package pyoss.agenda.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pyoss.agenda.AgendaApplicationService;

@RestController
@RequestMapping(BookingController.PATH)
public class BookingController {

    static final String PATH = "slots";

    private final AgendaApplicationService agendaApplicationService;

    @Autowired
    public BookingController(AgendaApplicationService agendaApplicationService) {
        this.agendaApplicationService = agendaApplicationService;
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public void doBooking(@RequestBody() BookingRequest bookingRequest) {
        agendaApplicationService.doBooking(bookingRequest);
    }
}
