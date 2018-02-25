package pyoss.agenda;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DayTest {

    @Test
    public void create8to10_shouldCreate4Slots(){
        Day day = Day.createFor(LocalDate.now(), 8, 10);
        assertEquals(day.getDate(), LocalDate.now());
        assertEquals(day.getSlots().size(), 4);


    }

}