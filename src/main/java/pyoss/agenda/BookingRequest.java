package pyoss.agenda;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BookingRequest {

    private int fromH;
    private int toH;
    private int fromM;
    private int toM;
    private int dateY;
    private int dateM;
    private int dateD;

    @JsonCreator
    public BookingRequest(@JsonProperty("fromH") int fromH,
                          @JsonProperty("toH") int toH,
                          @JsonProperty("fromM") int fromM,
                          @JsonProperty("toM") int toM,
                          @JsonProperty("dateY") int dateY,
                          @JsonProperty("dateM") int dateM,
                          @JsonProperty("dateD") int dateD
    ) {
        this.fromH = fromH;
        this.toH = toH;
        this.fromM = fromM;
        this.toM = toM;
        this.dateY = dateY;
        this.dateM = dateM;
        this.dateD = dateD;
    }

    public int fromM() {
        return fromM;
    }

    public int fromH() {
        return fromH;
    }

    public LocalDate date() {
        return LocalDate.of(dateY, dateM, dateD);
    }
}
