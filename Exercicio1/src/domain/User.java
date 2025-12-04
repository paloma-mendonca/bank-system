package domain;

import java.util.ArrayList;
import java.util.List;

public class User {
	protected String name;
	protected String cpf;
	protected List<String> account = new ArrayList<String>();;
	
	public User() {
	}

	public User(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<String> getAccounts() {
		return account;
	}

	public void setAccounts(String account) {
		this.account.add(account);
	}
}
