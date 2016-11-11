package dao;

import entity.Profile;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Администратор on 10.11.2016.
 */


@Transactional
public class ProfileImpl implements ProfileInterface{

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    @Override
    public Profile createProfile(Profile profile) {
        sessionFactory.getCurrentSession().save(profile);
        return profile;
    }

    @Override
    public void deleteProfile(Profile profile) {
        sessionFactory.getCurrentSession().delete(profile);
    }

    @Override
    public void updateProfile(Profile profile) {
        sessionFactory.getCurrentSession().update(profile);
    }

    @Override
    public Profile getProfileById(String profileId) {
        Profile profile = sessionFactory.getCurrentSession().get(Profile.class, profileId);
        return profile;
    }

    @Override
    public boolean isEmailExists(String email) {
        /*String profileHQL = "FROM entity.Profile WHERE email = :email";
        Query query = sessionFactory.getCurrentSession().createQuery(profileHQL);
        query.setParameter("email", email);
        return query.list().size() > 0;*/
        return false;
    }
}
