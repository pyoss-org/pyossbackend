package pyoss.agenda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.LocalDate.now;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class AgendaServiceTest {

    LocalDateTime todayBeforeClosing = LocalDateTime.now().withHour(12);

    @Autowired
    private AgendaService agendaService;

    private void assertEpochDay(LocalDate expected, LocalDate actual) {
        assertEquals(expected.toEpochDay(), actual.toEpochDay());
    }

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

    @Test
    public void dayPageWithAvailabilityAfter_today_generatesToday() {
        Page<Day> page = agendaService.dayPageWithAvailabilityAfter(todayBeforeClosing, 0);
        assertEpochDay(now(), firstDateIn(page));
    }

    @Test
    public void dayPageWithAvailabilityAfter_today_generatesTomorrow() {
        Page<Day> page = agendaService.dayPageWithAvailabilityAfter(todayBeforeClosing, 1);
        assertEpochDay(now().plusDays(1), firstDateIn(page));
    }

    private LocalDate firstDateIn(Page<Day> page) {
        return firstPageOf(page).getDate();
    }

    private Day firstPageOf(Page<Day> page) {
        return page.getContent().get(0);
    }

    private Agenda createNew() {
        return agendaService.getOrCreateAgendaFor(UUID.randomUUID().toString());
    }
}