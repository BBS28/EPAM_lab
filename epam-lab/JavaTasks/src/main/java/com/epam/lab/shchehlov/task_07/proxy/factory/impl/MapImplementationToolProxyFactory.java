package com.epam.lab.shchehlov.task_07.proxy.factory.impl;

import com.epam.lab.shchehlov.task_07.proxy.domain.Tool;
import com.epam.lab.shchehlov.task_07.proxy.factory.ProxyFactory;
import com.epam.lab.shchehlov.task_07.proxy.handler.ToolMapInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * Class for creating proxy which denies access to methods with prefix set
 *
 * @author B. Shchehlov
 */
public class MapImplementationToolProxyFactory implements ProxyFactory {

    @Override
    public Tool createProxy(Tool tool) throws IllegalArgumentException {
        ClassLoader toolClazzLoader = tool.getClass().getClassLoader();
        Class[] interfaces = tool.getClass().getInterfaces();
        ToolMapInvocationHandler toolMapInvocationHandler = new ToolMapInvocationHandler(tool);
        return (Tool) Proxy.newProxyInstance(toolClazzLoader, interfaces, toolMapInvocationHandler);
    }
}
