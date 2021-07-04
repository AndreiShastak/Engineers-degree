package pg.eti.ksg.Server.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pg.eti.ksg.Server.Entities.Friend;
import pg.eti.ksg.Server.Entities.FriendId;

public interface IFriendDao extends CrudRepository<Friend, FriendId> {

    @Query("SELECT f.id FROM Friend f WHERE f.id.user1 = ?1 OR f.id.user2 = ?1")
    Iterable<FriendId> getFriendsId(Long id);

}
