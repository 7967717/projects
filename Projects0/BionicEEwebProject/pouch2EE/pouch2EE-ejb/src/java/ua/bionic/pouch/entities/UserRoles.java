/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.pouch.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author romanrudenko
 */
@Entity
@Table(name = "user_roles")
@NamedQueries({
    @NamedQuery(name = "UserRoles.findAll", query = "SELECT u FROM UserRoles u"),
    @NamedQuery(name = "UserRoles.findById", query = "SELECT u FROM UserRoles u WHERE u.id = :id"),
    @NamedQuery(name = "UserRoles.findByType", query = "SELECT u FROM UserRoles u WHERE u.type = :type"),
    @NamedQuery(name = "UserRoles.findByLogin", query = "SELECT u FROM UserRoles u WHERE u.login = :login")})
public class UserRoles implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "login")
    private String login;
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users usersId;
    @JoinColumn(name = "user_types_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserTypes userTypesId;

    public UserRoles() {
    }

    public UserRoles(Integer id) {
        this.id = id;
    }

    public UserRoles(Integer id, String type, String login) {
        this.id = id;
        this.type = type;
        this.login = login;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Users getUsersId() {
        return usersId;
    }

    public void setUsersId(Users usersId) {
        this.usersId = usersId;
    }

    public UserTypes getUserTypesId() {
        return userTypesId;
    }

    public void setUserTypesId(UserTypes userTypesId) {
        this.userTypesId = userTypesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoles userRoles = (UserRoles) o;

        if (id != null ? !id.equals(userRoles.id) : userRoles.id != null) return false;
        if (!login.equals(userRoles.login)) return false;
        if (!type.equals(userRoles.type)) return false;
        if (userTypesId != null ? !userTypesId.equals(userRoles.userTypesId) : userRoles.userTypesId != null)
            return false;
        if (usersId != null ? !usersId.equals(userRoles.usersId) : userRoles.usersId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + type.hashCode();
        result = 31 * result + login.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return "ua.bionic.pouch.entities.UserRoles[ id=" + id + " ]";
    }
    
}
