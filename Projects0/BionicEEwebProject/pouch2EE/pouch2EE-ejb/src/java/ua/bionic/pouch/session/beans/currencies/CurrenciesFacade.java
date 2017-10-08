/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.pouch.session.beans.currencies;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ua.bionic.pouch.entities.Currencies;
import ua.bionic.pouch.session.beans.AbstractFacade;

/**
 *
 * @author romanrudenko
 */
@Stateless
public class CurrenciesFacade extends AbstractFacade<Currencies> implements CurrenciesFacadeLocal {
    @PersistenceContext(unitName = "pouch2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CurrenciesFacade() {
        super(Currencies.class);
    }
    
    @Override
    public Currencies findByType(String type) {
        TypedQuery<Currencies> query = em.createNamedQuery("Currencies.findByType", Currencies.class);
        query.setParameter("type", type);
        Currencies currency = query.getSingleResult();
        return currency;
    }
    
}
