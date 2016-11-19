package dao;

import entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Администратор on 10.11.2016.
 */


@Transactional
public class UserImpl implements UserInterface{

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    @Override
    public User createUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        User mergedUser = (User) sessionFactory.getCurrentSession().merge(user);
        sessionFactory.getCurrentSession().delete(mergedUser);
    }

    @Override
    public void updateUser(User user) {
        User mergedUser = (User) sessionFactory.getCurrentSession().merge(user);
        sessionFactory.getCurrentSession().update(mergedUser);
    }

    @Override
    public boolean isLoginExists(String login) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE login = :login");
        query.setParameter("login", login);
        return query.list().size() > 0;
    }

    @Override
    public User getUserByLogin(String login) {
        String userHQL = "FROM User WHERE login = :login";
        Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("login", login);
        return (User) query.uniqueResult();
    }
}
