package com.xxplus.dao;


import com.xxbase.dao.BaseDao;
import com.xxplus.entity.IdentityEntity;

/**
 * Created by lifang on 2015/2/1.
 */
public abstract interface IdentityDao extends BaseDao<IdentityEntity, Long> {

    public IdentityEntity findByClazz(String clazz);

}
