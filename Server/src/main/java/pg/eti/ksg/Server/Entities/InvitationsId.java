package pg.eti.ksg.Server.Entities;

import javax.persistence.Column;
import java.io.Serializable;

public class InvitationsId implements Serializable {

    @Column(name = "inviting_id")
    private Long invitingId;

    @Column(name = "invited_id")
    private Long invitedId;

    public InvitationsId(Long invitingId, Long invitedId) {
        this.invitingId = invitingId;
        this.invitedId = invitedId;
    }

    public InvitationsId() {
    }

    public Long getInvitingId() {
        return invitingId;
    }

    public Long getInvitedId() {
        return invitedId;
    }
}
