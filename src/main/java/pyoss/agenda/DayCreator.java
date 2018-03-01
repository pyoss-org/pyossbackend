package pyoss.agenda;

import pyoss.admin.config.SlotConfiguration;

import java.time.LocalDate;

public class DayCreator {
    public static Day createFor(LocalDate localDate, SlotConfiguration config) {
        return Day.createFor(localDate, config.getOpeningTime(), config.getClosingTime());
    }
}
