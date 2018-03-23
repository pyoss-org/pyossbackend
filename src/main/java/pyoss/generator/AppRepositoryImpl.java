package pyoss.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AppRepositoryImpl implements AppRepository {

    private static final String COLLECTION_NAME = "apps";

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(App app) {
        mongoTemplate.insert(app, COLLECTION_NAME);
    }

    @Override
    public Optional<App> findByOwner(String ownerName) {
        Query ownerQuery = new Query().addCriteria(Criteria.where("ownerName").is(ownerName));
        return Optional.ofNullable(mongoTemplate.findOne(ownerQuery, App.class, COLLECTION_NAME));
    }
}
