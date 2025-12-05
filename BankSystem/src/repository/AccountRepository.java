package repository;

import java.util.HashMap;
import java.util.Map;

import domain.BankAccount;

/**
 * The AccountRepository class is responsible for managing bank accounts in the system.
 * It provides methods to add, find, and list accounts.
 */
/**
 * Repository for managing bank accounts.
 * This class uses a HashMap to store and retrieve accounts based on their account numbers.
 */
public class AccountRepository {
	
	/** A map to store bank accounts with their account numbers as keys. */
	private Map<String, BankAccount> accounts = new HashMap<>();
	
	/**
	 * Adds a bank account to the repository.
	 * 
	 * @param account The bank account to be added.
	 */
	public void addAccount(BankAccount account) {
		accounts.put(account.getAccountNumber(), account);
	}
	
	/**
	 * Finds a bank account in the repository by its account number.
	 * 
	 * @param accountNumber The account number of the bank account to find.
	 * @return The bank account associated with the given account number, or null if not found.
	 */
	public BankAccount findAccount(String accountNumber) {
		return accounts.get(accountNumber);
	}
	
	/**
	 * Lists all bank accounts in the repository.
	 * This method prints the details of each account, including the username, branch, account number, and account type.
	 */
	public void listAccount() {
		for (BankAccount account : accounts.values()) {
			String accountType;
			if (account.getAccountType() == account.CHECKING_ACCOUNT)
				accountType = "Checking Account";
			else
				accountType = "Savings Account";
			System.out.println("Username: " + account.getUser().getName() +
					" Branch: " + account.getBranch() + 
					" Account: " + account.getAccountNumber() + 
					" Account type: " + accountType);
		}
	}
}
