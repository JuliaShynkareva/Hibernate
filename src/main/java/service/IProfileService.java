package service;

import entity.Profile;

/**
 * Created by Администратор on 10.11.2016.
 */
public interface IProfileService {

    void create(Profile profile);

    void delete(Profile profile);

    void update(Profile profile);

    Profile getById(String profileId);

    boolean isEmailExists(String email);
}
