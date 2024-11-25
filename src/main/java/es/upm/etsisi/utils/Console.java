package es.upm.etsisi.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Console {
    private static final Console instance = new Console();
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private Console() {
    }

    public static Console getInstance() {
        return instance;
    }

    public String readString(String title) {
        String input = null;
        this.write(title);
        try {
            input = this.bufferedReader.readLine();
        } catch (Exception ignored) {
        }
        return input;
    }

    public String readString() {
        return this.readString("");
    }

    public void write(String string) {
        System.out.print(string);
    }

    public void writeln() {
        System.out.println();
    }

    public void writeln(String string) {
        this.write(string);
        this.writeln();
    }
}