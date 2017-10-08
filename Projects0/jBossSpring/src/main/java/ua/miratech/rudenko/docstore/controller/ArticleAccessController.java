package ua.miratech.rudenko.docstore.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.miratech.rudenko.docstore.domain.Shared;
import ua.miratech.rudenko.docstore.domain.Users;
import ua.miratech.rudenko.docstore.service.ArticlesService;
import ua.miratech.rudenko.docstore.service.SharedService;
import ua.miratech.rudenko.docstore.service.UsersService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by RomanR on 2/19/14.
 */
@Controller
@Scope("session")
public class ArticleAccessController {

    public static final Logger LOG = Logger.getLogger("rootLogger");

    @Autowired
    ArticlesService articlesService;
    @Autowired
    UsersService usersService;
    @Autowired
    SharedService sharedService;

    private Users user = new Users();

    @RequestMapping("user/grantAccess")
    public ModelAndView gotoGrantAccess(ModelMap model, HttpServletRequest request) {
        LOG.info("entered page grantAccess");

        user = (Users) request.getSession().getAttribute("user");
        model.addAttribute("articles", articlesService.getArticlesByUser(user.getUsername()));

        return new ModelAndView("user/grantAccess");
    }

    @RequestMapping("user/viewEditAccess")
    public ModelAndView gotoViewEditMetaForm(@ModelAttribute("Shared") Shared shared, ModelMap model) {

        LOG.info("entered page viewEditAccess");

        model.addAttribute(shared);

        return new ModelAndView("user/viewEditAccess");
    }

    @RequestMapping(value = "user/submitEditAccess", method = RequestMethod.POST)
    public ModelAndView submitEditAccess(@ModelAttribute("Shared") Shared shared, HttpServletRequest request,
                                       ModelMap model) {

        LOG.info("entered page submitEditAccess");

        LOG.info("grantee name " + shared.getGrantee());

        if (!shared.getGrantee().isEmpty()){
            if(!(usersService.getByUsername(shared.getGrantee()) == null)){
                LOG.info("there is such user");
                shared.setOwner(user.getUsername());
                sharedService.insertShared(shared);
            } else {
                LOG.info("there is no user with name " + shared.getGrantee()); //TODO add message there is no user with name
                return new ModelAndView("user/viewEditAccess");
            }
        } else {
            LOG.info("please enter user name");
            return new ModelAndView("user/viewEditAccess");
        }

        LOG.info("granted access to user " + shared.getGrantee());

        model.addAttribute("articles", articlesService.getArticlesByUser(user.getUsername()));

        LOG.info("entered grantAccess article list");

        return new ModelAndView("user/grantAccess");
    }
}
