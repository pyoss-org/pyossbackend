package pyoss.agenda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pyoss.exception.NotFoundException;

import java.time.LocalDateTime;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DayControllerTest {

    @Mock
    private AgendaService agendaServiceMock;

    @Test
    public void nextDayWithAvailableSlot_nextAvailable_callsServices_firstDayWithAvailabilityAfter() {
        DayController controller = new DayController(agendaServiceMock);

        controller.nextDayWithAvailableSlot(true);

        verify(agendaServiceMock, times(1)).firstDayWithAvailabilityAfter(any(LocalDateTime.class));
        verifyNoMoreInteractions(agendaServiceMock);
    }

    @Test(expected = NotFoundException.class)
    public void nextDayWithAvailableSlot_noNextAvailable_throws_notFound() {
        DayController controller = new DayController(agendaServiceMock);

        controller.nextDayWithAvailableSlot(false);
    }

    @Test(expected = NotFoundException.class)
    public void nextDayWithAvailableSlot_nullNextAvailable_throws_notFound() {
        DayController controller = new DayController(agendaServiceMock);

        controller.nextDayWithAvailableSlot(null);
    }

}