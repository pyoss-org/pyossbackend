package pyoss.agenda;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BookingRequest {

    private int fromH;
    private int toH;
    private int fromM;
    private int toM;

    @JsonCreator
    public BookingRequest(@JsonProperty("fromH") int fromH,
                          @JsonProperty("toH") int toH,
                          @JsonProperty("fromM") int fromM,
                          @JsonProperty("toM") int toM) {
        this.fromH = fromH;
        this.toH = toH;
        this.fromM = fromM;
        this.toM = toM;
    }

    public int fromM() {
        return fromM;
    }

    public int fromH() {
        return fromH;
    }
}
