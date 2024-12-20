package es.upm.etsisi.service;

public enum ErrorType {
    DUPLICATE_ELEMENT_ERROR,
    DUPLICATE_TOURNAMENT_ERROR,
    DUPLICATE_PLAYER_ERROR,
    DUPLICATE_TEAM_ERROR,
    DUPLICATE_MATCH_ERROR,
    PLAYER_ALREADY_IN_MATCH_ERROR,
    PARTICIPANT_ALREADY_ASSIGNED_ERROR,
    ELEMENT_NOT_FOUND,
    NAME_FORMAT_ERROR,
    DATE_FORMAT_ERROR,
    INVALID_DNI_ERROR,
    PLAYER_NOT_IN_TEAM,
    USER_NOT_FOUND,
    PARTICIPANT_NOT_FOUND,
    PLAYER_NOT_FOUND,
    TEAM_NOT_FOUND,
    TOURNAMENT_NOT_FOUND,
    WRONG_PASSWORD,
    PLAYER_IN_GAME_ERROR,
    TOURNAMENT_NOT_ACTIVE,
    PARTICIPANT_NOT_ENROLLED,
    TEAM_IN_GAME_ERROR,
    PLAYER_ALREADY_IN_TEAM_ERROR,
    INVALID_COMMAND,
    INVALID_ARGUMENTS,
    INVALID_CODE,
    INVALID_TIME_FRAME,
    INVALID_MATCH,
    INVALID_EMAIL,
    MORE_PLAYERS_NEEDED,
    PLAYER_IN_ANOTHER_TEAM,

    NULL;

    public boolean isNull() {
        return this == NULL;
    }
}
