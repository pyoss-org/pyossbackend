package pyoss.agenda;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.Assert.assertEquals;

public class AgendaTest {


    LocalDateTime todayBeforeClosing = now().withHour(12);
    LocalDateTime todayAfterClosing = now().withHour(23);

    @Test
    public void emptyAgenda_checkBeforeClosing_firstAvailableDayIsToday() {
        Agenda emptyAgenda = createEmptyAgenda();
        Day day = emptyAgenda.firstAvailableDayAfter(todayBeforeClosing);
        assertEquals(0, daysUntil(day));

    }

    @Test
    public void emptyAgenda_checkAfterClosing_firstAvailableDayIsTommorow() {
        Agenda emptyAgenda = createEmptyAgenda();
        Day day = emptyAgenda.firstAvailableDayAfter(todayAfterClosing);
        assertEquals(1, daysUntil(day));
    }

    private Agenda createEmptyAgenda() {
        return Agenda.createForOwner("doesntmatter");
    }

    private int daysUntil(Day day) {
        LocalDate now = LocalDate.now();
        return (int) DAYS.between(now, day.getDate());
    }


}