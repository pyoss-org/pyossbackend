package pyoss.agenda;

import pyoss.admin.config.SlotConfiguration;
import pyoss.agenda.booking.BookingRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Agenda {

    private SlotConfiguration config;

    private String id;
    private String ownerName;
    private List<Day> days;

    private Agenda(SlotConfiguration config, String ownerName, List<Day> days) {
        this.config = config;
        this.ownerName = ownerName;
        this.days = days;
    }

    public static Agenda createForOwner(String ownerName) {
        return new Agenda(SlotConfiguration.createDefault(), ownerName, new ArrayList<>());
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
        return AvailableDayFinder.firstAvailableAfter(check, days, config);

    }

    public List<Day> availableDaysAfter(LocalDateTime check) {
        return List.of(firstAvailableDayAfter(check));
    }

    private Day getOrCreate(LocalDate localDate) {
        return dayInAgenda(localDate)
                .orElseGet(() -> DayCreator.createFor(localDate, config));
    }

    public void doBooking(BookingRequest bookingRequest) {
        LocalDate dateToBook = bookingRequest.date();
        Day day = getOrCreate(dateToBook);
        day.book(bookingRequest);
        if (!days.contains(day)) {
            days.add(day);
        }
    }

    private Optional<Day> dayInAgenda(LocalDate dateToBook) {
        return days.stream()
                .filter(day -> day.getDate().equals(dateToBook))
                .findFirst();
    }

    public SlotConfiguration config() {
        return config;

    }

    public void setConfig(SlotConfiguration config) {
        this.config = config;
    }
}
