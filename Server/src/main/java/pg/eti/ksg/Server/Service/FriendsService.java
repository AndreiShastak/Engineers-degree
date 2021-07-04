package pg.eti.ksg.Server.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.eti.ksg.Server.Dao.IFriendDao;
import pg.eti.ksg.Server.Dao.IUserDao;
import pg.eti.ksg.Server.Entities.FriendId;
import pg.eti.ksg.Server.Entities.User;
import pg.eti.ksg.Server.Model.ResponseMessageModel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class FriendsService implements IFriendsService{

    @Autowired
    private IFriendDao friendDao;

    @Autowired
    private IUserDao userDao;

    @Override
    public Iterable<User> getFriends(String userLogin) {
        User user = userDao.findByLogin(userLogin);
        if(user == null)
            return null;

        Iterable<FriendId> ids = friendDao.getFriendsId(user.Id());
        List<Long> friendsId = new LinkedList<>();
        for (FriendId id : ids) {
            if (id.getUser1().equals(user.Id()))
                friendsId.add(id.getUser2());
            else
                friendsId.add(id.getUser1());

        }
        return userDao.findAllById(friendsId);

    }

    @Override
    public ResponseMessageModel deleteFriends(String userLogin, String friendLogin) {
        return null;
    }
}
