package com.project.ecommerceapplication.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("ResponseLoggingFilter")
public class ResponseLoggingFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(ResponseLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Capture response status code and other details
        int statusCode = httpResponse.getStatus();

        // Log the response details
        LOGGER.info("Response Status Code: {}", statusCode);

        // Continue with the response chain
        chain.doFilter(request, response);
    }
}


