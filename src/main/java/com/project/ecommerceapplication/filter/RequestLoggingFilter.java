package com.project.ecommerceapplication.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class RequestLoggingFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(RequestLoggingFilter.class);


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Log the request details
        LOGGER.info("Request URI: " + httpRequest.getRequestURI() +
                "HTTP Method: "+ httpRequest.getMethod()
                +"Remote Address: "+ httpRequest.getRemoteAddr());

        // Continue with the request chain
        chain.doFilter(request, response);
    }

}
