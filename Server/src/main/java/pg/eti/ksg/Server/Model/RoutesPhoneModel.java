package pg.eti.ksg.Server.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.JoinColumn;
import java.time.LocalDateTime;

public class RoutesPhoneModel {

    private Long id;

    private String friendLogin;

    private boolean isDangerous;

    private LocalDateTime startDate;

    public RoutesPhoneModel(Long id, String friendLogin, boolean isDangerous, LocalDateTime startDate) {
        this.id = id;
        this.friendLogin = friendLogin;
        this.isDangerous = isDangerous;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public String getFriendLogin() {
        return friendLogin;
    }

    public boolean getIsDangerous() {
        return isDangerous;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getStartDate() {
        return startDate;
    }
}
