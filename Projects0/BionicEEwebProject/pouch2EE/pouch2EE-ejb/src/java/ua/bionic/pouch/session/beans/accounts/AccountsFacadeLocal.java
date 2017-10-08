/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.pouch.session.beans.accounts;

import java.util.List;
import javax.ejb.Local;
import ua.bionic.pouch.entities.Accounts;
import ua.bionic.pouch.entities.Users;

/**
 *
 * @author romanrudenko
 */
@Local
public interface AccountsFacadeLocal {

    void create(Accounts accounts);

    void edit(Accounts accounts);

    void remove(Accounts accounts);

    Accounts find(Object id);

    List<Accounts> findAll();

    List<Accounts> findRange(int[] range);

    int count();

    public List<Accounts> findByUserID(Users user);
    
}
