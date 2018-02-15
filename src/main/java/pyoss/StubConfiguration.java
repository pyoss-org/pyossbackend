package pyoss;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@org.springframework.context.annotation.Configuration
@Profile("stub")
public class StubConfiguration {

    @Bean
    public TimeSlotRepository timeSlotRepository() {
        return new TimeSlotRepositoryStub();
    }

}
