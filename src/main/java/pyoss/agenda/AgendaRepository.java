package pyoss.agenda;

import java.util.Optional;

public interface AgendaRepository {

    Optional<Agenda> getFor(String ownerName);

    void insert(Agenda agenda);

    void update(Agenda agenda);
}
