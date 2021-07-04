package pg.eti.ksg.Server.Service;

import org.springframework.stereotype.Service;
import pg.eti.ksg.Server.other.ValidatePatterns;

@Service
public class Validation {
    public boolean ValidLogin(String login)
    {
        if(login.isEmpty())
            return false;
        else if(!ValidatePatterns.LOGIN_PATTERN.matcher(login).matches())
            return false;
        else
            return true;
    }

    public boolean ValidName(String name)
    {
        if(name.isEmpty())
            return false;
        else if(!ValidatePatterns.NAME_PATTERN.matcher(name).matches())
            return false;
        else
            return true;
    }

    public boolean ValidSurname(String surname)
    {
        if(surname.isEmpty())
            return false;
        else if(!ValidatePatterns.NAME_PATTERN.matcher(surname).matches())
            return false;
        else
            return true;
    }

    public boolean ValidEmail(String email)
    {
        if(email.isEmpty())
            return false;
        else if(!ValidatePatterns.EMAIL_PATTERN.matcher(email).matches())
            return false;
        else
            return true;
    }

    public boolean ValidPassword(String password)
    {
        if(password.isEmpty())
            return false;
        else if(!ValidatePatterns.PASSWORD_PATTERN.matcher(password).matches())
            return false;
        else
            return true;
    }
}
