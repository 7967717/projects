/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.pouch.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author romanrudenko
 */
@Entity
@Table(name = "user_types")
@NamedQueries({
    @NamedQuery(name = "UserTypes.findAll", query = "SELECT u FROM UserTypes u"),
    @NamedQuery(name = "UserTypes.findById", query = "SELECT u FROM UserTypes u WHERE u.id = :id"),
    @NamedQuery(name = "UserTypes.findByType", query = "SELECT u FROM UserTypes u WHERE u.type = :type")})
public class UserTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTypesId")
    private Collection<UserRoles> userRolesCollection;

    public UserTypes() {
    }

    public UserTypes(Integer id) {
        this.id = id;
    }

    public UserTypes(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<UserRoles> getUserRolesCollection() {
        return userRolesCollection;
    }

    public void setUserRolesCollection(Collection<UserRoles> userRolesCollection) {
        this.userRolesCollection = userRolesCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTypes userTypes = (UserTypes) o;

        if (id != null ? !id.equals(userTypes.id) : userTypes.id != null) return false;
        if (!type.equals(userTypes.type)) return false;
        if (userRolesCollection != null ? !userRolesCollection.equals(userTypes.userRolesCollection) : userTypes.userRolesCollection != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ua.bionic.pouch.entities.UserTypes[ id=" + id + " ]";
    }
    
}
