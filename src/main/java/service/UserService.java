package service;

import dao.UserInterface;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Администратор on 10.11.2016.
 */

public class UserService implements IUserService{

    @Autowired
    private UserInterface dao;

    @Override
    @Transactional
    public void create(User user) {
        dao.createUser(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        dao.deleteUser(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        dao.updateUser(user);
    }

    @Override
    @Transactional
    public boolean isLogExists(String login) {
        return dao.isLoginExists(login);
    }

    @Override
    @Transactional
    public User getByLogin(String login) {
        return dao.getUserByLogin(login);
    }
}
