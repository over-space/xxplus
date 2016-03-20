package com.xxplus.dao.impl;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.PathBuilder;
import com.xxplus.dao.AdminDao;
import com.xxplus.entity.AdminEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by lifang on 2015/1/22.
 */
@Repository
public class AdminDaoImpl extends BaseDaoImpl<AdminEntity, Long> implements AdminDao {

    @Override
    public AdminEntity findByUsername(String username) {
        AdminEntity adminEntity = null;
        try {
            PathBuilder<AdminEntity> pb = new PathBuilder<AdminEntity>(AdminEntity.class, "o");
            JPAQuery query = new JPAQuery(entityManager);
            adminEntity = query.from(pb).where(pb.getString("username").eq(username)).singleResult(pb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminEntity;
    }
}
