package pg.eti.ksg.Server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pg.eti.ksg.Server.Entities.Points;
import pg.eti.ksg.Server.Entities.SharedRoute;
import pg.eti.ksg.Server.Model.*;
import pg.eti.ksg.Server.Service.IRoutesService;

@RestController
public class RoutesController {

    @Autowired
    private IRoutesService routesService;

    @PostMapping("/danger/{login}")
    public IdsModel danger(@RequestBody Points point, @PathVariable("login") String login){
        return routesService.addDangerPoint(point,login);
    }

    @PostMapping("/danger/{login}/start")
    public IdsModel dangerStart(@RequestBody Points point, @PathVariable("login") String login){
        return routesService.startDanger(point,login);
    }

    @GetMapping("/routes/my/{login}")
    public Iterable<RouteWithPointsModel> getMyRoutes(@PathVariable("login") String login)
    {
        return routesService.getMyRoutes(login);
    }

    @GetMapping("/routes/friends/{login}")
    public Iterable<RouteWithPointsModel> getFriendsRoutes(@PathVariable("login") String login)
    {
        return routesService.getFriendsRoutes(login);
    }
}
