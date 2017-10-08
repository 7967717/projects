package ua.miratech.rudenko.docstore.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.miratech.rudenko.docstore.configuration.ConfigurationManager;
import ua.miratech.rudenko.docstore.domain.Users;
import ua.miratech.rudenko.docstore.service.UsersService;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by RomanR on 1/25/14.
 */
@Controller
public class RegisterController {

    public static final Logger LOG = Logger.getLogger("rootLogger");

    @Autowired
    UsersService usersService;
    @Autowired
    ConfigurationManager configurationManager;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("users") @Valid Users user, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            LOG.info("form has Errors");
            return "/register";
        }

        if (checkLogin(user.getUsername())) {
            model.addAttribute("userExist", configurationManager.getProperty("USER_EXIST"));
            return "/register";
        }

        if (!user.getPassword().equals(user.getPasswordConfirmation())){
            model.addAttribute("password", configurationManager.getProperty("PASSWORD_DO_NOT_MATCH"));
            return "/register";
        }

        user.setEnabled(Integer.valueOf(configurationManager.getProperty("ENABLED")));
        user.setRole(configurationManager.getProperty("AUTHORITY"));
        usersService.registerUser(user); //TODO hash user password
                                         //TODO automatic login after registration

        return "registered";
    }


    @RequestMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("users", new Users());
        return "register";
    }

    private boolean checkLogin(String username) {
        boolean check = false;
        ArrayList<Users> userList = (ArrayList<Users>) usersService.getAllUsers();

        LOG.info("username " + username);

        for (Users user : userList) {
            if (user.getUsername().equals(username)) {
                LOG.info("user.getUsername() " + user.getUsername());
                check = true;
            }
        }
        return check;
    }

}
