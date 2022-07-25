package com.epam.lab.shchehlov.task_09.command.impl.tcp;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_09.command.ServerCommand;

import java.util.Objects;

import static com.epam.lab.shchehlov.task_09.constant.Constant.FORMAT_NO_PRODUCT;
import static com.epam.lab.shchehlov.task_09.constant.Constant.FORMAT_OUTPUT_GET_ITEM;
import static com.epam.lab.shchehlov.task_09.constant.Constant.REGEX_EQUALS;
import static com.epam.lab.shchehlov.task_09.constant.Constant.SECOND_INDEX;

/**
 * Handles execution of getting item info when processed at tcp server.
 *
 * @author B.Shchehlov.
 */
public class GetItemTcpServerCommand implements ServerCommand {
    private final ProductService productService;

    public GetItemTcpServerCommand(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(String request) {
        long id = Long.parseLong(request.trim().split(REGEX_EQUALS)[SECOND_INDEX]);
        PowerTool powerTool = productService.getOne(id);

        if (Objects.isNull(powerTool)) {
            return String.format(FORMAT_NO_PRODUCT, id);
        }

        return String.format(FORMAT_OUTPUT_GET_ITEM, powerTool.getName(), powerTool.getPrice());
    }
}
