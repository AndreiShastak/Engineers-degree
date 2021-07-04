package pg.eti.ksg.Server.Entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Friends")
public class Friend {

    @EmbeddedId
    private FriendId id;

    public FriendId getId() {
        return id;
    }

    public Friend(FriendId id) {
        this.id = id;
    }

    public Friend() {
    }
}
