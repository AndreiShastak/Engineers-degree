package pg.eti.ksg.Server.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pg.eti.ksg.Server.Entities.Points;
import pg.eti.ksg.Server.Entities.SharedRoute;
import pg.eti.ksg.Server.Entities.User;

public interface IPointDao extends CrudRepository<Points,Long> {

    @Query("SELECT p FROM Points p WHERE p.routeId = ?1")
    Iterable<Points> getRoutePoints(SharedRoute route);
}
