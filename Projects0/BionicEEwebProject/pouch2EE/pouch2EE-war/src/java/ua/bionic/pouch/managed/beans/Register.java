/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.pouch.managed.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import ua.bionic.pouch.entities.Users;
import ua.bionic.pouch.session.beans.users.UsersFacadeLocal;

/**
 *
 * @author romanrudenko
 */
@ManagedBean
@RequestScoped
public class Register implements Serializable{
    @EJB
    private UsersFacadeLocal usersFacade;
    
    
   private Users user = new Users();
   private List<Users> userList = new ArrayList<>();

    public Register() {
    }
    
    public String doCreateUser(){
        usersFacade.create(user);
        userList = usersFacade.findAll();
        return "users/userList.xhtml";
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    } 

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }
    
    
    
}
