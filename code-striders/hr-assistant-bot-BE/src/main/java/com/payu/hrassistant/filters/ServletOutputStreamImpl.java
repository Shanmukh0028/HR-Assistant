package com.payu.hrassistant.filters;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

class ServletOutputStreamImpl extends ServletOutputStream {
    private final ByteArrayOutputStream output;
    private final ServletOutputStream original;

    public ServletOutputStreamImpl(ByteArrayOutputStream output, ServletOutputStream original) {
        this.output = output;
        this.original = original;
    }

    @Override
    public void write(int b) throws IOException {
        output.write(b);
        original.write(b);
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        // Not implemented
    }
}