package com.infinova.sso.service;

import com.auth0.jwt.interfaces.Claim;
import com.infinova.sso.common.AppConstants;
import com.infinova.sso.entity.User;
import com.infinova.sso.exception.CustomException;
import com.infinova.sso.util.JwtUtil;
import com.infinova.sso.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 类功能简述： 类功能详述：
 *
 * @author wangh
 * @date 2020/5/30 18:17
 */
@Service
public class JwtService {


    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(JwtService.class);

    /**
     * jwt token超时时间，单位ms
     */
    private static int expireTime;

    @Value("${jwt_expire_time}")
    public void setExpireTime(int expireTime) {
        JwtService.expireTime = expireTime * 60 * 1000;
    }

    /**
     * Description:登录获取token
     *
     * @param user user
     * @return java.lang.String
     * @author wangh
     * @date 2020/5/30 18:45
     */
    public String login(User user) throws Exception {
        User entity = this.userService.findByName(user.getUsername());
        if (entity != null && entity.getPasswd().equals(user.getPasswd())) {
            return this.generateNewJwt(entity.getId(), entity.getNickname());
        } else {
            throw new CustomException("账号密码错误");
        }
    }

    /**
     * 过期时间小于半小时，返回新的jwt，否则返回原jwt 测试
     */
    public String refreshJwt(String jwt) {
        String secret = RedisUtil.redisTemplate.opsForValue().get(jwt);
        Map<String, Claim> map = JwtUtil.decode(jwt, secret);
        if (map.get(AppConstants.EXP).asLong() * 1000 - System.currentTimeMillis() / 1000 < 30 * 60 * 1000) {
            return this.generateNewJwt(map.get(AppConstants.USER_ID).asString(),map.get(AppConstants.NICK_NAME).asString());
        } else {
            return jwt;
        }
    }


    /**
     * Description: 生成新的jwt,并放入jwtMap中
     *
     * @return java.lang.String
     * @author wangh date 2020/5/30 10:44
     */
    private String generateNewJwt(String userId, String nickName) {
        String secret = UUID.randomUUID().toString().replaceAll("-", "");
        String token = JwtUtil.encode(userId, nickName, secret, expireTime);
        RedisUtil.set(token, secret, expireTime);
        return token;
    }

    /**
     * Description:检查jwt有效性
     *
     * @return Boolean
     * @author wangh
     * @date 2020/5/30 18:47
     */
    public Boolean checkJwt(String jwt) {
        try {
            String secret = RedisUtil.redisTemplate.opsForValue().get(jwt);
            Map<String, Claim> decode = JwtUtil.decode(jwt, secret);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Description: 使该jwt失效
     *
     * @author wangh
     * @date 2020/5/30 19:58
     */
    public void inValid(String jwt) {
        RedisUtil.delete(jwt);
    }

}
