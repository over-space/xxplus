package com.xxplus.services;


import com.xxbase.services.BaseService;
import com.xxplus.entity.PermissionSourceEntity;

/**
 * Created by lifang on 2015/5/1.
 */
public abstract interface PermissionSourceService extends BaseService<PermissionSourceEntity, Long> {

    public PermissionSourceEntity findBySubjectAndSourceAndAction(String subject, String source, String action);

}
