package es.upm.etsisi;

public interface Item {
    boolean isCalled();

    void validate(String input);

    void execute();
}
