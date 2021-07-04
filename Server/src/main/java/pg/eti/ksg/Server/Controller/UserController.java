package pg.eti.ksg.Server.Controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pg.eti.ksg.Server.Model.LoginModel;
import pg.eti.ksg.Server.Model.RegisterModel;
import pg.eti.ksg.Server.Model.ResponseMessageModel;
import pg.eti.ksg.Server.Entities.User;
import pg.eti.ksg.Server.Service.IUserService;
import pg.eti.ksg.Server.fcm.FirebaseService;
import pg.eti.ksg.Server.other.MessageCodes;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user")
    public @ResponseBody Iterable<User> getUsers()
    {
        return userService.getUsers();
    }

    @GetMapping("/user/{login}")
    public @ResponseBody
    User getUsers(@PathVariable("login") String login)
    {
        return userService.getUser(login);
    }

    @PostMapping("/user/register")
    public @ResponseBody ResponseMessageModel register(@RequestBody RegisterModel registerModel)
    {
        return userService.register(registerModel);
    }

    @PostMapping("/user/login")
    public @ResponseBody ResponseEntity login(@RequestBody LoginModel loginModel)
    {
        return userService.login(loginModel);
    }

    @PostMapping("/user/logout/{login}")
    public @ResponseBody ResponseMessageModel logout(@PathVariable("login") String login)
    {
        return userService.logout(login);
    }

    @PostMapping("/user/new/token/{login}")
    public ResponseMessageModel newToken(@PathVariable("login") String login, @RequestBody String token){
        return userService.newToken(login,token);
    }

}
