package ua.miratech.rudenko.docstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.miratech.rudenko.docstore.domain.Users;
import ua.miratech.rudenko.docstore.persistence.UsersMapper;

import java.util.List;

/**
 * Created by RomanR on 1/21/14.
 * //
 */
@Service
public class UsersService {

    @Autowired
    UsersMapper usersMapper;

    public Users getUser(String userId) {
        return usersMapper.getUser(userId);
    }

    public Users getByUsername (String username) {
        return usersMapper.getByUsername(username);
    }

    public Integer getIdByName (String username) {
        return usersMapper.getIdByName(username);
    }

    public List<Users> getAllUsers() {
        return usersMapper.getAllUsers();
    }

    @Transactional
    public void registerUser(Users user) {
        user.setUserId(usersMapper.selectNewUserId());
        usersMapper.insertUser(user);
        usersMapper.insertUserRole(user);
    }

}
