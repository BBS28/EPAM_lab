package com.epam.shchehlov.filter.wrapper.stream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import static com.epam.shchehlov.constant.Constant.MESSAGE_GZIP_NOT_SUPPORTED;

public class GZipServletOutputStream extends ServletOutputStream {

    private final GZIPOutputStream gzipOutputStream;

    public GZipServletOutputStream(OutputStream outputStream) throws IOException {
        this.gzipOutputStream = new GZIPOutputStream(outputStream);
    }

    @Override
    public void flush() throws IOException {
        this.gzipOutputStream.flush();
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        throw new UnsupportedOperationException(MESSAGE_GZIP_NOT_SUPPORTED);
    }

    @Override
    public void close() throws IOException {
        this.gzipOutputStream.close();
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.gzipOutputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.gzipOutputStream.write(b, off, len);
    }

    @Override
    public void write(int b) throws IOException {
        this.gzipOutputStream.write(b);
    }
}
