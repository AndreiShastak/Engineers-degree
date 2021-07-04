package pg.eti.ksg.Server.Service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pg.eti.ksg.Server.Entities.Message;
import pg.eti.ksg.Server.Model.ResponseMessageModel;

public interface IMessagesService {

    Iterable<Message> getMessages(String login);

    ResponseMessageModel sendMessage(String login,Message message);
}
