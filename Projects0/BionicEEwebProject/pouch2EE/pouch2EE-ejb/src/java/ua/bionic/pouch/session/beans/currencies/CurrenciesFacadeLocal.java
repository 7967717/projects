/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.pouch.session.beans.currencies;

import java.util.List;
import javax.ejb.Local;
import ua.bionic.pouch.entities.Currencies;

/**
 *
 * @author romanrudenko
 */
@Local
public interface CurrenciesFacadeLocal {

    void create(Currencies currencies);

    void edit(Currencies currencies);

    void remove(Currencies currencies);

    Currencies find(Object id);

    List<Currencies> findAll();

    List<Currencies> findRange(int[] range);

    int count();

    public Currencies findByType(String type);
    
}
