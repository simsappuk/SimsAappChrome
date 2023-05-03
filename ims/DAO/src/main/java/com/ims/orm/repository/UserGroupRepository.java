package com.ims.orm.repository;

import com.ims.orm.common.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("usergroupRepository")
public interface UserGroupRepository extends JpaRepository<UserGroup, String>{


}
