package pyoss.agenda;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public static Day createFor(LocalDate day, int openingTime, int closingTime) {
        List<TimeSlot> slots = new ArrayList<>();

        LocalDateTime start = day.atTime(openingTime, 0);
        TimeSlot nextSlot = TimeSlot.createFor(start, 30);
        while (!nextSlot.endsAfter(closingTime)) {
            slots.add(nextSlot);
            start = start.plusMinutes(30);
            nextSlot = TimeSlot.createFor(start, 30);
        }


        return new Day(day, slots);
    }

    public LocalDate getDate() {
        return date;
    }

    public List<TimeSlot> getSlots() {
        return slots;
    }
}
