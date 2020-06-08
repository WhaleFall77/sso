package com.infinova.sso.repository;

import com.infinova.sso.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: wangh
 * @Date: 2020/6/1 10:11
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserByUsername(String userName);

}
