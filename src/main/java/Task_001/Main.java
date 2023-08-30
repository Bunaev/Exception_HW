package Task_001;
public class Main {
    public static void main(String[] args) {
        try {
            Number number = new Number();
        } catch (InvalidNumberException e){
            System.out.println(e.getMessage());
        }
    }
}
