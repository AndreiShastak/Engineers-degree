package pg.eti.ksg.Server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pg.eti.ksg.Server.Entities.User;
import pg.eti.ksg.Server.Model.ResponseMessageModel;
import pg.eti.ksg.Server.Service.IInvitationsService;

import java.util.List;

@RestController
public class InvitationsController {

    @Autowired
    private IInvitationsService invitationsService;

    @PostMapping("invitations/{userLogin}/invite/{invitationLogin}")
    public ResponseMessageModel invite(@PathVariable("userLogin") String userLogin, @PathVariable("invitationLogin") String invitationLogin){
        return invitationsService.invite(userLogin,invitationLogin);
    }

    @GetMapping("invitations/{login}")
    public Iterable<User> getInvitations(@PathVariable("login") String login)
    {
        return invitationsService.getInvitations(login);
    }

    @PostMapping("invitations/{userLogin}/accept/{friendLogin}")
    User acceptInvitation(@PathVariable("userLogin") String userLogin, @PathVariable("friendLogin") String friendLogin){
        return invitationsService.acceptInvitation(userLogin,friendLogin);
    }

    @PostMapping("invitations/{userLogin}/dismiss/{invitationLogin}")
    ResponseMessageModel dismissInvitation(@PathVariable("userLogin") String userLogin, @PathVariable("invitationLogin") String invitationLogin){
        return invitationsService.dismissInvitation(userLogin,invitationLogin);
    }


}
