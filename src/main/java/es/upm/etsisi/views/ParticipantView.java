package es.upm.etsisi.views;


import es.upm.etsisi.utils.Console;
import es.upm.etsisi.models.Participant;
import es.upm.etsisi.models.Statistics;
import es.upm.etsisi.models.Category;
public class ParticipantView extends View<Participant> {

    private ParticipantView(){
        super()
    }

    @Override
    public void write(Participant element) {
        //comprobar si llega equipo o jugador
        Console.getInstance().writeln("     JUGADOR     ");
        Console.getInstance().writeln(element.getName());
        Statistics statistics = element.getStats();
        Console.getInstance().writeln("     ESTADISTICAS     ");
        Console.getInstance().write("----------------------");
        for(Category category : Category.values()){

            Console.getInstance().write(category.name() + ": ");
            Console.getInstance().writeln(statistics.get(category));
        }
        Console.getInstance().writeln("----------------------");
        
    }
}
