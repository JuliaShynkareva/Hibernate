package dao;

import entity.User;

/**
 * Created by Администратор on 10.11.2016.
 */
public interface UserInterface {

    User createUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    boolean isLoginExists(String login);

    User getUserByLogin(String login);
}
