package godxero.control.user;

import godxero.model.User;

public interface UserService {
	User getUser (String userName);
	int addUser (User user);
	int deleteUser(String userName);
	int updateUser (User user);
	boolean isUsernameAvailable(String userName);
}
