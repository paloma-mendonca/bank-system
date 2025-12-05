package domain;

/**
 * The SavingsAccount class represents a type of bank account that allows deposits and withdrawals.
 * It extends the BankAccount class and inherits its properties and methods.
 */
/**
 * Represents a savings account in the banking system.
 * This class provides functionality for depositing and withdrawing funds
 * while ensuring that withdrawals do not exceed the available balance.
 */
public class SavingsAccount extends BankAccount{

	/**
	 * Default constructor for the SavingsAccount class.
	 */
	public SavingsAccount() {}
	
	/**
	 * Constructs a SavingsAccount with the specified details.
	 * 
	 * @param accountNumber The account number of the savings account.
	 * @param branch The branch where the account is held.
	 * @param user The user who owns the account.
	 * @param balance The initial balance of the account.
	 * @param limit The overdraft limit for the account.
	 */
	public SavingsAccount(String accountNumber, String branch, User user, double balance, double limit) {
		super(accountNumber, branch, user, balance, limit);
	}

	/**
	 * Deposits a specified amount into the savings account.
	 * 
	 * @param amount The amount to be deposited.
	 */
	@Override
	public void deposit(double amount) {
		this.balance += amount;
	}

	/**
	 * Withdraws a specified amount from the savings account if sufficient funds are available.
	 * 
	 * @param amount The amount to be withdrawn.
	 */
	@Override
	public void withdraw(double amount) {
		if((this.balance - amount) >= 0) {
			this.balance -= amount;
		}
		else {
			System.out.println("Insufficient funds!");
		}
	}
}
