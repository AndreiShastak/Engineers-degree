package pg.eti.ksg.Server.Entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="Shared_routes")
public class SharedRoute implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="is_danger")
    private boolean danger;

    @Column(name="start_date")
    private LocalDateTime startDate;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public SharedRoute(User user, boolean danger,LocalDateTime startDate) {
        this.user = user;
        this.danger = danger;
        this.startDate =startDate;
    }

    public SharedRoute(){}

    public void setUser(User user) {
        this.user = user;
    }

    public void setDanger(boolean danger) {
        this.danger = danger;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public boolean getDanger() {
        return danger;
    }

    @Override
    public Map<String, String> ObjectMap() {
        HashMap<String,String> map =new HashMap<String, String>();
        map.put("id",String.valueOf(id));
        map.put("login",user.getLogin());
        map.put("danger",String.valueOf(danger));
        map.put("dateRoute", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(startDate));

        return map;
    }

}
