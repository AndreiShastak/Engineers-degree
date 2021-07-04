package pg.eti.ksg.Server.Service;

import org.springframework.http.ResponseEntity;
import pg.eti.ksg.Server.Model.LoginModel;
import pg.eti.ksg.Server.Model.RegisterModel;
import pg.eti.ksg.Server.Model.ResponseMessageModel;
import pg.eti.ksg.Server.Entities.User;

public interface IUserService {

    Iterable<User> getUsers();
    User getUser(String login);
    ResponseMessageModel register(RegisterModel registerModel);
    ResponseEntity login(LoginModel loginModel);
    ResponseMessageModel logout(String login);
    ResponseMessageModel newToken(String login,String token);

}
