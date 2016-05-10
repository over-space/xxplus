package com.xxplus.services.impl;


import com.xxbase.dao.BaseDao;
import com.xxbase.services.impl.BaseServiceImpl;
import com.xxplus.entity.MenuEntity;
import com.xxplus.services.MenuService;
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
