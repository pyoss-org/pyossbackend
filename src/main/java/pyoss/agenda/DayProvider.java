package pyoss.agenda;

import java.time.LocalDate;
import java.util.Optional;

public interface DayProvider {

    Optional<Day> getDay(LocalDate forDate);

}
