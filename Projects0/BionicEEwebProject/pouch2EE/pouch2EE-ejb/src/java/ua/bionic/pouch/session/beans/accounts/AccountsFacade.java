/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.session.beans.accounts;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ua.bionic.pouch.entities.Accounts;
import ua.bionic.pouch.entities.Users;
import ua.bionic.pouch.session.beans.AbstractFacade;

/**
 *
 * @author romanrudenko
 */
@Stateless
public class AccountsFacade extends AbstractFacade<Accounts> implements AccountsFacadeLocal {

    @PersistenceContext(unitName = "pouch2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountsFacade() {
        super(Accounts.class);
    }

    @Override
    public List<Accounts> findByUserID(Users user) {
        TypedQuery<Accounts> query = em.createNamedQuery("Accounts.findByUserId", Accounts.class);
        query.setParameter("userId", user);
        List<Accounts> accounts = null;
        accounts = query.getResultList();
        return accounts;
    }

}
