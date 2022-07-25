package com.epam.lab.shchehlov.task_09.client;

import com.epam.lab.shchehlov.task_09.client.tcp.TcpClient;
import com.epam.lab.shchehlov.task_09.constant.Constant;
import org.apache.log4j.Logger;

/**
 * Class that starts client thread.
 *
 * @author B.Shchehlov.
 */
public class ClientApp {
    private static final Logger log = Logger.getLogger(ClientApp.class.getName());

    public static void main(String[] args) {
        Thread client = new TcpClient();

        client.start();
        try {
            client.join();
        } catch (InterruptedException e) {
            log.error(Constant.EXCEPTION_INTERRUPTED);
            Thread.currentThread().interrupt();
        }
    }
}
