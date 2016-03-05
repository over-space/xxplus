package com.xxplus.code.entity;


import com.xxplus.code.enums.LoginModeEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lifang on 2015/1/23.
 */
@Entity
@Table(name = "t_sys_login_log")
public class LoginLogEntity extends BaseEntity {

    /**
     * 登录IP
     */
    @Column(length = 64, nullable = false)
    private String loginIp;

    /**
     * 登录时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginDate;

    /**
     * 登录模式
     */
    @Enumerated(EnumType.STRING)
    private LoginModeEnum loginMode;

    @OneToOne
    @JoinColumn(name = "adminId")
    private AdminEntity adminEntity;

    public LoginLogEntity(AdminEntity adminEntity, String loginIp, Date loginDate, LoginModeEnum loginMode) {
        this.adminEntity = adminEntity;
        this.loginIp = loginIp;
        this.loginDate = loginDate;
        this.loginMode = loginMode;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public LoginModeEnum getLoginMode() {
        return loginMode;
    }

    public void setLoginMode(LoginModeEnum loginMode) {
        this.loginMode = loginMode;
    }

    public AdminEntity getAdminEntity() {
        return adminEntity;
    }

    public void setAdminEntity(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }
}
