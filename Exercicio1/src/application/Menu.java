package application;

import java.util.Scanner;



import domain.BankAccount;
import repository.AccountRepository;
import repository.UserRepository;
import service.AccountService;
import service.ExportService;
import service.TransactionService;

/**Menu Class
 * Process commands
 */

public class Menu {

	private static final AccountRepository accountRepository = new AccountRepository();
	private static final UserRepository userRepository = new UserRepository();
	private static final TransactionService transactionService = new TransactionService();
	private static final AccountService accountService = new AccountService(accountRepository, transactionService);
	private static final ExportService exportService = new ExportService();

	public void start() {
		Scanner sc = new Scanner(System.in);
		int option;
		
		do {
			showMenu();
			option = sc.nextInt();
			sc.nextLine();

			switch (option) {
			case 1:
				BankAccount.createAccount(sc, accountRepository, userRepository);
				break;
			case 2:
				accountRepository.listAccount();
				break;
			case 3:
				accountService.checkBalance(sc);
			case 4:
				if (transactionService.checkTime() == true)
					accountService.deposit(sc);
				else System.out.println("This action cannot be performed at this time. Please try again between 8:00 and 18:00.");
				break;
			case 5:
				if (transactionService.checkTime() == true)
					accountService.withdraw(sc);
				else System.out.println("This action cannot be performed at this time. Please try again between 8:00 and 18:00.");
				break;
			case 6:
				if (transactionService.checkTime() == true)
					accountService.transfer(sc);
				else System.out.println("This action cannot be performed at this time. Please try again between 8:00 and 18:00.");
				break;
			case 7:
				accountService.changeLimit(sc);
				break;
			case 8:
				exportService.exportCSV(transactionService);
				break;
			case 0:
				System.out.println("Exiting.");
				break;
			default:
				System.out.println("Invalid option");
				break;
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (option != 0);

	}

	private void showMenu() {
		System.out.println("""
				---------- MENU ----------
				1 - Create account
				2 - List account
				3 - Check balance
				4 - Deposit
				5 - Withdraw
				6 - Transfer
				7 - Change limit
				8 - Export history CSV
				0 - Exit
				""");
	}
}
