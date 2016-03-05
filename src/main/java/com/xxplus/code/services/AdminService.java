package com.xxplus.code.services;


import com.xxplus.code.entity.AdminEntity;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by lifang on 2015/1/22.
 */
public abstract interface AdminService extends BaseService<AdminEntity, Long> {

    public AdminEntity findByUsername(@NotBlank String username);

}
