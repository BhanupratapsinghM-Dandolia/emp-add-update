package com.cts.codetest.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
 
public class LogFilter extends OncePerRequestFilter {
               
    private static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
        Long startTime = System.nanoTime();
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
        wrapper.addHeader("X-Start-Time", startTime.toString());
        chain.doFilter(request, wrapper);
        Long timeTaken = (System.nanoTime()-startTime)/1000000L;
        wrapper.addHeader("X-Time-Taken-String", startTime.toString());
        wrapper.addIntHeader("X-Time-Taken-Int", startTime.intValue());
        wrapper.addHeader("X-Time-Taken", String.valueOf(timeTaken));
        LOGGER.debug("Time Taken: {}", startTime);
        LOGGER.debug("Time Taken Header: {}", wrapper.getHeader("X-Time-Taken"));
    }
}
