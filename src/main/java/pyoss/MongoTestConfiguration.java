package pyoss;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@org.springframework.context.annotation.Configuration
@Profile("mongotest")
@EnableWebMvc
public class MongoTestConfiguration {

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), "test");
    }

    private Mongo mongo() {
        return new MongoClient("localhost");
    }
}
