package com.epam.lab.shchehlov.task_09.server;

import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_09.command.container.ServerCommandContainer;
import com.epam.lab.shchehlov.task_09.command.impl.tcp.GetCountTcpServerCommand;
import com.epam.lab.shchehlov.task_09.command.impl.tcp.GetItemTcpServerCommand;
import com.epam.lab.shchehlov.task_09.handler.impl.TcpHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Hashtable;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TcpServerTest {
    private final long ITEM_ID = 1L;
    private final String GET_ITEM = "get item";
    private final String GET_ITEM_ONE = "get item";
    private final String GET_COUNT = "get count";

    @Mock
    private ProductService productService;

    @Mock
    private ServerCommandContainer commandContainer;

    @Mock
    private Socket socket;

    private InputStream inputStream;
    private final OutputStream outputStream = spy(new ByteArrayOutputStream());
    private Hashtable<Long, PowerTool> toolList;

    @Before
    public void before() {
        toolList = new Hashtable<>();
        toolList.put(1L, new Drill(1L, "Bosch 10", 2098, 1000, 2400));
        toolList.put(2L, new Drill(2L, "Bosch 20", 2598, 1400, 2800));

        when(productService.getOne(ITEM_ID)).thenReturn(toolList.get(ITEM_ID));
        when(productService.getAll()).thenReturn(toolList);
        when(commandContainer.getServerCommand(GET_ITEM)).thenReturn(new GetItemTcpServerCommand(productService));
        when(commandContainer.getServerCommand(GET_COUNT)).thenReturn(new GetCountTcpServerCommand(productService));
    }

    @Test
    public void shouldReturnCorrectPriceWhenGetItem() throws IOException {
        inputStream = spy(new ByteArrayInputStream(GET_ITEM_ONE.getBytes()));

        when(socket.getInputStream()).thenReturn(inputStream);
        when(socket.getOutputStream()).thenReturn(outputStream);

        new Thread(new TcpHandler(productService, socket, commandContainer)).run();
        verify(socket).getInputStream();
        verify(socket).getOutputStream();
        String response = String.valueOf(socket.getOutputStream()).trim();

        assertTrue(response.endsWith(String.valueOf(toolList.get(ITEM_ID).getPrice())));
    }

    @Test
    public void shouldReturnCorrectContainerSizeWhenGetCount() throws IOException {
        inputStream = spy(new ByteArrayInputStream(GET_COUNT.getBytes()));

        when(socket.getInputStream()).thenReturn(inputStream);
        when(socket.getOutputStream()).thenReturn(outputStream);

        new Thread(new TcpHandler(productService, socket, commandContainer)).run();
        verify(socket).getInputStream();
        verify(socket).getOutputStream();
        String response = String.valueOf(socket.getOutputStream()).trim();

        assertTrue(response.endsWith(String.valueOf(toolList.size())));
    }
}
