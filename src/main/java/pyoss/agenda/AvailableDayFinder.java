package pyoss.agenda;

import pyoss.admin.config.SlotConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AvailableDayFinder {


    public static Day firstAvailableAfter(LocalDateTime check, List<Day> days, SlotConfiguration config) {
        LocalDate dateToCheck = check.toLocalDate();
        if (config.isAfterClosing(check)) {
            dateToCheck = dateToCheck.plusDays(1);
        }

        Day day = getOrCreate(dateToCheck, days, config);
        while (!day.hasAvailableSlot()) {
            dateToCheck = dateToCheck.plusDays(1);
            day = getOrCreate(dateToCheck, days, config);
        }

        return day;

    }

    private static Day getOrCreate(LocalDate localDate, List<Day> days, SlotConfiguration config) {
        return dayInAgenda(localDate, days)
                .orElseGet(() -> DayCreator.createFor(localDate, config));
    }


    private static Optional<Day> dayInAgenda(LocalDate dateToBook, List<Day> days) {
        return days.stream()
                .filter(day -> day.getDate().equals(dateToBook))
                .findFirst();
    }

}
