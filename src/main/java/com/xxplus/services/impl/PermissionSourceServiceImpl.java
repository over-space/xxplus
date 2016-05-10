package com.xxplus.services.impl;

import com.xxbase.dao.BaseDao;
import com.xxbase.services.impl.BaseServiceImpl;
import com.xxplus.dao.PermissionSourceDao;
import com.xxplus.entity.PermissionSourceEntity;
import com.xxplus.services.PermissionSourceService;
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
