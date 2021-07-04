package pg.eti.ksg.Server.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Points implements IEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="route_id")
    private SharedRoute routeId;

    private float lat;
    private float lng;

    @Column(name="point_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    public Points(){};
    public Points(SharedRoute route, float lat, float lng, LocalDateTime date) {
        this.routeId = route;
        this.lat = lat;
        this.lng = lng;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Long getRouteId() {
        return routeId.getId();
    }

    private SharedRoute Route(){
        return routeId;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setRouteId(SharedRoute routeId) {
        this.routeId = routeId;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public Map<String, String> ObjectMap() {
        HashMap<String,String> map =new HashMap<String, String>();
        map.put("pointId",String.valueOf(id));
        map.put("routeId",String.valueOf(routeId.getId()));
        map.put("lat",String.valueOf(lat));
        map.put("lng",String.valueOf(lng));
        map.put("datePoint", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(date));

        return map;
    }

    @JsonProperty("routeId")
    private void unpackNested(Long id) {
        this.routeId = new SharedRoute();
        routeId.setId(id);
    }
}
