package es.upm.etsisi.views;

import java.util.Iterator;

import es.upm.etsisi.models.List;
import es.upm.etsisi.utils.Console;

abstract class ListView<T>{

    
    protected void writeList(List<T> list){
        assert list != null;
        if(!list.isEmpty()){
            Iterator<T> iterator = list.iterator();
            while(iterator.hasNext()){
                this.writeElement(iterator.next());
            }
        }else Console.getInstance().writeln("La lista esta vacia.");
        
    }
    
    protected abstract void writeElement(T elemenT);
    public abstract void write(List<T> list);

}
