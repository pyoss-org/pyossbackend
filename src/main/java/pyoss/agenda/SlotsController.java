package pyoss.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SlotsController.PATH)
public class SlotsController {

    static final String PATH = "slots";

    private final AgendaService agendaService;

    @Autowired
    public SlotsController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public void doBooking(@RequestBody() BookingRequest bookingRequest){
        agendaService.doBooking(bookingRequest);
    }
}
