package pyoss.agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
            return getOrCreate(check.toLocalDate());
        } else {
            return getOrCreate(check.toLocalDate().plusDays(1));
        }
    }

    public List<Day> availableDaysAfter(LocalDateTime check) {
        return List.of(firstAvailableDayAfter(check));
    }

    private Day getOrCreate(LocalDate localDate) {
        return dayInAgenda(localDate)
                .orElseGet(() -> Day.createFor(localDate, openingTime, closingTime));
    }

    private boolean isBeforeClosing(LocalDateTime check) {
        return check.getHour() < closingTime;
    }

    public void doBooking(BookingRequest bookingRequest) {
        LocalDate dateToBook = LocalDate.now();//TODO should come from request
        if(!isBeforeClosing(LocalDateTime.now())){
            dateToBook = dateToBook.plusDays(1);//wont be necessary. te
        }
        Day day = getOrCreate(dateToBook);
        day.book(bookingRequest);
        if(!days.contains(day)){
            days.add(day);
        }
    }

    private Optional<Day> dayInAgenda(LocalDate dateToBook) {
        return days.stream()
                .filter(day -> day.getDate().equals(dateToBook))
                .findFirst();
    }
}
