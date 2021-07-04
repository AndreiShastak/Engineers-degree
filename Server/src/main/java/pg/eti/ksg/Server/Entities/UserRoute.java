package pg.eti.ksg.Server.Entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="users_routes")
public class UserRoute {

    @EmbeddedId
    private UserRouteId id;

    public UserRoute(UserRouteId id) {
        this.id = id;
    }

    public UserRoute() {
    }

    public UserRouteId getId() {
        return id;
    }
}
