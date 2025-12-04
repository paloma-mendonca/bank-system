package domain;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import repository.AccountRepository;
import repository.UserRepository;

public abstract class BankAccount {

	protected String accountNumber;
	protected String branch = "0001";
	protected User user;
	protected double balance;
	protected double limit;
	protected int accountType;
	public final int CHECKING_ACCOUNT = 1;
	public final int SAVINGS_ACCOUNT = 2;

	public BankAccount() {
	}

	public BankAccount(String accountNumber, String branch, User user, double balance, double limit) {
		this.accountNumber = accountNumber;
		this.branch = branch;
		this.user = user;
		this.balance = balance;
		this.limit = limit;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = balance;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public abstract void deposit(double amount);

	public abstract void withdraw(double amount);

	public void changeLimit(double newLimit) {
		limit += newLimit;
	}

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

	public static String genAccountNumber() {
		Random random = new Random();
		int accountNumberLen = 5;
		StringBuilder accountNumber = new StringBuilder(accountNumberLen);
		for (int i = 0; i < accountNumberLen; i++)
			accountNumber.append(random.nextInt(10));
		return accountNumber.toString();
	}
	
	public static String checkName(Scanner sc) {
		String name = null;
		do {
			System.out.print("Username: ");
			name = sc.nextLine();
			if(!name.matches("^[\\p{L} ]+( [\\p{L}]+)*$"))
				System.out.println("Invalid name.");
		}while(!name.matches("^[\\p{L} ]+( [\\p{L}]+)*$"));
		return name;
	}

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

	public static boolean checkCPF(String cpf) {
		if (!cpf.matches("[0-9]{11}"))
			return false;
		return true;
	}

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

	public static void printAccount(BankAccount account) {
		String accountType;
		if (account.getAccountType() == account.CHECKING_ACCOUNT)
			accountType = "Checking Account";
		else
			accountType = "Savings Account";
		System.out.println("Account created successfully!\n" + "User: " + account.getUser().getName() + "\nBranch: "
				+ account.getBranch() + "\n" + accountType + ": " + account.getAccountNumber()
				+ String.format("\nBalance: $%.2f", account.getBalance())
				+ String.format("\nLimit: $%.2f", account.getLimit()));
	}
}
