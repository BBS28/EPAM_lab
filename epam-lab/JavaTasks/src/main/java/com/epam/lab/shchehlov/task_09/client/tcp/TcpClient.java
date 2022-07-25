package com.epam.lab.shchehlov.task_09.client.tcp;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import static com.epam.lab.shchehlov.task_09.constant.Constant.COMMAND_STOP_CLIENT;
import static com.epam.lab.shchehlov.task_09.constant.Constant.CONTINUE_COMMAND;
import static com.epam.lab.shchehlov.task_09.constant.Constant.EXCEPTION_READING_DATA;
import static com.epam.lab.shchehlov.task_09.constant.Constant.INPUT_COMMAND;
import static com.epam.lab.shchehlov.task_09.constant.Constant.TCP_PORT;

/**
 * The client thread class.
 *
 * @author B.Shchehlov.
 */
public class TcpClient extends Thread {
    private static final Logger log = Logger.getLogger(TcpClient.class.getName());

    /**
     * Accepts command input from the console and returns the server response.
     */
    @Override
    public void run() {
        while (true) {
            try (Socket socket = getSocket();
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                System.out.println(INPUT_COMMAND);
                Scanner scanner = new Scanner(System.in);
                String request = scanner.nextLine();

                bufferedWriter.write(request + System.lineSeparator());
                bufferedWriter.flush();
                String response = bufferedReader.readLine();
                System.out.println(response);

                System.out.println(CONTINUE_COMMAND);
                if (COMMAND_STOP_CLIENT.equals(scanner.nextLine())) {
                    break;
                }

            } catch (IOException e) {
                log.error(EXCEPTION_READING_DATA + ExceptionUtils.getStackTrace(e));
                return;
            }
        }
    }

    /**
     * Returns new socket.
     *
     * @return new socket.
     * @throws IOException in case of a problem obtaining an address.
     */
    public Socket getSocket() throws IOException {
        return new Socket(InetAddress.getLocalHost(), TCP_PORT);
    }
}
