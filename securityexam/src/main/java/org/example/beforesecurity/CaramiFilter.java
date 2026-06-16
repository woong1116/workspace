package org.example.beforesecurity;

import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
//@Component
//@WebFilter(urlPatterns = "/api/*")
public class CaramiFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CaramiFilter init() 실행전");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("CaramiFilter doFilter() 실행전");
        chain.doFilter(request,response);
        System.out.println("CaramiFilter doFilter() 실행후");
    }


    @Override
    public void destroy() {
        System.out.println("CaramiFilter destroy()");
    }
}
