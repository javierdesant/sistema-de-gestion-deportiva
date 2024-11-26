package es.upm.etsisi;

import es.upm.etsisi.models.Administrator;
import es.upm.etsisi.models.Player;
import es.upm.etsisi.models.Team;
import es.upm.etsisi.models.DNI;
import java.text.Normalizer;
import java.util.Random;

public class DefaultParameters {
    private final int DEFAULT_PLAYERS = 6;
    private final int DEFAULT_TEAMS = 2;
    private final String[] DEFAULT_SURNAMES = { "Pérez", "Sánchez", "Jiménez", "Torres", "Blanco", "Navarro",
            "Paredes", "Moreno" };
    private final String[] DEFAULT_NAMES = { "Luisa", "Manuel", "Kurt", "Sofia", "Robert", "Jose", "Ramón",
            "Pablo" };
    private final String[] DEFAULT_TEAMNAMES = { "Alfa", "Beta", "Delta", "Omega", "Theta" };
    private final Administrator defaultAdmin;
    private final Player[] defaultPlayers;
    private final Team[] defaultTeams;

    public DefaultParameters() {
        this.defaultAdmin = new Administrator("admin@upm.es", "admin");
        this.defaultPlayers = generatePlayers();
        this.defaultTeams = generateTeams();
    }

    private DNI generateDNI() {
        Random randomNumber = new Random();
        String dniLetters = "TRWAGMYFPDXBNJZSQVHLCKE";
        int digits = 0;
        do {
            digits = randomNumber.nextInt(99999999);
        } while (digits == 0);

        String dniNumber = String.format("%08d", digits);
        return new DNI(dniNumber + dniLetters.charAt(digits % 23));
    }

    private Player[] generatePlayers() {
        Player[] players = new Player[DEFAULT_PLAYERS];
        Random randomNumber = new Random();
        String firstName, lastName, username; // De momento las contraseñas son default para todo jugador, igual retoco
                                              // esto

        for (int i = 0; i < players.length; i++) {
            firstName = DEFAULT_NAMES[randomNumber.nextInt(DEFAULT_NAMES.length)];
            lastName = DEFAULT_SURNAMES[randomNumber.nextInt(DEFAULT_SURNAMES.length)];

            username = String.join(".", Normalizer.normalize(firstName.toLowerCase(), Normalizer.Form.NFD),
                    Normalizer.normalize(lastName.toLowerCase(), Normalizer.Form.NFD));
            username = username.replaceAll("\\p{M}", "");
            username = username.concat("@upm.es");

            players[i] = new Player(username, "default", firstName, lastName, generateDNI(), this.defaultAdmin);
        }
        return players;
    }

    private Team[] generateTeams() {
        Team[] teams = new Team[DEFAULT_TEAMS];
        Random randomNumber = new Random();
        String teamName;

        for (int i = 0; i < teams.length; i++) {
            teamName = DEFAULT_TEAMNAMES[randomNumber.nextInt(DEFAULT_TEAMNAMES.length)];
            teams[i] = new Team(teamName, this.defaultAdmin,
                    this.defaultPlayers[randomNumber.nextInt(this.defaultPlayers.length)]);
        }
        return teams;
    }

    public Administrator getAdmin() {
        return this.defaultAdmin;
    }

    public Player[] getPlayers() {
        return this.defaultPlayers;
    }

    public Team[] getTeams() {
        return this.defaultTeams;
    }
}
