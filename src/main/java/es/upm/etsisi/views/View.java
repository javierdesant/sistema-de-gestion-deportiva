package es.upm.etsisi.views;

import es.upm.etsisi.utils.Console;

public abstract class View<T>{
    
    private  Console console;
    
    protected View(){
        this.console = Console.getInstance();
    }
    protected void writeConsole(String string){
        console.write(string);
    }
    protected void writeConsole(double num){
        console.write(num);
    }
    protected void writelnConsole(String string){
        console.writeln(string);
    }
    protected void writelnConsole(double num){
        console.writeln(num);
    }
    protected abstract void write(T element);
}
    
