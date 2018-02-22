package pyoss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import pyoss.agenda.Agenda;

@Component
public class AgendaRepository {

    @Autowired
    private MongoTemplate mongoTemplate;


    public Agenda getFor(String ownerName) {
        Query ownerQuery = new Query().addCriteria(Criteria.where("ownerName").is(ownerName));
        return mongoTemplate.findOne(ownerQuery, Agenda.class, "agendas");
    }

    public void insert(Agenda agenda) {
        mongoTemplate.insert(agenda, "agendas");
    }
}
