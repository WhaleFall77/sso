/**
 * Copyright (C), 2015‐2020, 北京清能互联科技有限公司 Author:  gss Date:  2020/6/1 10:13 History:
 * <author> <time> <version> <desc>
 */
package com.infinova.sso.service_test;

import com.infinova.sso.entity.User;
import com.infinova.sso.repository.UserRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description:  <br>
 *
 * @author gss
 * @create 2020/6/1 
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFind() {
        Optional<User> entity= this.userRepository.findById("4028828b6db9d7ac016db9db4dc20000");
        System.out.println(entity.get().getNickname());
    }


}
