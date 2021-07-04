package pg.eti.ksg.Server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pg.eti.ksg.Server.Entities.Message;
import pg.eti.ksg.Server.Entities.User;
import pg.eti.ksg.Server.Model.ResponseMessageModel;
import pg.eti.ksg.Server.Service.IMessagesService;

@Controller
public class MessagesController {

    @Autowired
    private IMessagesService messagesService;

    @GetMapping("/messages/get/{login}")
    public Iterable<Message> getMessages(@PathVariable("login") String login){
        return messagesService.getMessages(login);
    }

    @PostMapping("/messages/send/{login}")
    public ResponseMessageModel sendMessage(@PathVariable("login") String login,@RequestBody Message message)
    {
        return messagesService.sendMessage(login,message);
    }
}
