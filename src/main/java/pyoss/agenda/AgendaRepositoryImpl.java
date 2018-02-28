package pyoss.agenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class AgendaRepositoryImpl implements AgendaRepository{

    private static final String COLLECTION_NAME = "agendas";

    @Autowired
    private MongoTemplate mongoTemplate;


    public Agenda getFor(String ownerName) {
        Query ownerQuery = new Query().addCriteria(Criteria.where("ownerName").is(ownerName));
        return mongoTemplate.findOne(ownerQuery, Agenda.class, COLLECTION_NAME);
    }

    public void insert(Agenda agenda) {
        mongoTemplate.insert(agenda, COLLECTION_NAME);
    }

    public void update(Agenda agenda) {
        mongoTemplate.save(agenda, COLLECTION_NAME);
    }
}
