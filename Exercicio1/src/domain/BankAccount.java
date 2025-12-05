package domain;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import repository.AccountRepository;
import repository.UserRepository;

/**
 * Abstract class representing a bank account.
 * Provides common attributes and functionalities for specific types of accounts.
 */
public abstract class BankAccount {

	/** The account number associated with the bank account. */
	protected String accountNumber;

	/** The branch identifier for the account. Defaults to "0001". */
	protected String branch = "0001";

	/** The user associated with the account. */
	protected User user;

	/** The current balance in the account. */
	protected double balance;

	/** The credit limit for the account. */
	protected double limit;

	/** The type of the account (e.g., checking or savings). */
	protected int accountType;

	/** Constant indicating a checking account type. */
	public final int CHECKING_ACCOUNT = 1;

	/** Constant indicating a savings account type. */
	public final int SAVINGS_ACCOUNT = 2;

	/**
	 * Default constructor.
	 */
	public BankAccount() {
	}

	/**
	 * Parameterized constructor.
	 * 
	 * @param accountNumber The account number.
	 * @param branch The branch identifier.
	 * @param user The user associated with the account.
	 * @param balance The initial balance.
	 * @param limit The credit limit.
	 */
	public BankAccount(String accountNumber, String branch, User user, double balance, double limit) {
		this.accountNumber = accountNumber;
		this.branch = branch;
		this.user = user;
		this.balance = balance;
		this.limit = limit;
	}

	/**
	 * Returns the account number.
	 * 
	 * @return The account number.
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Updates the account number.
	 * 
	 * @param accountNumber The new account number.
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Returns the branch identifier.
	 * 
	 * @return The branch identifier.
	 */
	public String getBranch() {
		return branch;
	}

	/**
	 * Updates the branch identifier.
	 * 
	 * @param branch The new branch.
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
	 * Returns the user associated with the account.
	 * 
	 * @return The user.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Updates the user associated with the account.
	 * 
	 * @param user The new user.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Returns the current account balance.
	 * 
	 * @return The account balance.
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Updates the account balance.
	 * 
	 * @param balance The new balance.
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Returns the account's credit limit.
	 * 
	 * @return The credit limit.
	 */
	public double getLimit() {
		return limit;
	}

	/**
	 * Updates the credit limit.
	 * 
	 * @param limit The new credit limit.
	 */
	public void setLimit(double limit) {
		this.limit = balance;
	}

	/**
	 * Returns the account type.
	 * 
	 * @return The account type.
	 */
	public int getAccountType() {
		return accountType;
	}

	/**
	 * Updates the account type.
	 * 
	 * @param accountType The new account type.
	 */
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	/**
	 * Abstract method for depositing money into the account.
	 * 
	 * @param amount The amount to deposit.
	 */
	public abstract void deposit(double amount);

	/**
	 * Abstract method for withdrawing money from the account.
	 * 
	 * @param amount The amount to withdraw.
	 */
	public abstract void withdraw(double amount);

	/**
	 * Updates the credit limit by adding a specified amount.
	 * 
	 * @param newLimit The amount to add to the limit.
	 */
	public void changeLimit(double newLimit) {
		limit += newLimit;
	}

	/**
	 * Facilitates the creation of a new bank account.
	 * Validates user input and determines account type before creating the account.
	 * 
	 * @param sc Scanner for user input.
	 * @param accountRepository Repository for account management.
	 * @param userRepository Repository for user management.
	 */
	public static void createAccount(Scanner sc, AccountRepository accountRepository, UserRepository userRepository) {
		String name = checkName(sc);
		String cpf = getCPF(sc);
		int accountType = accountType(sc);		
		User checkUser = userRepository.findUser(cpf);
		if (checkUser != null) {
			List<String> checkUserAccount = checkUser.getAccounts();
			if (checkUserAccount != null) {
				for (String accountCheck : checkUserAccount) {
					BankAccount userAccount = accountRepository.findAccount(accountCheck);
					if (accountType == userAccount.getAccountType()) {
						System.out.println("You already have this account type. Try a different account type.");
						return;
					}
				}
			}
		}

		User user = new User();
		user.setName(name);
		user.setCpf(cpf);
		userRepository.addUser(user);

		BankAccount account;
		do {
			account = switch (accountType) {
			case 1 -> new CheckingAccount();
			case 2 -> new SavingsAccount();
			default -> null;
			};
			account.setAccountType(accountType);
		} while (account == null);

		account.setUser(user);
		account.setAccountNumber(genAccountNumber());
		printAccount(account);
		user.setAccounts(account.getAccountNumber());
		accountRepository.addAccount(account);
	}

	/**
	 * Generates a random account number consisting of 5 digits.
	 * 
	 * @return The generated account number.
	 */
	public static String genAccountNumber() {
		Random random = new Random();
		int accountNumberLen = 5;
		StringBuilder accountNumber = new StringBuilder(accountNumberLen);
		for (int i = 0; i < accountNumberLen; i++)
			accountNumber.append(random.nextInt(10));
		return accountNumber.toString();
	}
	
	/**
	 * Validates and retrieves the user's name from input.
	 * 
	 * @param sc Scanner for user input.
	 * @return The validated user name.
	 */
	public static String checkName(Scanner sc) {
		String name = null;
		do {
			System.out.print("Username: ");
			name = sc.nextLine();
			if(!name.matches("^[\p{L} ]+( [\p{L}]+)*$"))
				System.out.println("Invalid name.");
		} while(!name.matches("^[\p{L} ]+( [\p{L}]+)*$"));
		return name;
	}

	/**
	 * Validates and retrieves a valid CPF from input.
	 * 
	 * @param sc Scanner for user input.
	 * @return The validated CPF.
	 */
	public static String getCPF(Scanner sc) {
		String cpf = null;
		do {
			System.out.print("CPF: ");
			cpf = sc.nextLine();
			if (!checkCPF(cpf))
				System.out.println("Invalid CPF!");
		} while (!checkCPF(cpf));
		return cpf;
	}

	/**
	 * Validates a CPF format (11 numerical digits).
	 * 
	 * @param cpf The CPF string to validate.
	 * @return True if the CPF is valid; false otherwise.
	 */
	public static boolean checkCPF(String cpf) {
		if (!cpf.matches("[0-9]{11}"))
			return false;
		return true;
	}

	/**
	 * Prompts the user to select an account type.
	 * 
	 * @param sc Scanner for user input.
	 * @return The selected account type.
	 */
	public static int accountType(Scanner sc) {
		int accountType = 0;
		do {
			System.out.print("""
			Select account type:
			1 - Checking Account
			2 - Savings Account
			""");
			int type = sc.nextInt();
			sc.nextLine();
			if (type == 1 || type == 2)
				accountType = type;
			else
				System.out.println("Invalid account type.");
		} while (accountType == 0);
		return accountType;
	}

	/**
	 * Prints the details of an account to the console.
	 * 
	 * @param account The account whose details are to be printed.
	 */
	public static void printAccount(BankAccount account) {
		String accountType;
		if (account.getAccountType() == account.CHECKING_ACCOUNT)
			accountType = "Checking Account";
		else
			accountType = "Savings Account";
		System.out.println("Account created successfully!\n" + "User: " + account.getUser().getName() + "\nBranch: " +
			account.getBranch() + "\n" + accountType + ": " + account.getAccountNumber() +
			String.format("\nBalance: $%.2f", account.getBalance()) +
			String.format("\nLimit: $%.2f", account.getLimit()));
	}
}