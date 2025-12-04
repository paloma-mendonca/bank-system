package repository;

import java.util.HashMap;
import java.util.Map;

import domain.BankAccount;

public class AccountRepository {
	
	private Map<String, BankAccount> accounts = new HashMap<>();
	
	
	public void addAccount(BankAccount account) {
		accounts.put(account.getAccountNumber(), account);
	}
	
	public BankAccount findAccount (String accountNumber) {
		return accounts.get(accountNumber);
	}
	
	public void listAccount() {
		for(BankAccount account : accounts.values()) {
			String accountType;
			if(account.getAccountType() == account.CHECKING_ACCOUNT)
				accountType = "Checking Account";
			else accountType = "Savings Account";
			System.out.println("Username: " + account.getUser().getName() +
					" Branch: " + account.getBranch() + 
					" Account: " + account.getAccountNumber() + 
					" Account type: " + accountType);
		}
	}
}
