package com.xxplus.dao;


import com.xxbase.dao.BaseDao;
import com.xxplus.entity.CompanyEntity;

/**
 * Created by lifang on 2015/1/31.
 */
public abstract interface CompanyDao extends BaseDao<CompanyEntity, Long> {

    /**
     * 根据编号查询
     *
     * @param companyNo 编号
     * @return
     */
    public CompanyEntity findByCompanyNo(String companyNo);

}
