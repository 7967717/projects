package ua.miratech.rudenko.docstore.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.miratech.rudenko.docstore.domain.Articles;
import ua.miratech.rudenko.docstore.domain.Shared;
import ua.miratech.rudenko.docstore.persistence.SharedMapper;
import ua.miratech.rudenko.docstore.persistence.UsersMapper;

/**
 * Created by RomanR on 2/19/14.
 */
@Service
public class SharedService {

    public static final Logger LOG = Logger.getLogger("rootLogger");

    @Autowired
    SharedMapper sharedMapper;
    @Autowired
    UsersMapper usersMapper;

    public void insertShared(Shared shared) {
        sharedMapper.insertShared(shared);
    }

    public boolean checkAccessRights(Articles article, String username) {
        LOG.info("entered checkAccessRights");
        Boolean check = false;
        Shared shared = new Shared();
        shared.setId(article.getId());
        shared.setOwner(usersMapper.getNameById(article.getIdOwner()));
        shared.setGrantee(username);
        LOG.info("shared count = " + sharedMapper.selectSharedCount(shared));
        if (sharedMapper.selectSharedCount(shared) > 0){
            check = true;
        }
        return check;
    }


}
