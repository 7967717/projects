package ua.miratech.rudenko.docstore.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.miratech.rudenko.docstore.domain.Users;
import ua.miratech.rudenko.docstore.service.UsersService;

import java.util.List;

/**
 * Created by RomanR on 1/17/14.
 */
@Controller
@RequestMapping("admin/viewUsers")
public class UsersController {

    public static final Logger LOG = Logger.getLogger("rootLogger");

    @Autowired
    UsersService usersService;

    @RequestMapping(method = RequestMethod.GET)
    public String printUsers(ModelMap model) throws Exception {

        List<Users> userList = usersService.getAllUsers();
        model.addAttribute("users", userList);

        return "admin/viewUsers";
    }

}
