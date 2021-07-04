package pg.eti.ksg.Server.Model;

public class IdsModel {

    private Long routeId;
    private Long pointId;

    public IdsModel(Long routeId, Long pointId) {
        this.routeId = routeId;
        this.pointId = pointId;
    }

    public IdsModel(Long pointId) {
        this.pointId = pointId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public Long getPointId() {
        return pointId;
    }
}
