package domain;

public class CheckingAccount extends BankAccount {

	private double overdraft = -500.00;

	public CheckingAccount() {
	}

	public CheckingAccount(String accountNumber, String branch, User user, double balance, double limit) {
		super(accountNumber, branch, user, balance, limit);
	}

	@Override
	public void deposit(double amount) {
		this.balance += amount;
	}

	@Override
	public void withdraw(double amount) {
		if ((this.balance - amount) >= overdraft) {
			this.balance -= amount + (amount * 0.01);
		} else {
			System.out.println("Insufficient funds!");
		}
	}
}
