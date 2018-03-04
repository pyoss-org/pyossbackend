package pyoss.agenda;

import pyoss.admin.config.SlotConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DayCreator {


    private DayCreator() {
    }

    public static Day createFor(LocalDate localDate, SlotConfiguration config) {
        return new DayCreator().createFor(localDate, config.getOpeningTime(), config.getClosingTime(), config.getMinutesPerSlot());
    }


    public Day createFor(LocalDate day, int openingTime, int closingTime, int minutesPerSlot) {
        List<TimeSlot> slots = new ArrayList<>();

        LocalDateTime start = day.atTime(openingTime, 0);
        TimeSlot nextSlot = TimeSlot.createFor(start, minutesPerSlot);
        while (!nextSlot.endsAfter(closingTime) && sameDay(day, nextSlot)) {
            slots.add(nextSlot);
            start = start.plusMinutes(minutesPerSlot);
            nextSlot = TimeSlot.createFor(start, minutesPerSlot);
        }


        return new Day(day, slots);
    }

    private boolean sameDay(LocalDate day, TimeSlot nextSlot) {
        LocalDateTime endTime = nextSlot.getTo();
        LocalDateTime midnightInclusive = endTime.minusSeconds(1);
        return midnightInclusive
                .toLocalDate()
                .equals(day);
    }
}
