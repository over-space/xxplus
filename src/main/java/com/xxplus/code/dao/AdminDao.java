package com.xxplus.code.dao;


import com.xxplus.code.entity.AdminEntity;

/**
 * Created by lifang on 2015/1/22.
 */
public abstract interface AdminDao extends BaseDao<AdminEntity, Long> {

    public AdminEntity findByUsername(String username);

}
