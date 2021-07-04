package pg.eti.ksg.Server.Service;


import org.springframework.web.bind.annotation.PathVariable;
import pg.eti.ksg.Server.Entities.User;
import pg.eti.ksg.Server.Model.ResponseMessageModel;

public interface IFriendsService {
    Iterable<User> getFriends(String userLogin);
    ResponseMessageModel deleteFriends(String userLogin,String friendLogin);
}
