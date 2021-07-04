package pg.eti.ksg.Server.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.eti.ksg.Server.Dao.*;
import pg.eti.ksg.Server.Entities.Points;
import pg.eti.ksg.Server.Entities.SharedRoute;
import pg.eti.ksg.Server.Entities.User;
import pg.eti.ksg.Server.Model.*;
import pg.eti.ksg.Server.Threads.NewPointThread;
import pg.eti.ksg.Server.Threads.StartDangerThread;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RoutesService implements IRoutesService{

    @Autowired
    private ISharedRouteDao sharedRouteDao;

    @Autowired
    private IPointDao pointDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IUserRouteDao userRouteDao;

    @Autowired
    private IFriendDao friendDao;

    @Override
    public IdsModel addDangerPoint(Points point,String login) {
        try {
            SharedRoute route;

            Optional<SharedRoute> optionalRoute = sharedRouteDao.findById(point.getRouteId());
            if(!optionalRoute.isPresent())
                return new IdsModel((long)-1,(long)-1);
            else
                route = optionalRoute.get();

            if(!route.getUser().getLogin().equals(login))
                return new IdsModel((long)-1,(long)-1);

            point.setRouteId(route);
            pointDao.save(point);
            new NewPointThread(userDao,userRouteDao,point).run();
            return new IdsModel(point.getId());
        }catch (Exception e) {
            return new IdsModel((long)-1,(long)-1);
        }
    }

    public IdsModel startDanger(Points point, String login)
    {
        try {
            User user = userDao.findByLogin(login);
            if (user == null)
                return new IdsModel((long)-1,(long)-1);
            SharedRoute route = new SharedRoute(user, true,point.getDate());
            sharedRouteDao.save(route);
            point.setRouteId(route);
            pointDao.save(point);

            new StartDangerThread(friendDao,userDao,userRouteDao,user.Id(), route,point).run();
            //send to friends
            return new IdsModel(route.getId(),point.getId());
        }catch (Exception e){
            return new IdsModel((long)-1,(long)-1);
        }
    }

    @Override
    public Iterable<RouteWithPointsModel> getMyRoutes(String login) {
        try {
            User user = userDao.findByLogin(login);
            if(user == null)
                return null;

            Iterable<SharedRoute> myRoutes = sharedRouteDao.getMyRoutes(user);
           /// List<RoutesPhoneModel> routesPhone =new LinkedList<>();
            List<RouteWithPointsModel> routes =new LinkedList<>();
            for(SharedRoute route:myRoutes){
                RoutesPhoneModel model = new RoutesPhoneModel(route.getId(),user.getLogin(),route.getDanger(),route.getStartDate());
                //routesPhone.add(model);
                routes.add(new RouteWithPointsModel(model,pointDao.getRoutePoints(route)));
            }

            //return routesPhone;
            return routes;

        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Iterable<RouteWithPointsModel> getFriendsRoutes(String login) {
        try {
            User user = userDao.findByLogin(login);
            if(user == null)
                return null;

            Iterable<Long> routesId = userRouteDao.getFriendsRoutesId(user.Id());

            Iterable<SharedRoute> friendsRoutes = sharedRouteDao.findAllById(routesId);
            //List<RoutesPhoneModel> routesPhone =new LinkedList<>();
            List<RouteWithPointsModel> routes =new LinkedList<>();
            for(SharedRoute route:friendsRoutes){
                RoutesPhoneModel model = new RoutesPhoneModel(route.getId(),route.getUser().getLogin(),route.getDanger(),route.getStartDate());
                routes.add(new RouteWithPointsModel(model,pointDao.getRoutePoints(route)));
            }

            return routes;

        }catch (Exception e){
            return null;
        }
    }
}
