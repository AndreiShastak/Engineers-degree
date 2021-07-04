package pg.eti.ksg.Server.Entities;

import pg.eti.ksg.Server.Model.RegisterModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "Users")
public class User implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login;

    @Column(name = "user_name")
    private String name;
    private String surname;
    private String city;
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "date_of_birth")
    private LocalDate birth;
    private String token;

    public User(){}

    public User(String login,String name,String surname, String email, String password){
        this.login=login;
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.password=password;
    }

    public User(RegisterModel model)
    {
        this.login=model.getLogin();
        this.name= model.getName();
        this.surname=model.getSurname();
        this.email=model.getEmail();
        this.password=model.getPassword();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //public void setPassword(String password) {
    //    this.password = password;
    //}

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public String Token() {
        return token;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long Id() {
        return id;
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }


    public Map<String,String> ObjectMap()
    {
        Map<String,String> map =new HashMap<String, String>();
        map.put("login",login);
        map.put("name",name);
        map.put("surname",surname);
        if(city!=null)
            map.put("city",city);
        else
            map.put("city","null");

        map.put("email",email);
        if(birth!=null)
            map.put("birth",birth.toString());
        else
            map.put("birth","null");

        return map;

    }
}
