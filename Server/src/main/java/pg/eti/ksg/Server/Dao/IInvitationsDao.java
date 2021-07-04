package pg.eti.ksg.Server.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pg.eti.ksg.Server.Entities.Invitations;
import pg.eti.ksg.Server.Entities.InvitationsId;

import java.util.List;

public interface IInvitationsDao extends CrudRepository<Invitations, InvitationsId> {

    @Query("SELECT i.id.invitingId FROM Invitations i WHERE i.id.invitedId = ?1 ")
    Iterable<Long> getInvitingUsersId(Long id);
}
