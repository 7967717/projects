/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.pouch.session.beans.userRoles;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ua.bionic.pouch.entities.UserRoles;
import ua.bionic.pouch.session.beans.AbstractFacade;

/**
 *
 * @author romanrudenko
 */
@Stateless
public class UserRolesFacade extends AbstractFacade<UserRoles> implements UserRolesFacadeLocal {
    @PersistenceContext(unitName = "pouch2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRolesFacade() {
        super(UserRoles.class);
    }
    
}
