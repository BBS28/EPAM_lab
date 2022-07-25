package com.epam.lab.shchehlov.task_07.proxy.handler;

import com.epam.lab.shchehlov.task_07.proxy.constant.Constant;
import com.epam.lab.shchehlov.task_07.proxy.domain.Tool;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that intercepts calls to setters and getters and turns them into calls put and set methods of Map
 *
 * @author B. Shchehlov
 */
public class ToolMapInvocationHandler implements InvocationHandler {
    private static final Logger log = Logger.getLogger(ToolMapInvocationHandler.class.getName());
    private final Map<String, Object> toolFields;

    public ToolMapInvocationHandler(Tool tool) {
        toolFields = new HashMap<>();
        Field[] fields = tool.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                toolFields.put(field.getName(), field.get(tool));
            } catch (IllegalAccessException e) {
                log.error(Constant.INVALID_ACCESS, e);
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        StringBuilder fieldName = new StringBuilder(method.getName().substring(Constant.PREFIX_LENGTH));
        fieldName.setCharAt(0, Character.toLowerCase(fieldName.charAt(0)));

        if (method.getName().startsWith(Constant.PREFIX_GET)) {
            return toolFields.get(fieldName.toString());
        }
        if (method.getName().startsWith(Constant.PREFIX_SET)) {
            return toolFields.put(fieldName.toString(), args[0]);
        }

        return method.invoke(this, args);
    }

    @Override
    public String toString() {
        return "ToolMapInvocationHandler{" +
                "toolFields=" + toolFields +
                '}';
    }
}
