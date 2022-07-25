package com.epam.lab.shchehlov.task_09.server;

import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_04.service.ProductService;
import com.epam.lab.shchehlov.task_09.command.container.ServerCommandContainer;
import com.epam.lab.shchehlov.task_09.command.impl.http.GetCountHttpServerCommand;
import com.epam.lab.shchehlov.task_09.command.impl.http.GetItemHttpServerCommand;
import com.epam.lab.shchehlov.task_09.handler.impl.HttpHandler;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HttpServerTest {
    private final long ITEM_ID = 1L;
    private final String ITEM = "item";
    private final String COUNT = "count";
    private final String GET_INFO_COMMAND = "GET /shop/item?get_info=1 HTTP/1.1";
    private final String GET_COUNT_COMMAND = "GET /shop/count HTTP/1.1";
    private final String CORRECT_OUTPUT_INFO = "{name:Bosch 10, price:2098}";
    private final String CORRECT_OUTPUT_COUNT = "{count:2}";
    private final String OUTPUT_WRONG_COMMAND = "There is no such server command";

    private InputStream inputStream;
    private final OutputStream outputStream = spy(new ByteArrayOutputStream());

    @Mock
    private ProductService productService;

    @Mock
    private ServerCommandContainer commandContainer;

    @Mock
    private Socket socket;

    @Before
    public void before() {
        Hashtable<Long, PowerTool> toolList = new Hashtable<>();
        toolList.put(1L, new Drill(1L, "Bosch 10", 2098, 1000, 2400));
        toolList.put(2L, new Drill(2L, "Bosch 20", 2598, 1400, 2800));

        when(productService.getOne(ITEM_ID)).thenReturn(toolList.get(ITEM_ID));
        when(productService.getAll()).thenReturn(toolList);
        when(commandContainer.getServerCommand(ITEM)).thenReturn(new GetItemHttpServerCommand(productService));
        when(commandContainer.getServerCommand(COUNT)).thenReturn(new GetCountHttpServerCommand(productService));
    }

    @Test
    public void shouldReturnCorrectNameAndPriceWhenItem() throws IOException {
        inputStream = spy(new ByteArrayInputStream(GET_INFO_COMMAND.getBytes()));

        when(socket.getInputStream()).thenReturn(inputStream);
        when(socket.getOutputStream()).thenReturn(outputStream);

        new Thread(new HttpHandler(productService, socket, commandContainer)).run();
        verify(socket).getInputStream();
        verify(socket).getOutputStream();
        String response = String.valueOf(socket.getOutputStream()).trim();

        assertEquals(CORRECT_OUTPUT_INFO, response.substring((response.lastIndexOf(System.lineSeparator()) + 2)));
    }

    @Test
    public void shouldReturnCorrectContainerSizeWhenCount() throws IOException {
        inputStream = spy(new ByteArrayInputStream(GET_COUNT_COMMAND.getBytes()));
        final int LINE_SEPARATOR_LENGTH = 2;

        when(socket.getInputStream()).thenReturn(inputStream);
        when(socket.getOutputStream()).thenReturn(outputStream);

        new Thread(new HttpHandler(productService, socket, commandContainer)).run();
        verify(socket).getInputStream();
        verify(socket).getOutputStream();
        String response = String.valueOf(socket.getOutputStream()).trim();

        assertEquals(CORRECT_OUTPUT_COUNT, response.substring((response.lastIndexOf(System.lineSeparator()) + LINE_SEPARATOR_LENGTH)));
    }

    @Test
    public void shouldReturnNoCommandWhenWrongInput() throws IOException {
        inputStream = spy(new ByteArrayInputStream(GET_INFO_COMMAND.getBytes()));

        when(socket.getInputStream()).thenReturn(inputStream);
        when(socket.getOutputStream()).thenReturn(outputStream);

        new Thread(new HttpHandler(productService, socket, commandContainer)).run();
        verify(socket).getInputStream();
        verify(socket).getOutputStream();
        String response = String.valueOf(socket.getOutputStream()).trim();

        assertEquals(OUTPUT_WRONG_COMMAND, response.substring((response.lastIndexOf(System.lineSeparator()) + 2)));
    }
}
