/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.pouch.session.beans.users;

import java.util.List;
import javax.ejb.Local;
import ua.bionic.pouch.entities.Users;

/**
 *
 * @author romanrudenko
 */
@Local
public interface UsersFacadeLocal {

    void create(Users users);

    void edit(Users users);

    void remove(Users users);

    Users find(Object id);

    List<Users> findAll();

    List<Users> findRange(int[] range);

    int count();

    public Users findByLogin(String login);
    
}
