package pyoss.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static pyoss.pages.Pager.createPageFor;
import static pyoss.pages.Pager.onePageRequest;

@Service
public class AgendaService {

    private static final String OWNER_NAME = "kapperx";

    @Autowired
    private AgendaRepository agendaRepository;

    public Day firstDayWithAvailabilityAfter(LocalDateTime check) {
        return agenda().firstAvailableDayAfter(check);
    }

    Agenda getOrCreateAgendaFor(String ownerName) {
        Agenda existingAgenda = agendaRepository.getFor(ownerName);
        if (existingAgenda == null) {
            Agenda createdAgenda = Agenda.createForOwner(ownerName);
            agendaRepository.insert(createdAgenda);
            return createdAgenda;
        } else {
            return existingAgenda;
        }
    }

    public void doBooking(BookingRequest bookingRequest) {
        Agenda agenda = agenda();
        agenda.doBooking(bookingRequest);
        agendaRepository.update(agenda);
    }

    public Page<Day> dayPageWithAvailabilityAfter(LocalDateTime check, int offset) {
        return createPageFor(onePageRequest(offset), agenda().availableDaysAfter(check.plusDays(offset)));
    }

    private Agenda agenda() {
        return getOrCreateAgendaFor(OWNER_NAME);
    }

}
