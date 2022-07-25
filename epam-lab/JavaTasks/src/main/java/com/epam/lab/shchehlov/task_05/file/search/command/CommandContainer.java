package com.epam.lab.shchehlov.task_05.file.search.command;

import com.epam.lab.shchehlov.task_05.file.search.command.impl.ExtensionFilterCommand;
import com.epam.lab.shchehlov.task_05.file.search.command.impl.ModifyingDateFilterCommand;
import com.epam.lab.shchehlov.task_05.file.search.command.impl.NameFilterCommand;
import com.epam.lab.shchehlov.task_05.file.search.command.impl.SizeFilterCommand;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Container for storing commands
 *
 * @author B.Shchehlov
 */
public class CommandContainer implements Iterable<Command> {
    private final List<Command> commands;

    public CommandContainer() {
        commands = new LinkedList<>();

        commands.add(new NameFilterCommand());
        commands.add(new ExtensionFilterCommand());
        commands.add(new SizeFilterCommand());
        commands.add(new ModifyingDateFilterCommand());
    }

    @Override
    public Iterator<Command> iterator() {
        return commands.iterator();
    }
}
