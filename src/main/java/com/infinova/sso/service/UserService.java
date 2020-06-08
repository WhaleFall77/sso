/**
 * Copyright (C), 2015‐2020, 北京清能互联科技有限公司 Author:  gss Date:  2020/6/1 14:15 History:
 * <author> <time> <version> <desc>
 */
package com.infinova.sso.service;

import com.infinova.sso.entity.User;
import com.infinova.sso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:  <br>
 *
 * @author wangh
 * @create 2020/6/1 
 * @since 1.0.0
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User findByName(String userName) {
        return  userRepository.findUserByUsername(userName);

    }

}
