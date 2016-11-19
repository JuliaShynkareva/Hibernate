package validator;

import dto.UserDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Администратор on 14.11.2016.
 */
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;

        String login = userDTO.getLogin();
        if ((login.length()) > 16) {
            errors.rejectValue("login", "login.tooLong", "Login must not more than 16 characters.");
        }
        if ((login.length()) < 3) {
            errors.rejectValue("login", "login.tooShort", "Login must not less than 3 characters.");
        }

        if (userDTO.getPassword().length() < 4) {
            errors.rejectValue("password", "password.tooShort", "Password must not less than 4 characters.");
        }
        if (userDTO.getPassword().length() > 16) {
            errors.rejectValue("password", "password.tooLong", "Password must not more than 16 characters.");
        }
    }
}
