package ua.miratech.rudenko.docstore.persistence;

import org.springframework.stereotype.Component;
import ua.miratech.rudenko.docstore.domain.Shared;

/**
 * Created by RomanR on 2/19/14.
 */
@Component
public interface SharedMapper {

    void insertShared (Shared shared);

    Integer selectSharedCount (Shared shared);
}
