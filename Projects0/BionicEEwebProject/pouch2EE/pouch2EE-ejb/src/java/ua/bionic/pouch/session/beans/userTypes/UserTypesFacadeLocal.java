/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.pouch.session.beans.userTypes;

import java.util.List;
import javax.ejb.Local;
import ua.bionic.pouch.entities.UserTypes;

/**
 *
 * @author romanrudenko
 */
@Local
public interface UserTypesFacadeLocal {

    void create(UserTypes userTypes);

    void edit(UserTypes userTypes);

    void remove(UserTypes userTypes);

    UserTypes find(Object id);

    List<UserTypes> findAll();

    List<UserTypes> findRange(int[] range);

    int count();
    
}
