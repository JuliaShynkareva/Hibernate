package service;

import entity.User;

/**
 * Created by Администратор on 10.11.2016.
 */
public interface IUserService {

    void create(User user);

    void delete(User user);

    void update(User user);

    boolean isLogExists(String login);

    User getByLogin(String login);
}
