package Task_002;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число A: ");
        int a = scanner.nextInt();
        System.out.println("Введите число B: ");
        int b = scanner.nextInt();
        try {
            division(a, b);
        } catch (DivisionByZeroException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void division(int a, int b) throws DivisionByZeroException {
        if (b == 0) {
            throw new DivisionByZeroException();
        }
        System.out.println(a / b);
    }
}
