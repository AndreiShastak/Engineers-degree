package pg.eti.ksg.Server.Dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pg.eti.ksg.Server.Entities.User;

import javax.transaction.Transactional;

public interface IUserDao extends CrudRepository<User,Long> {

    User findByLogin(String login);
    User findByToken(String token);
    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query("Update User u SET u.token=?1 WHERE u.id=?2")
    void updateToken(String token,Long id);

}
