package pg.eti.ksg.Server.Threads;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.apache.commons.lang3.text.translate.NumericEntityUnescaper;
import pg.eti.ksg.Server.Dao.IFriendDao;
import pg.eti.ksg.Server.Dao.IUserDao;
import pg.eti.ksg.Server.Dao.IUserRouteDao;
import pg.eti.ksg.Server.Entities.*;
import pg.eti.ksg.Server.fcm.FirebaseService;
import pg.eti.ksg.Server.other.FirebaseCodes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class StartDangerThread extends Thread {

    private IFriendDao friendDao;
    private IUserRouteDao userRouteDao;
    private IUserDao userDao;
    private Long id;
    private SharedRoute route;
    private Points point;

    public StartDangerThread(IFriendDao friendDao,IUserDao userDao,IUserRouteDao userRouteDao,Long userId,SharedRoute route, Points point){
        this.friendDao=friendDao;
        this.userRouteDao=userRouteDao;
        this.route=route;
        this.point = point;
        this.id=userId;
        this.userDao=userDao;
    }

    @Override
    public void run() {
        Iterable<FriendId> ids = friendDao.getFriendsId(id);
        List<Long> friends = new LinkedList<>();
        for(FriendId friendId:ids){
            if(friendId.getUser1().longValue() == id.longValue())
                friends.add(friendId.getUser2());
            else
                friends.add(friendId.getUser1());
        }
        for(Long friendId: friends){
            userRouteDao.save(new UserRoute(new UserRouteId(friendId,route.getId())));
        }

        //send firebase
        List<String> tokens = new LinkedList<>();
        Iterable<User> users = userDao.findAllById(friends);
        for(User user:users) {
            if (user.Token() != null && !user.Token().isEmpty()) {
                tokens.add(user.Token());
            }
        }

        if(!tokens.isEmpty()) {
            FirebaseService firebaseService = new FirebaseService();
            try {
                firebaseService.sendDataAsync(tokens, route, point, FirebaseCodes.startDangerRoute);
            } catch (FirebaseMessagingException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
