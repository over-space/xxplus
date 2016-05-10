package com.xxplus.services.impl;


import com.xxbase.dao.BaseDao;
import com.xxbase.services.impl.BaseServiceImpl;
import com.xxplus.entity.LoginLogEntity;
import com.xxplus.services.LoginLogService;
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
