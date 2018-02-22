package pyoss.agenda;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Day {


    private LocalDate date;
    private List<TimeSlot> slots;

    public Day(LocalDate date, List<TimeSlot> slots) {
        this.date = date;
        this.slots = slots;
    }

    //TODO generate timeslots between open and closing
    public static Day createFor(LocalDate day, int openingTime, int closingTime) {
        return new Day(day, new ArrayList<>());
    }

    public LocalDate getDate() {
        return date;
    }
}
