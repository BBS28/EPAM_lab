package com.epam.lab.shchehlov.task_06.strategy.creator;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;

/**
 * General interface for specific classes Creator represented command pattern
 *
 * @author B.Shchehlov
 */
public interface Creator {

    /**
     * The main command method for creating concrete instance of PowerTool object
     *
     * @return instance of PowerTool
     */
    PowerTool create();

    /**
     * Returns description of the Creator
     *
     * @return String description of the Creator
     */
    String getDescription();
}
