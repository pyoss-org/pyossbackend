package pyoss;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pyoss.agenda.TimeSlot;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("stub")
@SpringBootTest
public class TimeSlotServiceTest {

    @Autowired
    private TimeSlotService timeSlotService;

    @Test
    public void getAvailable() {
        List<TimeSlot> result = timeSlotService.timeslots();
        assertEquals(1, result.size());
        TimeSlot timeSlot = result.get(0);
        TimeSlot epectedSlot = getExpected();
        assertEquals(epectedSlot, timeSlot);
    }

    private TimeSlot getExpected() {
        LocalDateTime threeOClock = LocalDateTime.of(2018, 3, 3, 3, 0);
        LocalDateTime fourOclock = LocalDateTime.of(2018, 3, 3, 4, 0);
        return new TimeSlot(threeOClock, fourOclock, true);
    }
}