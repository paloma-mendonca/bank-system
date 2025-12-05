package domain;

/**
 * Represents a Checking Account which extends the BankAccount class.
 * This account includes an overdraft facility and specific methods for deposits and withdrawals.
 */
public class CheckingAccount extends BankAccount {

    /**
     * The overdraft limit for the checking account. Negative value indicates the maximum overdraft.
     */
    private double overdraft = -500.00;

    /**
     * Default constructor for the CheckingAccount class.
     */
    public CheckingAccount() {
    }

    /**
     * Constructor for the CheckingAccount class with parameters.
     * 
     * @param accountNumber The account number associated with the checking account.
     * @param branch The branch identifier for the checking account.
     * @param user The user linked to the account.
     * @param balance The initial balance of the account.
     * @param limit The maximum limit for the account (overdraft not included here).
     */
    public CheckingAccount(String accountNumber, String branch, User user, double balance, double limit) {
        super(accountNumber, branch, user, balance, limit);
    }

    /**
     * Adds a specified amount to the account balance.
     * 
     * @param amount The amount to be deposited into the account.
     */
    @Override
    public void deposit(double amount) {
        this.balance += amount;
    }

    /**
     * Withdraws a specified amount from the account balance.
     * 
     * A fee of 1% of the withdrawal amount is applied to every transaction.
     * The withdrawal will only proceed if the resulting balance does not exceed the overdraft limit.
     * 
     * @param amount The amount to withdraw.
     */
    @Override
    public void withdraw(double amount) {
        if ((this.balance - amount) >= overdraft) {
            this.balance -= amount + (amount * 0.01);
        } else {
            System.out.println("Insufficient funds!");
        }
    }
}