package ua.bionic.pouch.dao;

import java.util.List;
import ua.bionic.pouch.beans.Currency;


public interface ICurrencyDao {
    void create(Currency currency);
    Currency read(int id);
    void update(Currency currency);
    void delete(Currency currency);
    List<Currency> findAll();
}
