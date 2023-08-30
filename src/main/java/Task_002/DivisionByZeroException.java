package Task_002;

public class DivisionByZeroException extends Exception {
    public DivisionByZeroException() {
        super();
    }
    @Override
    public String getMessage() {
        return "DivisionByZeroException: Деление на ноль недопустимо";
    }
}
