package pyoss.agenda;

import org.junit.Assert;
import org.junit.Test;
import pyoss.admin.config.SlotConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class DayCreatorTest {

    private LocalDate today = LocalDate.now();

    @Test
    public void dayIsForCorrectDate() {
        Day createdDay = DayCreator.createFor(today, SlotConfiguration.createDefault());
        assertEquals(today, createdDay.getDate());
    }

    @Test
    public void createdSlotsAreAvailble() {
        Day createdDay = DayCreator.createFor(today, SlotConfiguration.createDefault());
        createdDay.getSlots()
                .stream()
                .map(TimeSlot::isAvailable)
                .forEach(Assert::assertTrue);
    }

    @Test
    public void noSlotFitsInOpenTime_noSlotCreated() {
        Day createdDay = DayCreator.createFor(today, SlotConfiguration.createFor(9, 10, 600));
        assertAmountOfSlots(0, createdDay);

    }


    @Test
    public void hourAndHalfPerSlot_6hoursOpen_creates4SlotsOf1HourHalf() {
        Day createdDay = DayCreator.createFor(today, SlotConfiguration.createFor(9, 15, 90));
        assertAmountOfSlots(4, createdDay);
        assertEachSlotIs(90, createdDay);
    }


    @Test
    public void hourAndHalfPerSlot_7hoursOpen_creates4SlotsOf1HourHalf() {
        Day createdDay = DayCreator.createFor(today, SlotConfiguration.createFor(9, 15, 90));
        assertAmountOfSlots(4, createdDay);
        assertEachSlotIs(90, createdDay);
    }


    @Test
    public void firstSlotStartsAtOpeningTime() {
        Day createdDay = DayCreator.createFor(today, SlotConfiguration.createDefault());
        LocalDateTime startingTime = createdDay.getSlots().get(0).getFrom();
        LocalDateTime todayAtNine = today.atTime(9, 0, 0, 0);
        assertEquals(todayAtNine, startingTime);
    }

    @Test
    public void hourPerTimeslot_entireDay_shouldCreateSlotBeginningEachHour() {
        Day createdDay = DayCreator.createFor(today, SlotConfiguration.createFor(0, 24, 60));
        assertAmountOfSlots(24, createdDay);
        assertEachSlotIs(60, createdDay);
        Set<Integer> slotBeginHours = createdDay.getSlots()
                .stream()
                .map(slot -> slot.getFrom().getHour())
                .collect(Collectors.toSet());
        Set<Integer> allHoursOfDay = Stream.iterate(0, i -> i + 1)
                .limit(24)
                .collect(Collectors.toSet());
        assertEquals(allHoursOfDay, slotBeginHours);

    }

    private void assertAmountOfSlots(int amount, Day day) {
        assertEquals(amount, day.getSlots().size());
    }

    private void assertEachSlotIs(Integer minutes, Day createdDay) {
        createdDay.getSlots()
                .stream()
                .map(TimeSlot::lengthInMinutes)
                .map(minutes::equals)
                .forEach(Assert::assertTrue);
    }


}