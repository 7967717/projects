/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.pouch.session.beans.transactions;

import java.util.List;
import javax.ejb.Local;
import ua.bionic.pouch.entities.Accounts;
import ua.bionic.pouch.entities.Transactions;

/**
 *
 * @author romanrudenko
 */
@Local
public interface TransactionsFacadeLocal {

    void create(Transactions transactions);

    void edit(Transactions transactions);

    void remove(Transactions transactions);

    Transactions find(Object id);

    List<Transactions> findAll();

    List<Transactions> findRange(int[] range);

    int count();

    public List<Transactions> findByAccountId(Accounts accountId);
    
}
