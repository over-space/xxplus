package com.xxplus.services.impl;

import com.xxbase.services.impl.BaseServiceImpl;
import com.xxplus.dao.AdminDao;
import com.xxbase.dao.BaseDao;
import com.xxplus.entity.AdminEntity;
import com.xxplus.services.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by lifang on 2015/1/22.
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<AdminEntity, Long> implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    @Autowired
    public void setBaseDao(BaseDao<AdminEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }

    @Override
    @Transactional(readOnly = true)
    public AdminEntity findByUsername(@NotBlank String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        return adminDao.findByUsername(username);
    }
}
