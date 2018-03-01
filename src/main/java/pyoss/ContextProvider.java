package pyoss;

import org.springframework.stereotype.Component;

@Component
public class ContextProvider {
    //future: get data for this context from request header

    public String getOwnerName() {
        return "kapperX";
    }

    ;


}
