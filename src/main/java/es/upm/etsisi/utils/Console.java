package es.upm.etsisi.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Console {
    private static Console instance;
    private final BufferedReader bufferedReader;

    private Console() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static Console getInstance() {
        if (instance == null) {
            instance = new Console();
        }
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
    public void write(double num){
        System.out.print(num);
    }
    public void writeln() {
        System.out.println();
    }

    public void writeln(String string) {
        this.write(string);
        this.writeln();
    }

    public void writeln(double num){
        this.write(num);
        this.writeln();
    }
}