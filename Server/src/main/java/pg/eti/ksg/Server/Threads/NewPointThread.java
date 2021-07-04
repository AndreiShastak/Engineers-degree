package pg.eti.ksg.Server.Threads;

import com.google.firebase.messaging.FirebaseMessagingException;
import pg.eti.ksg.Server.Dao.IUserDao;
import pg.eti.ksg.Server.Dao.IUserRouteDao;
import pg.eti.ksg.Server.Entities.*;
import pg.eti.ksg.Server.fcm.FirebaseService;
import pg.eti.ksg.Server.other.FirebaseCodes;

import java.util.LinkedList;
import java.util.List;

public class NewPointThread extends Thread {

    private IUserRouteDao userRouteDao;
    private IUserDao userDao;
    private Points point;

    public NewPointThread(IUserDao userDao,IUserRouteDao userRouteDao,Points point){
        this.userRouteDao=userRouteDao;
        this.point = point;
        this.userDao=userDao;
    }

    @Override
    public void run() {

        Iterable<Long> ids =  userRouteDao.getRoutesFriendId(point.getRouteId());
        //send firebase
        List<String> tokens = new LinkedList<>();
        Iterable<User> users = userDao.findAllById(ids);
        for(User user:users) {
            if (user.Token() != null && !user.Token().isEmpty()) {
                tokens.add(user.Token());
            }
        }

        if(!tokens.isEmpty()) {
            FirebaseService firebaseService = new FirebaseService();
            try {
                firebaseService.sendDataAsync(tokens, point, FirebaseCodes.newPoint);
            } catch (FirebaseMessagingException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
