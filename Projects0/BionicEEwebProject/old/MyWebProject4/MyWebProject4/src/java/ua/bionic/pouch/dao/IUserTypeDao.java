package ua.bionic.pouch.dao;


import java.util.List;
import ua.bionic.pouch.beans.UserType;

public interface IUserTypeDao {
    void create(UserType userType);
    UserType read(int id);
    void update(UserType userType);
    void delete(UserType userType);
    List<UserType> findAll();
}
