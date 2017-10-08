package ua.miratech.rudenko.docstore.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.miratech.rudenko.docstore.domain.Users;
import ua.miratech.rudenko.docstore.service.UsersService;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("session")
public class LoginController {

    public static final Logger LOG = Logger.getLogger("rootLogger");

    @Autowired
    UsersService usersService;
    private Users user = new Users();


    @RequestMapping("/")
    public ModelAndView hello() {

        String message = "Welcome to docStore!";
        return new ModelAndView("index", "message", message);
    }


    @RequestMapping("admin/helloAdmin")
    public ModelAndView helloAdmin(HttpServletRequest request) {

        saveUserIdToSession(request);

        String message = "Hello " + user.getUsername() + "!";
        return new ModelAndView("admin/helloAdmin", "message", message);
    }

    @RequestMapping("/logout")
    public ModelAndView logout() {

        String message = "You've logout";
        return new ModelAndView("index", "message", message);
    }

    private void saveUserIdToSession(HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        user.setUsername(name);
        user.setUserId(usersService.getIdByName(name));
        request.getSession().setAttribute("user", user);
    }
}