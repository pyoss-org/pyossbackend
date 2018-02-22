package pyoss.agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agenda {


    private int openingTime = 9;
    private int closingTime = 17;
    private String id;
    private String ownerName;
    private List<Day> days;

    public Agenda(String ownerName, List<Day> days) {
        this.ownerName = ownerName;
        this.days = days;
    }

    public static Agenda createForOwner(String ownerName) {
        return new Agenda(ownerName, new ArrayList<>());
    }

    public String ownerName() {
        return ownerName;
    }

    public List<Day> days() {
        return Collections.unmodifiableList(days);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "id='" + id + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }

    public Day firstAvailableDayAfter(LocalDateTime check) {
        if (isBeforeClosing(check)) {
            return Day.createFor(LocalDate.now(), openingTime, closingTime);
        } else {
            return Day.createFor(LocalDate.now().plusDays(1), openingTime, closingTime);
        }
    }

    private boolean isBeforeClosing(LocalDateTime check) {
        return check.getHour() < closingTime;
    }
}
