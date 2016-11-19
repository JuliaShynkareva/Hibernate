package validator;

import dto.UserNewDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Администратор on 15.11.2016.
 */
public class NewUserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserNewDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserNewDTO userNewDTO = (UserNewDTO) o;

        String login = userNewDTO.getLogin();
        if ((login.length()) > 16) {
            errors.rejectValue("login", "login.tooLong", "Login must not more than 16 characters.");
        }
        if ((login.length()) < 3) {
            errors.rejectValue("login", "login.tooShort", "Login must not less than 3 characters.");
        }

        if (userNewDTO.getPassword().length() < 4) {
            errors.rejectValue("password", "password.tooShort", "Password must not less than 4 characters.");
        }
        if (userNewDTO.getPassword().length() > 16) {
            errors.rejectValue("password", "password.tooLong", "Password must not more than 16 characters.");
        }
        if (!(userNewDTO.getPassword()).equals(userNewDTO.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "confirmPassword.passwordDontMatch", "Passwords don't match.");
        }
    }
}
