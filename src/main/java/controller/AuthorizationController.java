package controller;

import dto.ProfileDTO;
import dto.UserDTO;
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
import validator.UserValidator;

import javax.servlet.http.HttpSession;

/**
 * Created by Администратор on 10.11.2016.
 */

@Controller
@RequestMapping("/")
public class AuthorizationController {

    private static final String INCORRECT_PASSWORD = "Incorrect password is entered";
    private static final String INCORRECT_LOGIN = "Entered login does not exist";
    private static final String UNDEF = "undefined";

    @Autowired
    private UserService userService;

    /*@Autowired
    private ProfileService profileService;*/

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String authorizationGet(ModelMap model, HttpSession session){

        model.addAttribute("user", new UserDTO());
        model.addAttribute("incorrect", UNDEF);

        return "index";
    }

    @RequestMapping(value = "authorization", method = RequestMethod.POST)
    public String authorizationPost(@ModelAttribute("user") UserDTO u, BindingResult br,
                                    ModelMap model, HttpSession session){

        model.addAttribute("user", u);

        userValidator.validate(u, br);

        if(br.hasErrors()) {
            return "index";
        }
        else {
            String login = u.getLogin();
            String password = u.getPassword();
            if(!userService.isLogExists(login)){
                model.addAttribute("incorrect", INCORRECT_LOGIN);
                return "index";
            }
            else {
                User user = userService.getByLogin(login);
                if(!user.getPassword().equals(password)){
                    model.addAttribute("incorrect", INCORRECT_PASSWORD);
                    return "index";
                }
                else {
                    Profile profile = user.getProfile();
                    ProfileDTO profileDTO = entityConvertToDTO(profile);
                    session.setAttribute("entry", profileDTO);
                    session.setAttribute("profileId", Long.toString(profile.getId()));
                    /*System.out.println(session.getAttribute("profileId") + " profileId");*/
                    model.addAttribute("profile", profileDTO);

                    return "redirect:/profile";
                }
            }
        }
    }

    @RequestMapping(value = "exit", method = RequestMethod.GET)
    public String exitProfile(ModelMap model, HttpSession session){
        session.invalidate();
        model.addAttribute("user", new UserDTO());
        return "redirect:/";
    }

    public ProfileDTO entityConvertToDTO(Profile profile){
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setFirstname(profile.getFirstname());
        profileDTO.setLastname(profile.getLastname());
        profileDTO.setEmail(profile.getEmail());
        profileDTO.setAge(profile.getAge());
        profileDTO.setCity(profile.getCity());
        profileDTO.setPhone(profile.getPhone_number());
        profileDTO.setSex(profile.getSex());
        return profileDTO;
    }
}
