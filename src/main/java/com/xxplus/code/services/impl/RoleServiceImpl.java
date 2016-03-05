package com.xxplus.code.services.impl;


import com.xxplus.code.dao.BaseDao;
import com.xxplus.code.entity.RoleEntity;
import com.xxplus.code.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifang on 2015/4/18.
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleEntity, Long> implements RoleService {

    @Override
    @Autowired
    public void setBaseDao(BaseDao<RoleEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }

}
