package com.xxplus.dao.impl;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.PathBuilder;
import com.xxbase.dao.impl.BaseDaoImpl;
import com.xxplus.dao.PermissionSourceDao;
import com.xxplus.entity.PermissionSourceEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by lifang on 2015/5/1.
 */
@Repository
public class PermissionSourceDaoImpl extends BaseDaoImpl<PermissionSourceEntity, Long> implements PermissionSourceDao {

    private PathBuilder<PermissionSourceEntity> pathBuilder = new PathBuilder<PermissionSourceEntity>(PermissionSourceEntity.class, "o");

    @Override
    public PermissionSourceEntity findBySubjectAndSourceAndAction(String subject, String source, String action) {
        JPAQuery jpaQuery = new JPAQuery(entityManager);
        return jpaQuery.from(pathBuilder).where(
                pathBuilder.getString("subject").eq(subject)
                        .and(pathBuilder.getString("source").eq(source))
                        .and(pathBuilder.getString("action").eq(action))).singleResult(pathBuilder);
    }
}
