package pg.eti.ksg.Server.other;

import java.util.regex.Pattern;

public class ValidatePatterns {
    public static final Pattern NAME_PATTERN =
            Pattern.compile("^[a-zA-Z]{3,}$");

    public static final Pattern LOGIN_PATTERN =
            Pattern.compile("^[a-zA-Z0-9_\\-]{3,}$");

    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^"+
                    "(?=.*[0-9])"+  //przynajmniej jedna cyfra
                    "(?=.*[a-z])"+  //przynajmniej jedna mala litera
                    "(?=.*[A-Z])"+  //przynajmniej jedna duza litera
                    "(?=.*[@#$%^&+=])"+ //przynajmniej jeden specjalny znak
                    "(?=\\S+$).{8,}$"); //bez białych znaków i przynajmniej o dlugosci 8


    public static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
}
