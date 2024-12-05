package es.upm.etsisi.views;


public class MessageView extends View<Message> {

    public MessageView() {
        super();
    }

    @Override
    public void write(Message element) {
        element.write();
    }
}
