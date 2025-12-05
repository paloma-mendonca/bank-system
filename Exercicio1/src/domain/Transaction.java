/**
 * The Transaction class represents a financial transaction in the banking system.
 * It includes details such as the transaction type, amount, origin account, and destination account.
 */
package domain;

import java.time.LocalDateTime;

/**
 * Represents a financial transaction in the banking system.
 * This class stores information about the transaction, including its type, amount,
 * origin account, destination account, and the date and time it occurred.
 */
public class Transaction {
	
	/** The date and time when the transaction occurred. */
	private LocalDateTime dateTime;
	
	/** The type of the transaction (e.g., deposit, withdrawal, transfer). */
	private String transactionType;
	
	/** The amount involved in the transaction. */
	private double amount;
	
	/** The account number of the origin account. */
	private String originAccount;
	
	/** The account number of the destination account. */
	private String destinyAccount;
	
	/**
	 * Constructs a Transaction with the specified details.
	 * 
	 * @param transactionType The type of the transaction.
	 * @param amount The amount involved in the transaction.
	 * @param originAccount The account number of the origin account.
	 * @param destinyAccount The account number of the destination account.
	 */
	public Transaction(String transactionType, double amount, String originAccount, String destinyAccount) {
		this.dateTime = LocalDateTime.now();
		this.transactionType = transactionType;
		this.amount = amount;
		this.originAccount = originAccount;
		this.destinyAccount = destinyAccount;
	}

	/**
	 * Gets the date and time of the transaction.
	 * 
	 * @return The date and time of the transaction.
	 */
	public LocalDateTime getDate() {
		return dateTime;
	}

	/**
	 * Sets the date and time of the transaction.
	 * 
	 * @param date The new date and time of the transaction.
	 */
	public void setDate(LocalDateTime date) {
		this.dateTime = date;
	}

	/**
	 * Gets the type of the transaction.
	 * 
	 * @return The type of the transaction.
	 */
	public String getType() {
		return transactionType;
	}

	/**
	 * Sets the type of the transaction.
	 * 
	 * @param type The new type of the transaction.
	 */
	public void setType(String type) {
		this.transactionType = type;
	}

	/**
	 * Gets the amount involved in the transaction.
	 * 
	 * @return The amount involved in the transaction.
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Sets the amount involved in the transaction.
	 * 
	 * @param amount The new amount involved in the transaction.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * Gets the account number of the origin account.
	 * 
	 * @return The account number of the origin account.
	 */
	public String getOriginAccount() {
		return originAccount;
	}

	/**
	 * Sets the account number of the origin account.
	 * 
	 * @param originAccount The new account number of the origin account.
	 */
	public void setOriginAccount(String originAccount) {
		this.originAccount = originAccount;
	}

	/**
	 * Gets the account number of the destination account.
	 * 
	 * @return The account number of the destination account.
	 */
	public String getDestinyAccount() {
		return destinyAccount;
	}

	/**
	 * Sets the account number of the destination account.
	 * 
	 * @param destinyAccount The new account number of the destination account.
	 */
	public void setDestinyAccount(String destinyAccount) {
		this.destinyAccount = destinyAccount;
	}
}
