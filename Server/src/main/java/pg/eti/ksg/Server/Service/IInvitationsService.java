package pg.eti.ksg.Server.Service;

import pg.eti.ksg.Server.Entities.User;
import pg.eti.ksg.Server.Model.ResponseMessageModel;

import java.util.List;

public interface IInvitationsService {

    ResponseMessageModel invite(String inviting, String invited);

    Iterable<User> getInvitations(String userLogin);

    User acceptInvitation(String invited,String inviting);

    ResponseMessageModel dismissInvitation(String invited,String inviting);
}
