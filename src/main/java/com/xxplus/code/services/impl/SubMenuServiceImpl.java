package com.xxplus.code.services.impl;

import com.xxplus.code.dao.BaseDao;
import com.xxplus.code.entity.SubMenuEntity;
import com.xxplus.code.services.SubMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifang on 2015/1/31.
 */
@Service
public class SubMenuServiceImpl extends BaseServiceImpl<SubMenuEntity, Long> implements SubMenuService {

    @Override
    @Autowired
    public void setBaseDao(BaseDao<SubMenuEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }
}
