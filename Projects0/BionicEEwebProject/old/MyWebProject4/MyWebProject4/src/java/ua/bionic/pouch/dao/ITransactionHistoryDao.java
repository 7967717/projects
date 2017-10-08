package ua.bionic.pouch.dao;

import ua.bionic.pouch.beans.TransactionHistory;

import java.util.List;

public interface ITransactionHistoryDao {
    void create(TransactionHistory transactionHistory);
    TransactionHistory readByAccountId(int id);
    void update(TransactionHistory transactionHistory);
    void delete(TransactionHistory transactionHistory);
    List<TransactionHistory> findAll();
}
