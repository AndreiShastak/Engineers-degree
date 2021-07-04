package pg.eti.ksg.Server.Entities;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "invitations")
public class Invitations {

    @EmbeddedId
    private InvitationsId id;

    public Invitations(){}

    public Invitations(InvitationsId id) {
        this.id = id;
    }

    public InvitationsId getId() {
        return id;
    }
}
