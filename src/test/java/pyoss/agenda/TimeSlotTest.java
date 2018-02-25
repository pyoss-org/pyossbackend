package pyoss.agenda;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TimeSlotTest {



    @Test
    public void creater(){
        LocalDateTime eightOClocl = LocalDateTime.of(2018, 1, 1, 20,0);
        TimeSlot slot = TimeSlot.createFor(eightOClocl, 30);
        assertEquals(eightOClocl, slot.getFrom());
        assertEquals(eightOClocl.withMinute(30), slot.getTo());
    }


    @Test
    public void endsAfter_indeed(){
        LocalDateTime eightOClocl = LocalDateTime.of(2018, 1, 1, 20,0);
        TimeSlot slot = TimeSlot.createFor(eightOClocl, 30);
        assertTrue(slot.endsAfter(20));
    }

    @Test
    public void endsAfter_boundaryNope(){
        LocalDateTime eightOClocl = LocalDateTime.of(2018, 1, 1, 19,30);
        TimeSlot slot = TimeSlot.createFor(eightOClocl, 30);
        assertFalse(slot.endsAfter(20));
    }


    @Test
    public void endsAfter_boundaryYup(){
        LocalDateTime eightOClocl = LocalDateTime.of(2018, 1, 1, 19,31);
        TimeSlot slot = TimeSlot.createFor(eightOClocl, 30);
        assertTrue(slot.endsAfter(20));
    }
}