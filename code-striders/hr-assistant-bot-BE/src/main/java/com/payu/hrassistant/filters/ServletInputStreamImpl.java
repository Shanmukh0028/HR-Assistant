package com.payu.hrassistant.filters;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;

import java.io.IOException;
import java.io.InputStream;

class ServletInputStreamImpl extends ServletInputStream {
    private final InputStream inputStream;

    public ServletInputStreamImpl(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

    @Override
    public boolean isFinished() {
        try {
            return inputStream.available() == 0;
        } catch (IOException e) {
            return true;
        }
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        // Not implemented
    }
}