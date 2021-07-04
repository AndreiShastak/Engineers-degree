package pg.eti.ksg.Server.Entities;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FriendId implements Serializable {

    @Column(name="user1_id")
    private Long user1;

    @Column(name = "user2_id")
    private Long user2;

    public Long getUser1() {
        return user1;
    }

    public Long getUser2() {
        return user2;
    }

    public FriendId(Long user1, Long user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public FriendId(){}
}
