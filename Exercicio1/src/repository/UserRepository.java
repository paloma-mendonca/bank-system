package repository;

import java.util.HashMap;
import java.util.Map;

import domain.User;

/**
 * The UserRepository class is responsible for managing users in the system.
 * It provides methods to add and find users based on their CPF (Cadastro de Pessoas Físicas).
 */

/**
 * Repository for managing users.
 * This class uses a HashMap to store and retrieve users based on their CPF.
 */
public class UserRepository {
	
	/** A map to store users with their CPF as keys. */
	private Map<String, User> users = new HashMap<>();
	
	/**
	 * Adds a user to the repository.
	 * 
	 * @param user The user to be added.
	 */
	public void addUser(User user) {
		users.put(user.getCpf(), user);
	}
	
	/**
	 * Finds a user in the repository by their CPF.
	 * 
	 * @param cpf The CPF of the user to find.
	 * @return The user associated with the given CPF, or null if not found.
	 */
	public User findUser(String cpf) {
		return users.get(cpf);
	}
}
