package pg.eti.ksg.Server.Dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pg.eti.ksg.Server.Entities.SharedRoute;
import pg.eti.ksg.Server.Entities.User;

import javax.transaction.Transactional;

public interface ISharedRouteDao extends CrudRepository<SharedRoute,Long> {

    //@Query("SELECT u FROM SharedRoute u WHERE u.user = ?1 and u.active = ?2")
   // SharedRoute getSharedRoute(User user, boolean active);

    //@Transactional
   // @Modifying
    //@Query("Update SharedRoute u SET u.active=false WHERE u.id=?1")
    //void update(Long id);
    @Query("SELECT r FROM SharedRoute r WHERE r.user =?1")
    Iterable<SharedRoute> getMyRoutes(User user);
}
