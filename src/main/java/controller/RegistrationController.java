package controller;

import dto.ProfileDTO;
import dto.UserDTO;
import dto.UserNewDTO;
import entity.Profile;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ProfileService;
import service.UserService;
import validator.NewUserValidator;
import validator.ProfileValidator;

import javax.servlet.http.HttpSession;

/**
 * Created by Администратор on 10.11.2016.
 */

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final String S_USER = "user";
    private static final String S_PROFILE = "profile";

    private static final String U_ENTER = "user";
    private static final String U_NOT_ENTER = "unnamed";

    private static final String INCORRECT_EMAIL = "Entered email already exist";
    private static final String INCORRECT_LOGIN = "Entered login already exist";
    private static final String UNDEF = "undefined";

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private NewUserValidator newUserValidator;

    @Autowired
    private ProfileValidator profileValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String registrationGet(ModelMap model, HttpSession session){

        model.addAttribute("userNew", new UserNewDTO());
        model.addAttribute("profile", new ProfileDTO());
        model.addAttribute("check", S_USER);
        model.addAttribute("incorrect", UNDEF);
        session.setAttribute("entry", U_NOT_ENTER);
        return "registration";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String registrationUserPost(@ModelAttribute("userNew") UserNewDTO u, BindingResult br,
                                       ModelMap model, HttpSession session){

        model.addAttribute("check", S_USER);
        model.addAttribute("user", u);
        model.addAttribute("incorrect", UNDEF);

        newUserValidator.validate(u, br);

        if(br.hasErrors()) {

        }
        else {
            String login = u.getLogin();
            String password = u.getPassword();
            if(userService.isLogExists(login)){
                model.addAttribute("incorrect", INCORRECT_LOGIN);
            }
            else {
                User newUser = new User();
                newUser.setLogin(login);
                newUser.setPassword(password);
                session.setAttribute("newUser", newUser);
                model.addAttribute("profile", new ProfileDTO());
                model.addAttribute("check", S_PROFILE);
            }
        }
        return "registration";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String registrationProfilePost(@ModelAttribute("profile") ProfileDTO p, BindingResult br,
                                          ModelMap model, HttpSession session){

        model.addAttribute("check", S_PROFILE);
        model.addAttribute("profile", p);
        model.addAttribute("incorrect", UNDEF);

        profileValidator.validate(p, br);

        if(br.hasErrors()) {
            p.setAge(20);
            return "registration";
        }
        else {
            String email = p.getEmail();
            if(profileService.isEmailExists(email)) {
                model.addAttribute("incorrect", INCORRECT_EMAIL);
                return "registration";
            }
            else {
                Profile newProfile = createNewProfile(p);
                User newUser = (User) session.getAttribute("newUser");
                newUser.setProfile(newProfile);
                profileService.create(newProfile);
                userService.create(newUser);
                session.invalidate();
                session.setAttribute("entry", p);
                session.setAttribute("profileId", Long.toString(newProfile.getId()));
                    /*System.out.println(session.getAttribute("profileId") + " profileId");*/
                model.addAttribute("profile", p);

                return "redirect:/profile";
            }
        }
    }

    public Profile createNewProfile(ProfileDTO profileDTO){
        Profile profile = new Profile();
        profile.setFirstname(profileDTO.getFirstname());
        profile.setLastname(profileDTO.getLastname());
        profile.setEmail(profileDTO.getEmail());
        profile.setAge(profileDTO.getAge());
        profile.setCity(profileDTO.getCity());
        profile.setPhone_number(profileDTO.getPhone());
        profile.setSex(profileDTO.getSex());
        return profile;
    }
}
