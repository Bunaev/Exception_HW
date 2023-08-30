package Task_001;

import java.util.Scanner;

public class Number {
    Scanner scanner = new Scanner(System.in);
    int number;
    public Number() throws InvalidNumberException {
        System.out.println("Введите число: ");
        number = scanner.nextInt();
        if (number <=0){
            throw new InvalidNumberException("Некорректное число!");
        }
        else{
            System.out.println("Число корректно!");
        }
    }
}
