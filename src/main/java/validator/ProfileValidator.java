package validator;

import dto.ProfileDTO;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * Created by Администратор on 15.11.2016.
 */
public class ProfileValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProfileDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProfileDTO profile = (ProfileDTO) o;

        String firstname = profile.getFirstname();
        if ((firstname.length()) > 16 || (firstname.length()) < 2) {
            errors.rejectValue("firstname", "firstname.notValid", "Name must be between 2 to 16.");
        }

        String lastname = profile.getLastname();
        if ((lastname.length()) > 20 || (lastname.length()) < 2) {
            errors.rejectValue("lastname", "lastname.notValid", "Surname must be between 2 to 20.");
        }

        /*int age = profile.getAge();
        if (age < 11 || age > 80) {
            errors.rejectValue("age", "age.notValid", "Age must be between 11 to 80.");
        }*/

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "age.empty", "Age must not be empty.");

        String phone = profile.getPhone();
        Pattern p = Pattern.compile("^(((((\\+37529|8029)[1-9]{1})|(\\+375447|80447)|(\\+375336|80336)|((\\+37525|8025)[0-9]{1}))(\\d{6}))|((\\+375|80)(1[567]|2[1234])\\d{7}))$");
        if (!p.matcher(phone).matches()) {
            errors.rejectValue("phone", "phone.notValid", "Phone is not valid.");
        }

        String city = profile.getCity();
        if (city.length() > 15 || city.length() < 3) {
            errors.rejectValue("city", "city.notValid", "City must be between 3 to 15.");
        }

        if( !EmailValidator.getInstance().isValid( profile.getEmail() ) ){
            errors.rejectValue("email", "email.notValid", "Email address is not valid.");
        }
    }
}
