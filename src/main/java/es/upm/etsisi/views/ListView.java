package es.upm.etsisi.views;

import java.util.Iterator;

import es.upm.etsisi.models.List;
import es.upm.etsisi.utils.Console;

abstract class ListView<T> extends View<T>{
    
    private Console console;

    protected ListView(){
        console = Console.getInstance();
    }
    
    protected void writeList(List<T> list){
        assert list != null;
        if(!list.isEmpty()){
            Iterator<T> iterator = list.iterator();
            while(iterator.hasNext()){
                this.write(iterator.next());
            }
        }else {console.writeln("La lista esta vacia.");}
        
    }
    
    public abstract void write(List<T> list);
}
