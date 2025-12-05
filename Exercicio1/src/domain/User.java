package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The User class represents a user in the banking system.
 * It includes details such as the user's name, CPF (Cadastro de Pessoas Físicas),
 * and a list of accounts associated with the user.
 */
/**
 * Represents a user in the banking system.
 * This class provides functionality to manage user details and their associated accounts.
 */
public class User {
	
	/** The name of the user. */
	protected String name;
	
	/** The CPF (Cadastro de Pessoas Físicas) of the user. */
	protected String cpf;
	
	/** A list of account numbers associated with the user. */
	protected List<String> account = new ArrayList<String>();
	
	/**
	 * Default constructor for the User class.
	 */
	public User() {
	}

	/**
	 * Constructs a User with the specified name and CPF.
	 * 
	 * @param name The name of the user.
	 * @param cpf The CPF of the user.
	 */
	public User(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}

	/**
	 * Gets the name of the user.
	 * 
	 * @return The name of the user.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the user.
	 * 
	 * @param name The new name of the user.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the CPF of the user.
	 * 
	 * @return The CPF of the user.
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Sets the CPF of the user.
	 * 
	 * @param cpf The new CPF of the user.
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * Gets the list of account numbers associated with the user.
	 * 
	 * @return A list of account numbers.
	 */
	public List<String> getAccounts() {
		return account;
	}

	/**
	 * Adds an account number to the user's list of accounts.
	 * 
	 * @param account The account number to be added.
	 */
	public void setAccounts(String account) {
		this.account.add(account);
	}
}
