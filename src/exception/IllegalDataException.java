package exception;

public class IllegalDataException extends Exception{
    private String retCd ;
    private String msgDes;

    public IllegalDataException() {
        super();
    }

    public IllegalDataException(String message) {
        super(message);
        msgDes = message;
    }

    public IllegalDataException(String retCd, String msgDes) {
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }

    public String getRetCd() {
        return retCd;
    }

    public String getMsgDes() {
        return msgDes;
    }
}
