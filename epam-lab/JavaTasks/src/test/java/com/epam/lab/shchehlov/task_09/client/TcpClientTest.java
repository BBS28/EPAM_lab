package com.epam.lab.shchehlov.task_09.client;

import com.epam.lab.shchehlov.task_09.client.tcp.TcpClient;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import static java.lang.System.lineSeparator;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TcpClientTest {
    private static final String RESPONSE = "response";
    private static final String EXIT_COMMAND = "0";
    private static final String LINE_SEPARATOR = lineSeparator();

    @InjectMocks
    private TcpClient mockClient;

    @Mock
    private Socket socket;

    @Before
    public void before() {
        mockClient = new TcpClient();
    }

    @Test
    public void shouldReturnRightResult() throws Exception {
        OutputStream output = new ByteArrayOutputStream();
        TcpClient client = spy(mockClient);
        doReturn(socket).when(client).getSocket();
        when(socket.getInputStream()).thenReturn(new ByteArrayInputStream(StringUtils.EMPTY.getBytes()));
        when(socket.getOutputStream()).thenReturn(output);
        System.setIn(new ByteArrayInputStream((RESPONSE + LINE_SEPARATOR + EXIT_COMMAND + LINE_SEPARATOR).getBytes()));

        client.start();
        client.join();

        assertEquals(RESPONSE + lineSeparator(), output.toString());
    }

    @Test
    public void shouldThrowIOException() throws Exception {
        TcpClient client1 = spy(mockClient);
        doThrow(new IOException()).when(client1).getSocket();

        client1.start();
        client1.join();
    }
}
