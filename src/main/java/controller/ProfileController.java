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
import validator.ProfileValidator;

import javax.servlet.http.HttpSession;

/**
 * Created by Администратор on 10.11.2016.
 */

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private static final String U_ENTER = "user";
    private static final String U_NOT_ENTER = "unnamed";

    private static final String DATA_UPDATE = "The data has been successfully updated";
    private static final String DATA_NOT_UPDATE = "old_data";

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileValidator profileValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String profileView(ModelMap model, HttpSession session) {
        if(session.getAttribute("entry").equals(U_NOT_ENTER)){
            return "redirect:/";
        }
        else {
            String profileId = (String) session.getAttribute("profileId");
            ProfileDTO profileDTO = entityConvertToDTO(profileService.getById(profileId));
            model.addAttribute("profile", profileDTO);
            model.addAttribute("profile_edit", DATA_NOT_UPDATE);
            return "privatePage";
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String registrationProfilePost(@ModelAttribute("profile") ProfileDTO p, BindingResult br,
                                          ModelMap model, HttpSession session){

        profileValidator.validate(p, br);

        model.addAttribute("profile", p);

        if(br.hasErrors()) {
            model.addAttribute("profile_edit", DATA_NOT_UPDATE);
        }
        else {
            String profileId = (String) session.getAttribute("profileId");
            Profile profile = profileService.getById(profileId);
            Profile profileNew = DTOConvertToEntity(p, profile);
            profileService.update(profileNew);
            model.addAttribute("profile", p);
            model.addAttribute("profile_edit", DATA_UPDATE);
        }

        return "privatePage";
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

    public Profile DTOConvertToEntity(ProfileDTO profileDTO, Profile profile){
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
