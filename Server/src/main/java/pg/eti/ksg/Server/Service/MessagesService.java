package pg.eti.ksg.Server.Service;

import org.springframework.stereotype.Service;
import pg.eti.ksg.Server.Entities.Message;
import pg.eti.ksg.Server.Model.ResponseMessageModel;

@Service
public class MessagesService implements IMessagesService{
    @Override
    public Iterable<Message> getMessages(String login) {
        return null;
    }

    @Override
    public ResponseMessageModel sendMessage(String login, Message message) {
        return null;
    }
}
