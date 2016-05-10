package com.xxblog.entity;

import com.xxbase.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by admin on 16/05/09.
 */
@Entity
@Table(name = "t_blog_category")
public class BlogCategoryEntity extends BaseEntity{

    @Column(length = 32)
    private String name;

    private boolean isEnable = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
