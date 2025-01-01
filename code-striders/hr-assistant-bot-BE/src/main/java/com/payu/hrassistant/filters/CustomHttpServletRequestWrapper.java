package com.payu.hrassistant.filters;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final byte[] body;

    public CustomHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = StreamUtils.copyToByteArray(request.getInputStream());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new ServletInputStreamImpl(new ByteArrayInputStream(body));
    }
}