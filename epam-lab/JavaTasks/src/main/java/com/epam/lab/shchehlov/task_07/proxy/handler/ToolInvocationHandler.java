package com.epam.lab.shchehlov.task_07.proxy.handler;

import com.epam.lab.shchehlov.task_07.proxy.constant.Constant;
import com.epam.lab.shchehlov.task_07.proxy.domain.Tool;
import com.epam.lab.shchehlov.task_07.proxy.exception.UnacceptableMethodException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Class that intercepts method calls with the prefix set and throws an exception
 *
 * @author B. Shchehlov
 */
public class ToolInvocationHandler implements InvocationHandler {

    private final Tool tool;

    public ToolInvocationHandler(Tool tool) {
        this.tool = tool;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith(Constant.PREFIX_SET)) {
            throw new UnacceptableMethodException(Constant.UNACCEPTABLE_METHOD);
        }
        return method.invoke(tool, args);
    }
}
