package repository;

import java.util.HashMap;
import java.util.Map;

import domain.User;

public class UserRepository {
	
	private Map<String, User> users = new HashMap<>();
	
	public void addUser(User user) {
		users.put(user.getCpf(), user);
	}
	
	public User findUser(String cpf) {
		return users.get(cpf);
	}
}
