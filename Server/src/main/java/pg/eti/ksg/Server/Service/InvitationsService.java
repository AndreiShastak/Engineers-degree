package pg.eti.ksg.Server.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.eti.ksg.Server.Dao.IFriendDao;
import pg.eti.ksg.Server.Dao.IInvitationsDao;
import pg.eti.ksg.Server.Dao.IUserDao;
import pg.eti.ksg.Server.Entities.*;
import pg.eti.ksg.Server.Model.ResponseMessageModel;
import pg.eti.ksg.Server.fcm.FirebaseService;
import pg.eti.ksg.Server.other.FirebaseCodes;
import pg.eti.ksg.Server.other.MessageCodes;


import java.util.Optional;

@Service
public class InvitationsService implements IInvitationsService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IInvitationsDao invitationsDao;

    @Autowired
    private IFriendDao friendDao;

    @Autowired
    private FirebaseService firebaseService;

    @Override
    public ResponseMessageModel invite(String inviting, String invited) {
        try {
            User userInvited = userDao.findByLogin(invited);
            if (userInvited == null)
                return new ResponseMessageModel(MessageCodes.INVALIDLOGIN);
            User userInviting = userDao.findByLogin(inviting);
            if (invitationsDao.existsById(new InvitationsId(userInviting.Id(), userInvited.Id())))
                return new ResponseMessageModel(MessageCodes.ERROR);
            if(friendDao.existsById(new FriendId(userInvited.Id(),userInviting.Id())) || friendDao.existsById(new FriendId(userInviting.Id(),userInvited.Id())))
                return new ResponseMessageModel(MessageCodes.INVALIDLOGIN);
            invitationsDao.save(new Invitations(new InvitationsId(userInviting.Id(), userInvited.Id())));

            if(userInvited.Token() != null && !userInvited.Token().isEmpty())
                firebaseService.sendDataAsync(userInvited.Token(),userInviting, FirebaseCodes.newInvitation);

            return new ResponseMessageModel(MessageCodes.OK);
        }catch (Exception e){
            return new ResponseMessageModel(MessageCodes.ERROR);
        }
    }

    @Override
    public Iterable<User> getInvitations(String userLogin) {
        User user = userDao.findByLogin(userLogin);
        if(user == null)
            return null;
        Iterable<Long> id = invitationsDao.getInvitingUsersId(user.Id());
        return userDao.findAllById(id);
    }

    @Override
    public User acceptInvitation(String invited, String inviting) {
        try {
            User invitedUser = userDao.findByLogin(invited);
            User invitingUser = userDao.findByLogin(inviting);
            if (invitedUser == null || invitingUser == null)
                return null;
            Optional<Invitations> invitation = invitationsDao.findById(new InvitationsId(invitingUser.Id(),invitedUser.Id()));
            if(!invitation.isPresent())
                return invitingUser;

            friendDao.save(new Friend(new FriendId(invitedUser.Id(), invitingUser.Id())));
            invitationsDao.delete(invitation.get());
            if(invitingUser.Token() != null && !invitingUser.Token().isEmpty())
                firebaseService.sendDataAsync(invitingUser.Token(),invitedUser, FirebaseCodes.newFriend);

            return invitingUser;
        }catch(Exception e){
            return null;
        }

    }

    @Override
    public ResponseMessageModel dismissInvitation(String invited, String inviting) {
        try {
            User invitedUser = userDao.findByLogin(invited);
            User invitingUser = userDao.findByLogin(inviting);
            if (invitedUser == null || invitingUser == null)
                return null;
            Optional<Invitations> invitation = invitationsDao.findById(new InvitationsId(invitingUser.Id(),invitedUser.Id()));
            if(!invitation.isPresent())
                return new ResponseMessageModel(MessageCodes.OK);

            invitationsDao.delete(invitation.get());
            return new ResponseMessageModel(MessageCodes.OK);
        }catch(Exception e){
            return null;
        }
    }
}
