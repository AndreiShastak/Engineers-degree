package pg.eti.ksg.Server.Service;

import pg.eti.ksg.Server.Entities.Points;
import pg.eti.ksg.Server.Entities.SharedRoute;
import pg.eti.ksg.Server.Model.*;

public interface IRoutesService {
    IdsModel addDangerPoint(Points point, String login);
    IdsModel startDanger(Points point, String login);
    Iterable<RouteWithPointsModel> getMyRoutes(String login);
    Iterable<RouteWithPointsModel> getFriendsRoutes(String login);
}
