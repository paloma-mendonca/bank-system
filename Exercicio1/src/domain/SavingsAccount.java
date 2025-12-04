package domain;

public class SavingsAccount extends BankAccount{

	public SavingsAccount() {}
	
	public SavingsAccount(String accountNumber, String branch, User user, double balance, double limit) {
		super(accountNumber, branch, user, balance, limit);
	}

	@Override
	public void deposit(double amount) {
		this.balance += amount;
	}

	@Override
	public void withdraw(double amount) {
		if((this.balance - amount) >= 0) {
			this.balance -= amount;
		}
		else {System.out.println("Insufficient funds!");}
	}
}
