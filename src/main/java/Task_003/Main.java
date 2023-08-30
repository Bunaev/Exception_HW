package Task_003;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число A: ");
        int a = scanner.nextInt();
        System.out.println("Введите число B: ");
        int b = scanner.nextInt();
        System.out.println("Введите число C: ");
        int c = scanner.nextInt();
        try {
            numberCheck(a, b, c);
        } catch (NumberOutOfRangeException | NumberSumException | DivisionByZeroException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void numberCheck(int a, int b, int c) throws NumberOutOfRangeException, NumberSumException, DivisionByZeroException {
        if (a > 100){throw new NumberOutOfRangeException("NumberOutOfRangeException: Первое число вне допустимого диапазона");}
        else if (b < 0){throw new NumberOutOfRangeException("NumberOutOfRangeException: Второе число вне допустимого диапазона");}
        else if (a + b < 10){throw new NumberSumException("NumberSumException: Сумма первого и второго чисел слишком мала");}
        else if (c == 0){throw new DivisionByZeroException();}
        else System.out.println("Проверка пройдена успешно!");
    }
}
