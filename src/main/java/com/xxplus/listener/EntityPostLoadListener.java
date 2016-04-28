package com.xxplus.listener;

import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;

/**
 * Created by admin on 2016-04-10.
 */
public class EntityPostLoadListener implements PostLoadEventListener{

    @Override
    public void onPostLoad(PostLoadEvent postLoadEvent) {
        System.out.println("================================");
    }
}
