package pg.eti.ksg.Server.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.eti.ksg.Server.Dao.IUserDao;
import pg.eti.ksg.Server.Entities.IEntity;
import pg.eti.ksg.Server.Entities.User;

import java.util.List;

@Service
public class FirebaseService {

    public void sendDataAsync(String token, IEntity entity,int messageType) throws FirebaseMessagingException{
        Message message = Message.builder()
                .setToken(token)
                .putData("type",String.valueOf(messageType))
                .putAllData(entity.ObjectMap())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }

    public void sendDataAsync(List<String> token, IEntity entity, int messageType) throws FirebaseMessagingException{
        MulticastMessage message = MulticastMessage.builder()
                .putData("type",String.valueOf(messageType))
                .putAllData(entity.ObjectMap())
                .addAllTokens(token)
                .build();

        FirebaseMessaging.getInstance().sendMulticastAsync(message);
    }

    public void sendDataAsync(List<String> token, IEntity firstEntity,IEntity secondEntity, int messageType) throws FirebaseMessagingException{
        MulticastMessage message = MulticastMessage.builder()
                .putData("type",String.valueOf(messageType))
                .putAllData(firstEntity.ObjectMap())
                .putAllData(secondEntity.ObjectMap())
                .addAllTokens(token)
                .build();

        FirebaseMessaging.getInstance().sendMulticastAsync(message);
    }
}
