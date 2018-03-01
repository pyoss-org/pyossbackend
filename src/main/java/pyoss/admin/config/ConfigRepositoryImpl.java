package pyoss.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pyoss.agenda.Agenda;
import pyoss.agenda.AgendaRepository;

@Component
public class ConfigRepositoryImpl implements ConfigRepository {


    @Autowired //TODO: shouldnt keep this dependency forever.
    private AgendaRepository agendaRepository;


    public SlotConfiguration getFor(String ownerName) {
        return agendaRepository.getFor(ownerName).config();
    }


    @Override
    public void update(SlotConfiguration config, String ownerName) {
        Agenda agenda = agendaRepository.getFor(ownerName);
        agenda.setConfig(config);
        agendaRepository.update(agenda);
    }

}
