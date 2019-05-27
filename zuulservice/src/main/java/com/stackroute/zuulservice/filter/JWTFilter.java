package com.stackroute.zuulservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends GenericFilterBean {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    final String authHeader = httpServletRequest.getHeader("authorization");
    if("OPTIONS".equals(httpServletRequest.getMethod())){
      httpServletResponse.setStatus(HttpServletResponse.SC_OK);
      chain.doFilter(httpServletRequest,httpServletResponse);
    }else{
      if(authHeader == null || !authHeader.startsWith("Bearer ")){
        throw new ServletException("Missing or Wrong authorization Header");
      }
      final String token = authHeader.substring(7);
      final Claims claims = Jwts.parser().setSigningKey("MySecr@").parseClaimsJws(token).getBody();
      httpServletRequest.setAttribute("claims",claims);
      chain.doFilter(httpServletRequest,httpServletResponse);
    }
  }
}
