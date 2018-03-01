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
    private AgendaApplicationService agendaApplicationServiceMock;

    @Mock
    private Page<Day> pageMock;

    @Test

    public void nextDayWithAvailableSlot_nextAvailable_callsServices_firstDayWithAvailabilityAfter() {
        DayController controller = new DayController(agendaApplicationServiceMock);
        when(agendaApplicationServiceMock.dayPageWithAvailabilityAfter(any(LocalDateTime.class), anyInt())).thenReturn(pageMock);

        controller.nextDayWithAvailableSlot(true, 0);

        verify(agendaApplicationServiceMock, times(1)).dayPageWithAvailabilityAfter(any(LocalDateTime.class), anyInt());
        verifyNoMoreInteractions(agendaApplicationServiceMock);
    }

    @Test(expected = NotFoundException.class)
    public void nextDayWithAvailableSlot_noNextAvailable_throws_notFound() {
        DayController controller = new DayController(agendaApplicationServiceMock);

        controller.nextDayWithAvailableSlot(false, 0);
    }

    @Test(expected = NotFoundException.class)
    public void nextDayWithAvailableSlot_nullNextAvailable_throws_notFound() {
        DayController controller = new DayController(agendaApplicationServiceMock);

        controller.nextDayWithAvailableSlot(null, 0);
    }

}