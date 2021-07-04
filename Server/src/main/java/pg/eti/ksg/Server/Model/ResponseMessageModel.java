package pg.eti.ksg.Server.Model;

import pg.eti.ksg.Server.other.MessageCodes;

public class ResponseMessageModel {

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(MessageCodes code) {
        this.code = code.getCode();
    }


    public ResponseMessageModel(){}
    public ResponseMessageModel(MessageCodes code)
    {
        this.code=code.getCode();
    }
    public ResponseMessageModel(int code){
        this.code=code;
    }

}
