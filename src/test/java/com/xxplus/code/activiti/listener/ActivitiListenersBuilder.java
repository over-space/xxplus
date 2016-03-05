package com.xxplus.code.activiti.listener;

import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.bpmn.model.ImplementationType;

import java.util.List;

/**
 * Created by admin on 2015/12/20.
 */
public abstract class ActivitiListenersBuilder {

    public abstract Class getImplClassName(String event);

    public <T> void addListener(List<ActivitiListener> activitiListeners, String event) {
        if (activitiListeners == null)
            return;

        Class implClass = getImplClassName(event);
        if (implClass == null)
            return;

        ActivitiListener activitiListener = createActivitiListener(event, implClass);
        if (activitiListener != null)
            activitiListeners.add(activitiListener);
    }

    protected <T> ActivitiListener createActivitiListener(String event, Class<T> implClass) {
        ActivitiListener listener = new ActivitiListener();
        listener.setImplementationType(ImplementationType.IMPLEMENTATION_TYPE_CLASS);
        listener.setImplementation(implClass.getName());
        listener.setEvent(event);
        return listener;
    }

}
