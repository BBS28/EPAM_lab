package com.epam.shchehlov.filter.wrapper;

import com.epam.shchehlov.filter.wrapper.stream.GZipServletOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import static com.epam.shchehlov.constant.Constant.MESSAGE_OS_OBTAINED;
import static com.epam.shchehlov.constant.Constant.MESSAGE_PW_OBTAINED;

public class GzipResponseWrapper extends HttpServletResponseWrapper {

    private ServletOutputStream gzipOutputStream;
    private PrintWriter printWriter;

    public GzipResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public void closeWriter() throws IOException {
        if (this.printWriter != null) {
            this.printWriter.close();
        }

        if (this.gzipOutputStream != null) {
            this.gzipOutputStream.close();
        }
    }

    @Override
    public void flushBuffer() throws IOException {
        if (this.printWriter != null) {
            this.printWriter.flush();
        }

        if (this.gzipOutputStream != null) {
            this.gzipOutputStream.flush();
        }
        super.flushBuffer();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (this.printWriter != null) {
            throw new IllegalStateException(MESSAGE_PW_OBTAINED);
        }

        if (this.gzipOutputStream == null) {
            this.gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
        }
        return this.gzipOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (this.printWriter == null && this.gzipOutputStream != null) {
            throw new IllegalStateException(MESSAGE_OS_OBTAINED);
        }

        if (this.printWriter == null) {
            this.gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
            this.printWriter = new PrintWriter(new OutputStreamWriter(this.gzipOutputStream, getResponse().getCharacterEncoding()));
        }

        return this.printWriter;
    }
}
