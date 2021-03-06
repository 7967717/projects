/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.pouch.session.beans.orders;

import java.util.List;
import javax.ejb.Local;
import ua.bionic.pouch.entities.Accounts;
import ua.bionic.pouch.entities.Orders;

/**
 *
 * @author romanrudenko
 */
@Local
public interface OrdersFacadeLocal {

    void create(Orders orders);

    void edit(Orders orders);

    void remove(Orders orders);

    Orders find(Object id);

    List<Orders> findAll();

    List<Orders> findRange(int[] range);

    int count();

    public List<Orders> findByAccountId(Accounts accountId);

    public List<Orders> findByAccountIdAndConfirmed(Accounts accountId, boolean confirmed);
    
}
