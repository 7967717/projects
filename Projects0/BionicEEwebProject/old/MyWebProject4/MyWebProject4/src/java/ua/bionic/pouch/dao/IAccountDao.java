package ua.bionic.pouch.dao;

import ua.bionic.pouch.beans.Account;


public interface IAccountDao {
    void create(Account account);
    Account readByUserId(int id);
    void update(Account account);
    void delete(Account account);
    void findAll();
}
