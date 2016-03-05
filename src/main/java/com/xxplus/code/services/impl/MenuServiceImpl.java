package com.xxplus.code.services.impl;


import com.xxplus.code.dao.BaseDao;
import com.xxplus.code.entity.MenuEntity;
import com.xxplus.code.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lifang on 2015/1/31.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuEntity, Long> implements MenuService {

    @Override
    @Autowired
    public void setBaseDao(BaseDao<MenuEntity, Long> baseDao) {
        super.setBaseDao(baseDao);
    }
}
