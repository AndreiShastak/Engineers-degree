package pg.eti.ksg.Server.Entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="Messages")
public class Message implements IEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name="receiver_id")
    private User receiver;

    private String message;
    @Column(name="date_send")
    private LocalDateTime date;

    public Message(User sender, User receiver, String message, LocalDateTime date) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.date = date;
    }
    public Message(){}

    public Long getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public Map<String, String> ObjectMap() {
        HashMap<String,String> map =new HashMap<String, String>();
        map.put("sender",sender.getLogin());
        map.put("receiver",receiver.getLogin());
        map.put("message",message);
        map.put("date",date.toString());

        return map;
    }
}
