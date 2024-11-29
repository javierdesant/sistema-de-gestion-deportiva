package es.upm.etsisi;

import es.upm.etsisi.models.Administrator;
import es.upm.etsisi.models.User;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.Random;
import java.text.Normalizer;

public class DefaultParameters {
    private static final int DEFAULT_PLAYERS = 6;
    private static final int DEFAULT_TEAMS = 2;
    private static final int DEFAULT_PASSWORD_LENGTH = 10;
    private static final String[] DEFAULT_SURNAMES = { "Pérez", "Sánchez", "Jiménez", "Torres", "Blanco", "Navarro",
            "Paredes", "Moreno" };
    private static final String[] DEFAULT_NAMES = { "Luisa", "Manuel", "Kurt", "Sofia", "Robert", "Jose", "Ramón",
            "Pablo" };
    private static final String[] DEFAULT_TEAMNAMES = { "Alfa", "Beta", "Delta", "Omega", "Theta" };

    public DefaultParameters() {
    }

    public static Administrator getDefaultAdmin() {
        return new Administrator("admin@upm.es", "admin");
    }

    public static LinkedList<LinkedList<String>> getDefaultParticipants() {
        LinkedList<LinkedList<String>> parameters = new LinkedList<>();
        int j = 0;
        for (int i = 0; i < DEFAULT_PLAYERS; i++) {
            LinkedList<String> playerParams;
            do {
                playerParams = generatePlayer();
            } while (parameters.contains(playerParams));
            parameters.add(playerParams);
            if (j < DEFAULT_TEAMS) {
                LinkedList<String> teamParams;
                do {
                    teamParams = generateTeam(playerParams.get(1));
                } while (!isValidTeam(parameters, teamParams));
                parameters.add(teamParams);
                j++;
            }
        }
        return parameters;
    }

    private static boolean isValidTeam(LinkedList<LinkedList<String>> parameters, LinkedList<String> teamParameters) {
        Iterator<LinkedList<String>> iterator = parameters.iterator();
        boolean result = true;
        while (iterator.hasNext() && result) {
            LinkedList<String> currentItem = iterator.next();
            if (!User.isUpmEmail(currentItem.get(0))) {
                if (currentItem.get(0) == teamParameters.get(0) || currentItem.get(1) == teamParameters.get(1)) {
                    result = false;
                }
            }
        }
        return result;
    }

    private static LinkedList<String> generatePlayer() {
        Random randomNumber = new Random(); // De momento las contraseñas son default para todo jugador, igual retoco
                                            // esto
        LinkedList<String> parameters = new LinkedList<>();
        String firstName = DEFAULT_NAMES[randomNumber.nextInt(DEFAULT_NAMES.length)];
        String lastName = DEFAULT_SURNAMES[randomNumber.nextInt(DEFAULT_SURNAMES.length)];
        parameters.add(createUserName(firstName, lastName));
        parameters.add(createPassword());
        parameters.add(firstName);
        parameters.add(lastName);

        return parameters;
    }

    private static LinkedList<String> generateTeam(String playerName) {
        Random randomNumber = new Random();
        LinkedList<String> parameters = new LinkedList<>();
        String teamName = DEFAULT_TEAMNAMES[randomNumber.nextInt(DEFAULT_TEAMNAMES.length)];
        parameters.add(teamName);
        parameters.add(playerName);

        return parameters;
    }

    private static String createUserName(String firstName, String lastName) {
        String username = String.join(".", normalize(firstName.toLowerCase()), normalize(lastName.toLowerCase()));
        return username.concat("@upm.es");
    }

    private static String createPassword(){
        Random randomNumber = new Random();
        StringBuilder password = new StringBuilder();
        int key = randomNumber.nextInt(256);

        for (int i = 0; i < DEFAULT_PASSWORD_LENGTH; i++) {
            char character = (char) (randomNumber.nextInt(26) + 'A');
            char shuffled = (char) (character ^ key);
            while (shuffled < 'A' || shuffled > 'Z') {
                shuffled = (char) ((shuffled ^ key) % ('Z' - 'A' + 1) + 'A');
            }
            password.append(shuffled);
        }
        return password.toString();
    }

    private static String normalize(String string) {
        String result = Normalizer.normalize(string, Normalizer.Form.NFD);
        return result.replaceAll("\\p{M}", "");
    }
}
