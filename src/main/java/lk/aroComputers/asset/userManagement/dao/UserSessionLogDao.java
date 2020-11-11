package lk.aroComputers.asset.userManagement.dao;

import lk.aroComputers.asset.userManagement.entity.Enum.UserSessionLogStatus;
import lk.aroComputers.asset.userManagement.entity.User;
import lk.aroComputers.asset.userManagement.entity.UserSessionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionLogDao extends JpaRepository< UserSessionLog, Integer > {
    UserSessionLog findByUserAndUserSessionLogStatus(User user, UserSessionLogStatus userSessionLogStatus);
}
