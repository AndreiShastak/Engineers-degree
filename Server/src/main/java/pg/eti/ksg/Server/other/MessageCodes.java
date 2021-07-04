package pg.eti.ksg.Server.other;

public enum MessageCodes {
    INVALIDLOGIN(1),
    INVALIDEMAIL(2),
    INVALIDVALUES(3),
    ERROR(404),
    OK(200);

    private int code;

    public int getCode() {
        return code;
    }

    MessageCodes(int i) {
        this.code=i;
    }

}
