package pg.eti.ksg.Server.Entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserRouteId implements Serializable {

    @Column(name="user_id")
    private Long user;

    @Column(name = "route_id")
    private Long route;

    public UserRouteId() {
    }


    public UserRouteId(Long user, Long route) {
        this.user = user;
        this.route = route;
    }

    public Long getUser() {
        return user;
    }

    public Long getRoute() {
        return route;
    }
}
