package pg.eti.ksg.Server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pg.eti.ksg.Server.Entities.User;
import pg.eti.ksg.Server.Model.ResponseMessageModel;
import pg.eti.ksg.Server.Service.IFriendsService;

@RestController
public class FriendsController {

    @Autowired
    private IFriendsService friendsService;


    @GetMapping("friends/{login}")
    public Iterable<User> getFriends(@PathVariable("login") String login)
    {
        return friendsService.getFriends(login);
    }

    @PostMapping("/friends/{userLogin}/delete/{friendLogin}")
    public ResponseMessageModel deleteFriends(@PathVariable("userLogin") String userLogin, @PathVariable("friendLogin") String friendLogin){
        return friendsService.deleteFriends(userLogin,friendLogin);
    }
}
