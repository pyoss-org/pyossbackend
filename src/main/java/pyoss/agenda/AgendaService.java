package pyoss.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AgendaService {


    @Autowired
    private AgendaRepository agendaRepository;


    public Day firstDayWithAvailabilityAfter(LocalDateTime check) {
        Agenda agenda = getOrCreateAgendaFor("kapperx");
        return agenda.firstAvailableDayAfter(check);
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
        Agenda agenda = getOrCreateAgendaFor("kapperx");
        agenda.doBooking(bookingRequest);
        agendaRepository.update(agenda);
    }
}
