package com.epam.lab.shchehlov.task_09.command.container;

import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_09.command.ServerCommand;
import com.epam.lab.shchehlov.task_09.command.impl.common.NoServerCommand;
import com.epam.lab.shchehlov.task_09.command.impl.http.GetCountHttpServerCommand;
import com.epam.lab.shchehlov.task_09.command.impl.http.GetItemHttpServerCommand;
import com.epam.lab.shchehlov.task_09.command.impl.tcp.GetCountTcpServerCommand;
import com.epam.lab.shchehlov.task_09.command.impl.tcp.GetItemTcpServerCommand;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.epam.lab.shchehlov.task_09.constant.Constant.COMMAND_COUNT;
import static com.epam.lab.shchehlov.task_09.constant.Constant.COMMAND_GET_COUNT;
import static com.epam.lab.shchehlov.task_09.constant.Constant.COMMAND_GET_ITEM;
import static com.epam.lab.shchehlov.task_09.constant.Constant.COMMAND_ITEM;
import static com.epam.lab.shchehlov.task_09.constant.Constant.COMMAND_NO_COMMAND;

/**
 * Container for storing server commands with assigned access keys.
 *
 * @author B.Shchehlov.
 */
public class ServerCommandContainer {
    private final Map<String, ServerCommand> serverCommands;

    public ServerCommandContainer(ProductService productService) {
        serverCommands = new LinkedHashMap<>();
        serverCommands.put(COMMAND_NO_COMMAND, new NoServerCommand());
        serverCommands.put(COMMAND_GET_COUNT, new GetCountTcpServerCommand(productService));
        serverCommands.put(COMMAND_GET_ITEM, new GetItemTcpServerCommand(productService));
        serverCommands.put(COMMAND_COUNT, new GetCountHttpServerCommand(productService));
        serverCommands.put(COMMAND_ITEM, new GetItemHttpServerCommand(productService));
    }

    /**
     * Returns the selected command from the list by key.
     *
     * @param commandName command name as key.
     * @return the selected command.
     */
    public ServerCommand getServerCommand(String commandName) {
        if (!serverCommands.containsKey(commandName)) {
            return serverCommands.get(COMMAND_NO_COMMAND);
        }
        return serverCommands.get(commandName);
    }
}
