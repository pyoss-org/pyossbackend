package pyoss;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import pyoss.agenda.Agenda;
import pyoss.agenda.AgendaRepository;

import java.util.HashMap;
import java.util.Map;

@org.springframework.context.annotation.Configuration
@Profile("inmemory")
public class InmemoryConfiguration {

    @Bean
    public AgendaRepository agendaRepository() {
        return new AgendaRepository() {

            private final Map<String, Agenda> ownerAgendas = new HashMap<>();

            @Override
            public Agenda getFor(String ownerName) {
                return ownerAgendas.computeIfAbsent(ownerName, (oN) -> Agenda.createForOwner(ownerName));
            }

            @Override
            public void insert(Agenda agenda) {
                ownerAgendas.put(agenda.ownerName(), agenda);
            }

            @Override
            public void update(Agenda agenda) {
                ownerAgendas.put(agenda.ownerName(), agenda);
            }
        };
    }
}
