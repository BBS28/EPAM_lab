package com.epam.lab.shchehlov.task_07.proxy.factory.impl;

import com.epam.lab.shchehlov.task_07.proxy.domain.Tool;
import com.epam.lab.shchehlov.task_07.proxy.factory.ProxyFactory;
import com.epam.lab.shchehlov.task_07.proxy.handler.ToolInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * Class for creating proxy which implements the interface of the product based on the Map
 *
 * @author B. Shchehlov
 */
public class UnmodifiableToolProxyFactory implements ProxyFactory {

    @Override
    public Tool createProxy(Tool tool) {
        ClassLoader toolClazzLoader = tool.getClass().getClassLoader();
        Class[] interfaces = tool.getClass().getInterfaces();
        ToolInvocationHandler toolInvocationHandler = new ToolInvocationHandler(tool);
        return (Tool) Proxy.newProxyInstance(toolClazzLoader, interfaces, toolInvocationHandler);
    }
}
