package controller;

import entity.Profile;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ProfileService;
import service.UserService;

/**
 * Created by Администратор on 10.11.2016.
 */

@Controller
@RequestMapping("/")
public class AuthorizationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        Profile profile = new Profile();
        profile.setEmail("123");
        User user = new User();
        user.setLogin("qwerty");
        user.setPassword("123456");
        user.setProfile(profile);

        profileService.create(profile);
        userService.create(user);

        model.addAttribute("message", "Hello world!");
        return "hello";
    }
}
