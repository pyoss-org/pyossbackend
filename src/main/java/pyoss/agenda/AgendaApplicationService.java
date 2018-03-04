package pyoss.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pyoss.ContextProvider;
import pyoss.agenda.booking.BookingRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static pyoss.pages.Pager.createPageFor;
import static pyoss.pages.Pager.onePageRequest;

@Service
public class AgendaApplicationService {

    @Autowired
    private ContextProvider contextProvider;

    @Autowired
    private AgendaRepository agendaRepository;

    public Day firstDayWithAvailabilityAfter(LocalDateTime check) {
        return agenda().firstAvailableDayAfter(check);
    }


    public void doBooking(BookingRequest bookingRequest) {
        Agenda agenda = agenda();
        agenda.doBooking(bookingRequest);
        agendaRepository.update(agenda);
    }

    public Page<Day> dayPageWithAvailabilityAfter(LocalDateTime check, int offset) {
        List<Day> availableDays = agenda().availableDaysAfter(check.plusDays(offset));
        return createPageFor(onePageRequest(offset), availableDays);
    }

    private Agenda agenda() {
        return getAgendaFor(contextProvider.getOwnerName());
    }

    Agenda getAgendaFor(String ownerName) {
        Optional<Agenda> existingAgenda = agendaRepository.getFor(ownerName);
        return existingAgenda.orElseGet(() -> createNewAgendaFor(ownerName));
    }

    private Agenda createNewAgendaFor(String ownerName) {
        Agenda createdAgenda = Agenda.createForOwner(ownerName);
        agendaRepository.insert(createdAgenda);
        return createdAgenda;
    }
}
