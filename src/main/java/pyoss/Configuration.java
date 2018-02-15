package pyoss;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@org.springframework.context.annotation.Configuration
@Profile("prd")
public class Configuration {

    @Bean
    public TimeSlotRepository timeSlotRepository() {
        return new TimeSlotRepository();
    }

}
