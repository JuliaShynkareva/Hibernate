package service;

import dao.ProfileInterface;
import entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Администратор on 10.11.2016.
 */

public class ProfileService implements IProfileService {

    @Autowired
    private ProfileInterface dao;

    @Override
    @Transactional
    public void create(Profile profile) {
        dao.createProfile(profile);
    }

    @Override
    @Transactional
    public void delete(Profile profile) {
        dao.deleteProfile(profile);
    }

    @Override
    @Transactional
    public void update(Profile profile) {
        dao.updateProfile(profile);
    }

    @Override
    @Transactional
    public Profile getById(String profileId) {
        return dao.getProfileById(profileId);
    }

    @Override
    @Transactional
    public boolean isEmailExists(String email) {
        return dao.isEmailExists(email);
    }
}
