package service;

import java.util.Scanner;

import domain.BankAccount;
import domain.Transaction;
import repository.AccountRepository;

public class AccountService {

	private AccountRepository repository;
	private TransactionService transactionService;
	public BankAccount account;

	public AccountService(AccountRepository repository, TransactionService transactionService) {
		this.repository = repository;
		this.transactionService = transactionService;
	}
	
	public void checkBalance(Scanner sc) {
		do {
			System.out.print("Enter the account number: ");
			String accountNumber = sc.next();
			account = repository.findAccount(accountNumber);
			if (account != null) {
				System.out.printf("Balance: $%.2f%n", account.getBalance());
			} else {
				System.out.println("Invalid account!");
			}
		} while (account == null);
	}

	public void deposit(Scanner sc) {
		if (transactionService.checkTime() == true) {
			do {
				System.out.print("Enter the account number: ");
				String accountNumber = sc.nextLine();
				account = repository.findAccount(accountNumber);
				if (account != null) {
					String depositStr = null;
					do {
						System.out.print("Deposited amount: $");
						depositStr = sc.next().trim();
						if(!checkAmount(depositStr))
							System.out.println("Invalid Amount. Please use '.' instead of ','.");
						else{
							double deposit = Double.parseDouble(depositStr);
							account.deposit(deposit);
							System.out.printf("Balance: $%.2f%n", account.getBalance());
							transactionService.register(new Transaction("Deposit", deposit, null, accountNumber));
						}
					}while(!checkAmount(depositStr));
				} else {
					System.out.println("Invalid account!");
				}
			} while (account == null);
		}else {
			System.out.println("This action cannot be performed at this time. Please try again between 8:00 and 18:00.");
		}
	}

	public void withdraw(Scanner sc) {
		do {
			System.out.print("Enter the account number: ");
			String accountNumber = sc.next();
			account = repository.findAccount(accountNumber);
			if (account != null) {
				String withdrawStr = null;
				do {
					System.out.println("Withdraw amount: $");
					withdrawStr = sc.next().trim();
					if(!checkAmount(withdrawStr))
						System.out.println("Invalid Amount. Please use '.' instead of ','.");
					else{
						System.out.print("Withdrawn: $");
						double withdraw = Double.parseDouble(withdrawStr);
						account.withdraw(withdraw);
						System.out.printf("Balance: $%.2f%n", account.getBalance());
						transactionService.register(new Transaction("Withdraw", withdraw, accountNumber, null));
					}
				}while(!checkAmount(withdrawStr));
			} else {
				System.out.println("Invalid account!");
			}
		} while (account == null);
	}

	public void transfer(Scanner sc) {
		BankAccount destinyAccount;
		do {
			System.out.print("Enter the account number: ");
			String accountNumber = sc.next();
			System.out.print("Enter the destination account number: ");
			String destinyAccountNumber = sc.next();
			account = repository.findAccount(accountNumber);
			destinyAccount = repository.findAccount(destinyAccountNumber);
			if (account != null && destinyAccount != null && destinyAccount != account) {
				String transferStr = null;
				do {
					System.out.print("Transfer amount: $");
					transferStr = sc.next().trim();
					double transfer = Double.parseDouble(transferStr);
					if(transfer > account.getLimit()) {
						System.out.println("Limit exceeded!");
						return;
					}				
					account.withdraw(transfer);
					destinyAccount.deposit(transfer);
					System.out.printf("Transfer in the amount of $%.2f completed successfully!%n", transfer);
					transactionService.register(new Transaction("Transfer", transfer, accountNumber, destinyAccountNumber));
				}while(!checkAmount(transferStr));
			} else {
				System.out.println("Invalid account!");
			}
		} while (account == null && destinyAccount == null);
	}

	public boolean checkAmount(String amount) {
		if (!amount.matches("^\\d+(\\.\\d{1,2})?$"))
			return false;
		return true;
	}
	
	public void changeLimit(Scanner sc) {
		do {
			System.out.print("Enter the account number: ");
			String accountNumber = sc.next();
			account = repository.findAccount(accountNumber);
			if (account != null) {
				System.out.print("New limit: $");
				double limit = sc.nextDouble();
				account.changeLimit(limit);
				System.out.printf("Limit: $%.2f%n", limit);
			} else {
				System.out.println("Invalid account!");
			}
		} while (account == null);
	}
}
