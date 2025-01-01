package com.payu.hrassistant.filters;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponseWrapper;

import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Custom Response Wrapper
class CustomHttpServletResponseWrapper extends HttpServletResponseWrapper {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private ServletOutputStream filterOutput;

    public CustomHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (filterOutput == null) {
            filterOutput = new ServletOutputStreamImpl(output, getResponse().getOutputStream());
        }
        return filterOutput;
    }

    // Additional methods to capture response
    public byte[] getResponseData() {
        return output.toByteArray();
    }
}
