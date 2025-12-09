package service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import domain.Transaction;

/**
 * The TransactionService class provides services for managing transactions.
 * It includes functionality to register, list, and validate transactions based on time constraints.
 */
/**
 * Service class for managing transactions.
 * This class maintains a history of transactions and provides methods to register,
 * list, and validate transactions based on the allowed time window.
 */
public class TransactionService {
	
	/** A list to store the history of transactions. */
	public List<Transaction> transactionHistory = new ArrayList<>();
	
	/** The date and time formatter for transaction timestamps. */
	public DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	/** The start time for allowed transactions. */
	private LocalTime startTime = LocalTime.of(8, 0);
	
	/** The end time for allowed transactions. */
	private LocalTime endTime = LocalTime.of(18, 0);
	
	/**
	 * Registers a new transaction in the transaction history.
	 * 
	 * @param t The transaction to be registered.
	 */
	public void register(Transaction t) {
		transactionHistory.add(t);
	}
	
	/**
	 * Lists all transactions in the transaction history.
	 * This method prints the details of each transaction, including date, type, amount,
	 * origin account, and destination account.
	 */
	public void list() {
		for (Transaction transaction : transactionHistory) {
			System.out.println("Date: " + transaction.getDate().format(dateForm) + 
					"; Transaction type: " + transaction.getType() + 
					String.format("; Amount: $%.2f", transaction.getAmount()) + 
					"; Origin account: " + transaction.getOriginAccount() + 
					"; Destination account: " + transaction.getDestinyAccount());
		}
	}
	
	/**
	 * Checks if the current time is within the allowed transaction time window.
	 * 
	 * @return True if the current time is within the allowed window, false otherwise.
	 */
	public boolean checkTime() {
		LocalTime time = LocalTime.of(8, 0);
		if (time.equals(startTime) || time.isAfter(startTime) && time.isBefore(endTime)) {
			return true;
		}
		return false;
	}
}

