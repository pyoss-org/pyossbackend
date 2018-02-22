package pyoss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pyoss.agenda.Agenda;
import pyoss.agenda.Day;

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
}
