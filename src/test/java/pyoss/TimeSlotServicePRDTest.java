package pyoss;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("prd")
@SpringBootTest
public class TimeSlotServicePRDTest {

    @Autowired
    private TimeSlotService timeSlotService;

    @Test(expected = RuntimeException.class)
    public void getAvailable() {
        timeSlotService.timeslots();
    }
}