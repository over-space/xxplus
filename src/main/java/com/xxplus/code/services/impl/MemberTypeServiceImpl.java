package com.xxplus.code.services.impl;

import com.xxplus.code.dao.BaseDao;
import com.xxplus.code.entity.MemberTypeEntity;
import com.xxplus.code.services.MemberTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifang on 2015/1/31.
 */
@Service
public class MemberTypeServiceImpl extends BaseServiceImpl<MemberTypeEntity, Long> implements MemberTypeService {

    @Override
    @Autowired
    public void setBaseDao(BaseDao<MemberTypeEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }
}
