package pyoss.agenda;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import pyoss.exception.NotFoundException;

import java.time.LocalDateTime;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DayControllerTest {

    @Mock
    private AgendaService agendaServiceMock;

    @Mock
    private Page<Day> pageMock;

    @Test

    public void nextDayWithAvailableSlot_nextAvailable_callsServices_firstDayWithAvailabilityAfter() {
        DayController controller = new DayController(agendaServiceMock);
        when(agendaServiceMock.dayPageWithAvailabilityAfter(any(LocalDateTime.class), anyInt())).thenReturn(pageMock);

        controller.nextDayWithAvailableSlot(true, 0);

        verify(agendaServiceMock, times(1)).dayPageWithAvailabilityAfter(any(LocalDateTime.class), anyInt());
        verifyNoMoreInteractions(agendaServiceMock);
    }

    @Test(expected = NotFoundException.class)
    public void nextDayWithAvailableSlot_noNextAvailable_throws_notFound() {
        DayController controller = new DayController(agendaServiceMock);

        controller.nextDayWithAvailableSlot(false, 0);
    }

    @Test(expected = NotFoundException.class)
    public void nextDayWithAvailableSlot_nullNextAvailable_throws_notFound() {
        DayController controller = new DayController(agendaServiceMock);

        controller.nextDayWithAvailableSlot(null, 0);
    }

}