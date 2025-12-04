package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.Transaction;

public class ExportService {

	public void exportCSV (TransactionService transacaoService) {
		String line = "Date,Transaction type,Amoun,Origin account,Destiny account";
		List<String> lines = new ArrayList<>();
		lines.add(line);
		for(Transaction transaction : transacaoService.transactionHistory) {
			line = transaction.getDate().format(transacaoService.dateForm) + ", " + transaction.getType() + "," +
					String.format("$%.2f", transaction.getAmount()) + ", " + transaction.getOriginAccount() + ", " + transaction.getDestinyAccount();
			lines.add(line);
		}		
		File file = new File("transationHistory.csv");
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
			for(String writeLine : lines) {
				bw.write(writeLine);
				bw.newLine();
			}
			System.out.println("Transaction history generated on: " + file.getCanonicalPath());
		}catch(IOException e) {
			if(e.getMessage().contains("The process cannot access the file because it is being used by another process"))
				System.out.println("The process cannot access the file because it is being used by another process. Please close it and try again.");
			else e.printStackTrace();
		}
	}
}
