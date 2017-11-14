package exception;

/**
 * This class is for reading file when an exception occur
 * @author Fu, Yunhao
 * @version 1.0
 */
public class IllegalDataException extends Exception{
    private String retCd ;
    private String msgDes;

    /**
     * Default constructor
     */
    public IllegalDataException() {
        super();
    }

    /**
     * Constructor with error message
     * @param message Error message
     */
    public IllegalDataException(String message) {
        super(message);
        msgDes = message;
    }

    /**
     * Full constructor
     * @param retCd RETCD information
     * @param msgDes Description message
     */
    public IllegalDataException(String retCd, String msgDes) {
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }

    /**
     * Return RETCD
     * @return RETCD
     */
    public String getRetCd() {
        return retCd;
    }

    /**
     * Return Description message
     * @return Description message
     */
    public String getMsgDes() {
        return msgDes;
    }
}
