package com.xxplus.code.services.impl;

import com.xxplus.code.dao.BaseDao;
import com.xxplus.code.dao.PermissionSourceDao;
import com.xxplus.code.entity.PermissionSourceEntity;
import com.xxplus.code.services.PermissionSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifang on 2015/5/1.
 */
@Service
public class PermissionSourceServiceImpl extends BaseServiceImpl<PermissionSourceEntity, Long> implements PermissionSourceService {

    @Autowired
    private PermissionSourceDao permissionSourceDao;

    @Override
    @Autowired
    public void setBaseDao(BaseDao<PermissionSourceEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }

    @Override
    public PermissionSourceEntity findBySubjectAndSourceAndAction(String subject, String source, String action) {
        return permissionSourceDao.findBySubjectAndSourceAndAction(subject, source, action);
    }

}
