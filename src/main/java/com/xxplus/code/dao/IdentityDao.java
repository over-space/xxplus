package com.xxplus.code.dao;


import com.xxplus.code.entity.IdentityEntity;

/**
 * Created by lifang on 2015/2/1.
 */
public abstract interface IdentityDao extends BaseDao<IdentityEntity, Long> {

    public IdentityEntity findByClazz(String clazz);

}
