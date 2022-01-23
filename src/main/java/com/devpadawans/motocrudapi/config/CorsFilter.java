package com.devpadawans.motocrudapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Value("${motocrud.client.origin:*}")
    private String origin;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String requestOrigin = request.getHeader("Origin");

        List<String> listOrigin = Stream.of(origin.split(",")).map(String::trim).collect(Collectors.toList());
        String allowOrigin;

        if (StringUtils.hasText(requestOrigin) && !CollectionUtils.isEmpty(listOrigin) && listOrigin.contains(requestOrigin)) {
            allowOrigin = requestOrigin;
        } else {
            allowOrigin = listOrigin.get(0);
        }

        response.setHeader("Access-Control-Allow-Origin", allowOrigin);
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if (HttpMethod.OPTIONS.name().equals(request.getMethod()) && (allowOrigin.equals(requestOrigin) || this.origin.equals("*"))) {
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, PATCH, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
            response.setHeader("Access-Control-Max-Age", "3600");

            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, resp);
        }
    }

}
