package es.upm.etsisi.models.game;

import es.upm.etsisi.models.entities.Participant;
import es.upm.etsisi.models.entities.ParticipantList;
import es.upm.etsisi.service.List;
import es.upm.etsisi.utils.Message;

import java.util.Iterator;

public class MatchList extends List<Match> {

    public MatchList() {
        super();
    }

    @Override
    public void add(Match match) {
        assert this.isValidMatch(match) : Message.PLAYERS_MATCHED_ERROR;    // TODO: update messages

        this.addElement(match);
    }

    @Override
    public void remove(Match match) {
        boolean removed = this.removeElement(match);
        assert removed; // TODO: add message
    }

    public void remove(String name) {
        for (Match match : this.getElements()) {
            for (Participant participant : match.getEntities()) {
                if (participant.getName().equals(name))
                    this.remove(match);
            }
        }
    }

    public boolean contains(String name) {
        boolean found = false;

        Iterator<Match> iterator = this.getElements().iterator();
        while (iterator.hasNext() && !found) {
            found = iterator.next().contains(name);
        }

        return found;
    }

    public boolean contains(Participant participant) {
        boolean found = false;

        Iterator<Match> iterator = this.getElements().iterator();
        while (iterator.hasNext() && !found) {
            found = iterator.next().contains(participant);
        }

        return found;
    }

    private boolean isValidMatch(Match match) {
        boolean isValid = !this.contains(match);

        Iterator<Participant> iterator = match.getEntities().iterator();
        while (iterator.hasNext() && isValid) {
            isValid = !this.contains(iterator.next());
        }

        return isValid;
    }

    public void randomize(ParticipantList participantList) {
        // TODO
//        assert !entityList.isEmpty() : Message.NO_PLAYERS;
//        assert this.isEmpty() : Message.NO_MATCHES;
//
//        LinkedList<Entity> randomEntities = new LinkedList<>(entityList.getEntities());
//        assert randomEntities.size() % 2 == 0 : Message.EVEN_PLAYERS_REQUIRED;
//
//        Collections.shuffle(randomEntities);
//        while (!randomEntities.isEmpty()) {
//            this.add(new Match(entityList, randomEntities.pop(), randomEntities.pop()));
//        }
    }
}
