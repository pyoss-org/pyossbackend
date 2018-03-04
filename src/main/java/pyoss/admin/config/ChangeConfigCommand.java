package pyoss.admin.config;

public class ChangeConfigCommand {

    private final Integer openingHour;
    private final Integer closingHour;
    private final Integer minutesPerSlot;

    public ChangeConfigCommand(Integer openingHour, Integer closingHour, Integer minutesPerSlot) {
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.minutesPerSlot = minutesPerSlot;
    }

    public static ChangeConfigCommand from(ChangeConfigRequest changeConfigRequest) {
        return new ChangeConfigCommand(changeConfigRequest.getOpeningHour(), changeConfigRequest.getClosingHour(), changeConfigRequest.getMinutesPerSlot());
    }

    public int getOpeningHour() {
        return openingHour;
    }

    public int getClosingHour() {
        return closingHour;
    }

    public int getMinutesPerSlot() {
        return minutesPerSlot;
    }
}
