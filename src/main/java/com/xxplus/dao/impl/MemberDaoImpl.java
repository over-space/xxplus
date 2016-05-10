package com.xxplus.dao.impl;

import com.xxbase.dao.impl.BaseDaoImpl;
import com.xxplus.dao.MemberDao;
import com.xxplus.entity.MemberEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by lifang on 2015/4/18.
 */
@Repository
public class MemberDaoImpl extends BaseDaoImpl<MemberEntity, Long> implements MemberDao {
}
