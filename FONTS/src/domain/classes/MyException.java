package domain.classes;

public class MyException extends Exception {

    public MyException(String msg) {
        super(msg);
    }

    public String getMsg() { return getMessage(); }
}
