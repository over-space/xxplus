package com.xxplus.dao.impl;

import com.xxbase.dao.impl.BaseDaoImpl;
import com.xxplus.dao.LoginLogDao;
import com.xxplus.entity.LoginLogEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by lifang on 2015/1/24.
 */
@Repository
public class LoginLogDaoImpl extends BaseDaoImpl<LoginLogEntity, Long> implements LoginLogDao {
}
