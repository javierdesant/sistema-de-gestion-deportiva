package es.upm.etsisi.views;

import es.upm.etsisi.utils.Message;

public class MessageView extends View<Message>{

    public MessageView(){
        super();
    }

    @Override
    public void write(Message element) {
        element.write();
    }
}
