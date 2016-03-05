package com.xxplus.code.services.impl;


import com.xxplus.code.dao.BaseDao;
import com.xxplus.code.entity.LoginLogEntity;
import com.xxplus.code.services.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifang on 2015/1/24.
 */
@Service
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLogEntity, Long> implements LoginLogService {

    @Override
    @Autowired
    public void setBaseDao(BaseDao<LoginLogEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }
}
