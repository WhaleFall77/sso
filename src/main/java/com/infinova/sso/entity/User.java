package com.infinova.sso.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

/**
 * 用户对象
 *
 * @author wangh
 * @date 2020/5/30 18:34
 */
@Entity
@Table(name = "tsie_user")
public class User implements Serializable {

    /**
     * 用户有效，可以登录
     */
    public static final Integer ENABLE_YES = 1;

    /**
     * 用户无效，不可以登录
     */
    public static final Integer ENABLE_NO = 0;

    /**
     * 提示用户修改密码
     */
    public static final Integer PASSWD_CHANGE_PROMPT_YES = 1;

    /**
     * 不提示用户修改密码
     */
    public static final Integer PASSWD_CHANGE_PROMPT_NO = 0;

    @Id
    @Column(name = "id", length = 32)
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    private String username;

    private Integer enable;

    private String passwd;

    private String nickname;

    private String email;

    private String phoneno;

    private Integer loginMode;

    private Integer passwdChangePrompt;

    private Date expiredDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "USERNAME", length = 32, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "ENABLE", length = 1, nullable = false)
    @ColumnDefault("1")
    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    @Column(name = "PASSWD", length = 64, nullable = false)
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Column(name = "NICKNAME", length = 20)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "EMAIL", length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "PHONENO", length = 21)
    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    @Column(name = "LOGIN_MODE", length = 1)
    public Integer getLoginMode() {
        return loginMode;
    }

    public void setLoginMode(Integer loginMode) {
        this.loginMode = loginMode;
    }

    /**
     * 密码修改提示，当管理员新增用户或修改了用户名密码，且tsie.passwdChangePrompt=true时需要提示用户修改密码
     */
    @Column(name = "PASSWD_CHANGE_PROMPT", length = 1)
    @ColumnDefault("0")
    public Integer getPasswdChangePrompt() {
        return passwdChangePrompt;
    }

    public void setPasswdChangePrompt(Integer passwdChangePrompt) {
        this.passwdChangePrompt = passwdChangePrompt;
    }

    /**
     * 账号过期日期，如果为空则永不过期
     */
    @Column(name = "EXPIRED_DATE")
    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }


    @Override
    public int hashCode() {

        return Objects.hash(id, username);
    }

}
