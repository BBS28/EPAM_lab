package com.epam.lab.shchehlov.task_07.proxy.factory;

import com.epam.lab.shchehlov.task_07.proxy.domain.Tool;

/**
 * Interface of class that creates proxy of class that implements Tool
 *
 * @author B. Shchehlov
 */
public interface ProxyFactory {

    /**
     * Returns the proxy class passed in parameters
     *
     * @param tool object of class that implements Tool
     * @return proxy class
     */
    Tool createProxy(Tool tool);
}
