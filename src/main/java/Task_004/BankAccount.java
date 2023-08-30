package Task_004;

public class BankAccount {
    private String owner;
    private String accountNumber;
    private double balance;
    private final double maxBalance = 100000;

    public BankAccount(String owner, String accountNumber, double balance) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getOwner() {
        return owner;
    }

    public double getMaxBalance() {
        return maxBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
