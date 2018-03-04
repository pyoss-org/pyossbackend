package pyoss.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pyoss.agenda.Agenda;
import pyoss.agenda.AgendaRepository;

import java.util.Optional;

@Component
public class ConfigRepositoryImpl implements ConfigRepository {


    @Autowired //TODO: shouldnt keep this dependency forever.
    private AgendaRepository agendaRepository;


    public Optional<SlotConfiguration> getFor(String ownerName) {
        return agendaRepository.getFor(ownerName).map(Agenda::config);
    }


    @Override
    public void update(SlotConfiguration config, String ownerName) {
        Agenda agenda = agendaRepository.getFor(ownerName).orElseThrow(() -> new AgendaOwnerDoesNotExist(ownerName));
        agenda.setConfig(config);
        agendaRepository.update(agenda);
    }

    private class AgendaOwnerDoesNotExist extends RuntimeException {
        public AgendaOwnerDoesNotExist(String ownerName) {
            super(ownerName);
        }
    }
}
