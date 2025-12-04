package domain;

import java.time.LocalDateTime;

public class Transaction {
	
	private LocalDateTime dateTime;
	private String transactionType;
	private double amount;
	private String originAccount;
	private String destinyAccount;
	
	public Transaction(String transactionType, double amount, String originAccount, String destinyAccount) {
		this.dateTime = LocalDateTime.now();
		this.transactionType = transactionType;
		this.amount = amount;
		this.originAccount = originAccount;
		this.destinyAccount = destinyAccount;
	}

	public LocalDateTime getDate() {
		return dateTime;
	}

	public void setDate(LocalDateTime date) {
		this.dateTime = date;
	}

	public String getType() {
		return transactionType;
	}

	public void setType(String type) {
		this.transactionType = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getOriginAccount() {
		return originAccount;
	}

	public void setOriginAccount(String originAccount) {
		this.originAccount = originAccount;
	}

	public String getDestinyAccount() {
		return destinyAccount;
	}

	public void setDestinyAccount(String destinyAccount) {
		this.destinyAccount = destinyAccount;
	}
}
