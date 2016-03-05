package com.xxplus.code.services;


import com.xxplus.code.entity.PermissionSourceEntity;

/**
 * Created by lifang on 2015/5/1.
 */
public abstract interface PermissionSourceService extends BaseService<PermissionSourceEntity, Long> {

    public PermissionSourceEntity findBySubjectAndSourceAndAction(String subject, String source, String action);

}
