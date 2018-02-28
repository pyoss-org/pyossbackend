package pyoss.agenda;

public interface AgendaRepository {

    Agenda getFor(String ownerName);

    void insert(Agenda agenda);

    void update(Agenda agenda);
}
