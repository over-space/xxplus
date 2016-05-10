package com.xxplus.dao;


import com.xxbase.dao.BaseDao;
import com.xxplus.entity.PermissionSourceEntity;

/**
 * Created by lifang on 2015/5/1.
 */
public abstract interface PermissionSourceDao extends BaseDao<PermissionSourceEntity, Long> {

    public PermissionSourceEntity findBySubjectAndSourceAndAction(String subject, String source, String action);

}
