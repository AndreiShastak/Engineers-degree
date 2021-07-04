package pg.eti.ksg.Server.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pg.eti.ksg.Server.Entities.UserRoute;
import pg.eti.ksg.Server.Entities.UserRouteId;

public interface IUserRouteDao extends CrudRepository<UserRoute, UserRouteId> {

    @Query("SELECT r.id.route FROM UserRoute r WHERE r.id.user = ?1")
    Iterable<Long> getFriendsRoutesId(Long userId);

    @Query("SELECT r.id.user FROM UserRoute r WHERE r.id.route = ?1")
    Iterable<Long> getRoutesFriendId(Long routeId);
}
