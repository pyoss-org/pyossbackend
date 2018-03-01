package pyoss.admin.config;

public class ChangeConfigCommand {

    private final int openingHour;
    private final int closingHour;
    private final int minutesPerSlot;

    public ChangeConfigCommand(int openingHour, int closingHour, int minutesPerSlot) {
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
