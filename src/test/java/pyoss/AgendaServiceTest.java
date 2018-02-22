package pyoss;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pyoss.agenda.Agenda;

import java.util.UUID;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class AgendaServiceTest {

    @Autowired
    private AgendaService agendaService;

    @Test
    public void getOrCreate_notExisting_createsNew() {
        Agenda agenda = createNew();
        assertNotNull(agenda);
        assertNotNull(agenda.ownerName());
        assertTrue(agenda.days().isEmpty());
    }


    @Test
    public void getOrCreate_existing_getsExisting() {
        String randomOwnerName = UUID.randomUUID().toString();
        Agenda created = agendaService.getOrCreateAgendaFor(randomOwnerName);
        Agenda existing = agendaService.getOrCreateAgendaFor(randomOwnerName);
        assertEquals(existing.getId(), created.getId());
    }

    @Test
    public void getOrCreate_new() {
        Agenda agenda = createNew();
        Agenda agenda2 = createNew();
        assertNotEquals(agenda.getId(), agenda2.getId());
    }

    private Agenda createNew() {
        return agendaService.getOrCreateAgendaFor(UUID.randomUUID().toString());
    }
}