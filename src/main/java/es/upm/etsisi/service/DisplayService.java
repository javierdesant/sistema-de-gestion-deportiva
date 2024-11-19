package es.upm.etsisi.service;

import es.upm.etsisi.models.*;
import es.upm.etsisi.utils.Message;

import java.util.Iterator;

public class DisplayService {       // FIXME: this class is ugly, we should delete it
    public static void show(Tournament tournament) {
        Message.FOOTER.writeln();

        System.out.println(tournament.getName());
        System.out.println(tournament.getStartDate() + " - " + tournament.getEndDate());
        System.out.println(tournament.getSport());
        System.out.println(tournament.getLeague());

        for (Match match : tournament.getMatches().getElements()) {
            show(match);
        }

        Message.FOOTER.writeln();
    }

    public static void show(Match match) {
        assert match != null;
        assert match.getParticipants().size() > 1;

        Iterator<Participant> iterator = match.getParticipants().iterator();

        Message.LIGHT_FOOTER.writeln();
        System.out.println(iterator.next());
        do {
            System.out.println("vs");
            System.out.println(iterator.next());
        } while (iterator.hasNext());
        Message.LIGHT_FOOTER.writeln();
    }

    public static void show(Participant participant) {
        System.out.println(participant.getName());

        Statistics stats = participant.getStats();
        for (Category category : Category.values()) {
            System.out.println(" - " + category + ": " + stats.get(category));
        }

        for (Participant child : participant.getChildren().getElements()) {
            show(child);
        }
    }
}
