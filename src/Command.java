import java.util.Scanner;

public class Command {
    private String name;
    private String[] arguments;
    private static final Scanner scanner = new Scanner(System.in);

    Command() {
        this.name = null;
        this.arguments = null;
    }
    public String getCommandName(){
        return this.name;
    }
    public String getArgument(int index){
        return this.arguments[index];
    }
    public int getArgumentsLength(){
        return this.arguments.length;    
    }
    public Command readCommand(){
        System.out.printf("> ");
        String[] splitCommand = scanner.nextLine().trim().split(" ");
        this.name = splitCommand[0];
        this.arguments = splitCommand.length > 1 ? splitCommand[1].split(";") : new String[0];
        return this;
    }
}
