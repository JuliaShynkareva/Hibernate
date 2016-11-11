package dao;

import entity.Profile;

/**
 * Created by Администратор on 10.11.2016.
 */
public interface ProfileInterface {

    Profile createProfile(Profile profile);

    void deleteProfile(Profile profile);

    void updateProfile(Profile profile);

    Profile getProfileById(String profileId);

    boolean isEmailExists(String email);
}
