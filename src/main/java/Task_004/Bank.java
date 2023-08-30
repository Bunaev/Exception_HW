package Task_004;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank extends Thread {
    String name;
    List<BankAccount> bankAccounts = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public Bank(String name) {
        this.name = name;
    }
    public void addAccount(BankAccount account) {
        bankAccounts.add(account);
    }
    public void run() {
        try {
            cashIn();
            cashOut();
        } catch (MaxBalanceExceededException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }
    private void cashIn() throws MaxBalanceExceededException {
        System.out.print("\n" + "Введите порядковый номер счета и сумму для пополнения.\n"+"Порядковый номер: ");
        int index = scanner.nextInt();
        while (index > bankAccounts.size() || index < 1) {
            System.out.print("Вы ввели неверное значение. Попробуйте еще раз: ");
            index = scanner.nextInt();
        }
        System.out.print("Сумма: ");
        double amount = scanner.nextDouble();
        if (amount + bankAccounts.get(index-1).getBalance() > bankAccounts.get(index-1).getMaxBalance()) {
            throw new MaxBalanceExceededException("MaxBalanceExceededException: Итоговая сумма превышает максимальный баланс по данному счету.");
        }
        else{
            bankAccounts.get(index-1).setBalance(bankAccounts.get(index-1).getBalance() + amount);
            System.out.println("Счет  №" + bankAccounts.get(index-1).getAccountNumber() + ", принадлежащий: " + bankAccounts.get(index-1).getOwner() + " успешно пополнен.");
        }
    }
    private void cashOut() throws InsufficientFundsException{
        System.out.print("\n" + "Введите порядковый номер счета и сумму для снятия.\n"+"Порядковый номер: ");
        int index = scanner.nextInt();
        while (index > bankAccounts.size() || index < 1) {
            System.out.print("Вы ввели неверное значение. Попробуйте еще раз: ");
            index = scanner.nextInt();
        }
        System.out.print("Сумма: ");
        double amount = scanner.nextDouble();
        if (amount > bankAccounts.get(index-1).getBalance()) {
            throw new InsufficientFundsException("InsufficientFundsException: Недостаточно средств на счете.");
        }
        else{
            bankAccounts.get(index-1).setBalance(bankAccounts.get(index-1).getBalance() - amount);
            System.out.println("Со счета №" + bankAccounts.get(index-1).getAccountNumber() + ", принадлежащего: " + bankAccounts.get(index-1).getOwner() + " успешно снято: " + amount + " руб.");
        }
    }
    public void showAccounts() {
        for (int i = 0; i < bankAccounts.size(); i++) {
            System.out.println(i+1 + ". Владелец счета: " + bankAccounts.get(i).getOwner() + "; Номер счета: " + bankAccounts.get(i).getAccountNumber() + "; Доступный баланс: " + bankAccounts.get(i).getBalance());
        }
    }
}
