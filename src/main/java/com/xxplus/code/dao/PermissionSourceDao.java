package com.xxplus.code.dao;


import com.xxplus.code.entity.PermissionSourceEntity;

/**
 * Created by lifang on 2015/5/1.
 */
public abstract interface PermissionSourceDao extends BaseDao<PermissionSourceEntity, Long> {

    public PermissionSourceEntity findBySubjectAndSourceAndAction(String subject, String source, String action);

}
