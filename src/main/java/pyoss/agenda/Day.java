package pyoss.agenda;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import pyoss.agenda.booking.BookingRequest;

import java.time.LocalDate;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Day {


    private LocalDate date;
    private List<TimeSlot> slots;

    public Day(LocalDate date, List<TimeSlot> slots) {
        this.date = date;
        this.slots = slots;
    }



    public LocalDate getDate() {
        return date;
    }

    public List<TimeSlot> getSlots() {
        return slots;
    }

    public void book(BookingRequest bookingRequest) {
        slots.stream()
                .filter(timeSlot -> equalSlot(timeSlot, bookingRequest))
                .findFirst()
                .ifPresent(TimeSlot::book);

    }

    private boolean equalSlot(TimeSlot timeSlot, BookingRequest bookingRequest) {
        return timeSlot.getFrom().getHour() == bookingRequest.fromH()
        && timeSlot.getFrom().getMinute() == bookingRequest.fromM();
    }

    public boolean hasAvailableSlot() {
        return slots.stream()
                .anyMatch(TimeSlot::isAvailable);
    }
}
