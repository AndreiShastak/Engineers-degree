package pg.eti.ksg.Server.Dao;

import org.springframework.data.repository.CrudRepository;
import pg.eti.ksg.Server.Entities.Message;

public interface IMessageDao extends CrudRepository<Message,Long> {
}
