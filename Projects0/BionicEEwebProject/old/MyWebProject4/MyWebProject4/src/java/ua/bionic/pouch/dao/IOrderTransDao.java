/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.dao;

import java.util.List;
import ua.bionic.pouch.beans.OrderTrans;

/**
 *
 * @author romanrudenko
 */
public interface IOrderTransDao {
    
    void create(OrderTrans orderTrans);
    OrderTrans read(int id);
    void updateConfirmed(OrderTrans orderTrans);
    void delete(OrderTrans orderTrans);
    List<OrderTrans> findAll();
    
}
