package com.xxplus.services;


import com.xxbase.services.BaseService;
import com.xxplus.entity.AdminEntity;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by lifang on 2015/1/22.
 */
public abstract interface AdminService extends BaseService<AdminEntity, Long> {

    public AdminEntity findByUsername(@NotBlank String username);

}
