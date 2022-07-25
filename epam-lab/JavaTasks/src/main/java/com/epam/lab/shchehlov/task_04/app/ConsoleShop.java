package com.epam.lab.shchehlov.task_04.app;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.command.CommandContainer;
import com.epam.lab.shchehlov.task_04.console.ConsoleMenu;
import com.epam.lab.shchehlov.task_04.repository.AddedProductsRepository;
import com.epam.lab.shchehlov.task_04.repository.BasketRepository;
import com.epam.lab.shchehlov.task_04.repository.OrderRepository;
import com.epam.lab.shchehlov.task_04.repository.impl.AddedProductsRepositoryImpl;
import com.epam.lab.shchehlov.task_04.repository.impl.BasketRepositoryImpl;
import com.epam.lab.shchehlov.task_04.repository.impl.OrderRepositoryImpl;
import com.epam.lab.shchehlov.task_04.repository.impl.ProductRepositoryImpl;
import com.epam.lab.shchehlov.task_04.service.AddedProductsService;
import com.epam.lab.shchehlov.task_04.service.BasketService;
import com.epam.lab.shchehlov.task_04.service.OrderService;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_04.service.impl.AddedProductServiceImpl;
import com.epam.lab.shchehlov.task_04.service.impl.BasketServiceImpl;
import com.epam.lab.shchehlov.task_04.service.impl.OrderServiceImpl;
import com.epam.lab.shchehlov.task_04.service.impl.ProductServiceImpl;
import com.epam.lab.shchehlov.task_09.command.container.ServerCommandContainer;
import com.epam.lab.shchehlov.task_09.server.impl.HttpServer;
import com.epam.lab.shchehlov.task_09.server.impl.TcpServer;

import java.util.Scanner;

/**
 * Console store application class
 *
 * @author B.Shchehlov
 */
public class ConsoleShop {
    private static final int TCP_PORT = 3000;
    private static final int HTTP_PORT = 8080;

    private ProductService productService;
    private BasketService basketService;
    private OrderService orderService;
    private AddedProductsService addedProductsService;
    private CommandContainer commandContainer;
    private ConsoleMenu consoleMenu;

    public ConsoleShop(ConsoleShopBuilder consoleShopBuilder) {
        this.productService = consoleShopBuilder.productService;
        this.basketService = consoleShopBuilder.basketService;
        this.orderService = consoleShopBuilder.orderService;
        this.addedProductsService = consoleShopBuilder.addedProductsService;
        this.commandContainer = consoleShopBuilder.commandContainer;
        this.consoleMenu = consoleShopBuilder.consoleMenu;
    }

    public ConsoleMenu getConsoleMenu() {
        return consoleMenu;
    }

    public void setConsoleMenu(ConsoleMenu consoleMenu) {
        this.consoleMenu = consoleMenu;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setBasketService(BasketService basketService) {
        this.basketService = basketService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setAddedProductsService(AddedProductsService addedProductsService) {
        this.addedProductsService = addedProductsService;
    }

    public void setCommandContainer(CommandContainer commandContainer) {
        this.commandContainer = commandContainer;
    }

    public ProductService getProductService() {
        return productService;
    }

    public BasketService getBasketService() {
        return basketService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public AddedProductsService getAddedProductsService() {
        return addedProductsService;
    }

    public CommandContainer getCommandContainer() {
        return commandContainer;
    }

    public static class ConsoleShopBuilder {
        private ProductService productService;
        private BasketService basketService;
        private OrderService orderService;
        private AddedProductsService addedProductsService;
        private CommandContainer commandContainer;
        private ConsoleMenu consoleMenu;

        public ConsoleShopBuilder() {
        }

        public ConsoleShopBuilder setProductService(PowerTool[] products) {
            this.productService = new ProductServiceImpl(new ProductRepositoryImpl(products));
            return this;
        }

        public ConsoleShop build() {
            BasketRepository basketRepository = new BasketRepositoryImpl();
            OrderRepository orderRepository = new OrderRepositoryImpl();
            AddedProductsRepository addedProductsRepository = new AddedProductsRepositoryImpl();

            ServerCommandContainer serverCommandContainer = new ServerCommandContainer(productService);
            new TcpServer(productService, serverCommandContainer, TCP_PORT).start();
            new HttpServer(productService, serverCommandContainer, HTTP_PORT).start();

            Scanner scanner = new Scanner(System.in);
            basketService = new BasketServiceImpl(basketRepository);
            orderService = new OrderServiceImpl(orderRepository, basketRepository);
            addedProductsService = new AddedProductServiceImpl(addedProductsRepository);
            commandContainer = new CommandContainer(productService, basketService, orderService, addedProductsService, scanner);
            consoleMenu = new ConsoleMenu(commandContainer, productService, scanner);

            return new ConsoleShop(this);
        }
    }
}
