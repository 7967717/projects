/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.dao;

import java.util.List;
import ua.bionic.pouch.beans.TransactionType;

/**
 *
 * @author romanrudenko
 */
public interface ITransactionTypeDao {
    void create(TransactionType transactionType);
    TransactionType read(TransactionType transactionType);
    void update(TransactionType transactionType);
    void delete(TransactionType transactionType);
    List<TransactionType> findAll();
    
}
