package ua.miratech.rudenko.docstore.persistence;

import org.springframework.stereotype.Component;
import ua.miratech.rudenko.docstore.domain.Users;

import java.util.List;

/**
 * Created by RomanR on 1/21/14.
 */
@Component
public interface UsersMapper {

    Users getUser(String userId);

    Users getByUsername (String username);

    Integer getIdByName (String username);

    String getNameById (Integer id);

    List<Users> getAllUsers();

    Integer selectNewUserId ();

    void insertUser(Users user);

    void insertUserRole(Users user);

}
