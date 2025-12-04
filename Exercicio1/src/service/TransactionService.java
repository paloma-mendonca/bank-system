package service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import domain.Transaction;

public class TransactionService {
	
	public List<Transaction> transactionHistory = new ArrayList<>();
	public DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private LocalTime startTime = LocalTime.of(8, 0);
	private LocalTime endTime = LocalTime.of(18, 0);
	
	public void register(Transaction t) {
		transactionHistory.add(t);
	}
	
	public void list() {
		for(Transaction transaction : transactionHistory) {
			System.out.println("Date: " + transaction.getDate().format(dateForm) + 
					"; Transaction type: " + transaction.getType() + 
					String.format("; Amount: $%.2f", transaction.getAmount()) + 
					"; Origin account: " + transaction.getOriginAccount() + 
					"; Destiny account: " + transaction.getDestinyAccount());
		}
	}
	
	public boolean checkTime() {
		LocalTime time = LocalTime.of(8,0);
		if(time.equals(startTime) || time.isAfter(startTime) && time.isBefore(endTime)) {
			return true;
		}
		return false;
	}
}
