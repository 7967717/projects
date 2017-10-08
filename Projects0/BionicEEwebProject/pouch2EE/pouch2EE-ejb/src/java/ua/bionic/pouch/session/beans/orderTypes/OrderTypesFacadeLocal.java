/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.pouch.session.beans.orderTypes;

import java.util.List;
import javax.ejb.Local;
import ua.bionic.pouch.entities.Accounts;
import ua.bionic.pouch.entities.OrderTypes;
import ua.bionic.pouch.entities.Orders;

/**
 *
 * @author romanrudenko
 */
@Local
public interface OrderTypesFacadeLocal {

    void create(OrderTypes orderTypes);

    void edit(OrderTypes orderTypes);

    void remove(OrderTypes orderTypes);

    OrderTypes find(Object id);

    List<OrderTypes> findAll();

    List<OrderTypes> findRange(int[] range);

    int count();

    public OrderTypes findByType(String type);
    
}
