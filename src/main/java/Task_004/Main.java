package Task_004;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank tinkoff = new Bank("TinkoffBank");
        tinkoff.addAccount(new BankAccount("Сергей Сергеев", "1234 4567 8900 5467", 24567.35));
        tinkoff.addAccount(new BankAccount("Иван Иванов", "1234 4567 8900 6789", 56789.70));
        tinkoff.addAccount(new BankAccount("Андрей Андреев", "1234 4567 8908 8990", 45678.99));
        tinkoff.showAccounts();
        tinkoff.start();
    }
}
