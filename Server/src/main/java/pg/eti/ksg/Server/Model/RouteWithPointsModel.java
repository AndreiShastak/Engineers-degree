package pg.eti.ksg.Server.Model;

import pg.eti.ksg.Server.Entities.Points;

import java.util.List;

public class RouteWithPointsModel {
    private RoutesPhoneModel route;
    private Iterable<Points> points;

    public RouteWithPointsModel(RoutesPhoneModel route, Iterable<Points> points) {
        this.route = route;
        this.points = points;
    }

    public RouteWithPointsModel() {
    }

    public RoutesPhoneModel getRoute() {
        return route;
    }

    public Iterable<Points> getPoints() {
        return points;
    }

    public void setRoute(RoutesPhoneModel route) {
        this.route = route;
    }

    public void setPoints(Iterable<Points> points) {
        this.points = points;
    }
}
