/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.pouch.session.beans.userRoles;

import java.util.List;
import javax.ejb.Local;
import ua.bionic.pouch.entities.UserRoles;

/**
 *
 * @author romanrudenko
 */
@Local
public interface UserRolesFacadeLocal {

    void create(UserRoles userRoles);

    void edit(UserRoles userRoles);

    void remove(UserRoles userRoles);

    UserRoles find(Object id);

    List<UserRoles> findAll();

    List<UserRoles> findRange(int[] range);

    int count();
    
}
