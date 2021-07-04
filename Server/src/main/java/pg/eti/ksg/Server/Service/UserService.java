package pg.eti.ksg.Server.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pg.eti.ksg.Server.Dao.IUserDao;
import pg.eti.ksg.Server.Model.LoginModel;
import pg.eti.ksg.Server.other.MessageCodes;
import pg.eti.ksg.Server.Model.RegisterModel;
import pg.eti.ksg.Server.Model.ResponseMessageModel;
import pg.eti.ksg.Server.Entities.User;

@Service
public class UserService implements IUserService{

    @Autowired
    IUserDao userDao;

    @Autowired
    Validation validation;


    @Override
    public Iterable<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUser(String login) {
        User user =  userDao.findByLogin(login);
        if(user == null)
            return null;
        return user;
    }

    @Override
    public ResponseMessageModel register(RegisterModel registerModel) {

        try {
            if(!validation.ValidEmail(registerModel.getEmail()) | !validation.ValidLogin(registerModel.getLogin()) | ! validation.ValidName(registerModel.getName()) |
                    ! validation.ValidPassword(registerModel.getPassword()) | !validation.ValidSurname(registerModel.getSurname()) ) {
                return new ResponseMessageModel(MessageCodes.INVALIDVALUES);
            }
            User user = userDao.findByLogin(registerModel.getLogin());
            if (user != null) {
                return new ResponseMessageModel(MessageCodes.INVALIDLOGIN);
            }
            user=userDao.findByEmail(registerModel.getEmail());
            if(user!= null) {
                return new ResponseMessageModel(MessageCodes.INVALIDEMAIL);
            }
            user = new User(registerModel);
            userDao.save(user);
            return new ResponseMessageModel(MessageCodes.OK);
        }catch (Exception e){
            return new ResponseMessageModel(MessageCodes.ERROR);//,e.getMessage());
        }
    }

    @Override
    public ResponseEntity login(LoginModel loginModel) {
        try {
            User user = userDao.findByLogin(loginModel.getLogin());
            if (user == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            if (user.checkPassword(loginModel.getPassword())) {
                if(loginModel.getToken()!=null) {
                    userDao.updateToken(loginModel.getToken(),user.Id());
                    return ResponseEntity.status(HttpStatus.OK).build();
                }
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseMessageModel logout(String login) {
        try {
            User user = userDao.findByLogin(login);
            if(user == null || user.Token() == null)
                return new ResponseMessageModel(MessageCodes.ERROR);
            if (user.Token().isEmpty())
                return new ResponseMessageModel(MessageCodes.OK);
            else
                userDao.updateToken("", user.Id());
            return new ResponseMessageModel(MessageCodes.OK);
        }catch(Exception e){
            return new ResponseMessageModel(MessageCodes.ERROR);
        }
    }

    @Override
    public ResponseMessageModel newToken(String login,String token) {
        return null;
    }


}
