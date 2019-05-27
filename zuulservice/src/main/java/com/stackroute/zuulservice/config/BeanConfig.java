package com.stackroute.zuulservice.config;

import com.stackroute.zuulservice.filter.JWTFilter;
import com.stackroute.zuulservice.filter.PostFilter;
import com.stackroute.zuulservice.filter.PreFilter;
import com.stackroute.zuulservice.filter.RouteFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class BeanConfig {
  @Bean
  public FilterRegistrationBean jwtFilter(){
    final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(new JWTFilter());
    filterRegistrationBean.addUrlPatterns("/usertrackservice/api/v1/usertrackservice/user/*");
    return filterRegistrationBean;

  }

  @Bean
  public PreFilter preFilter(){
    return new PreFilter();
  }

  @Bean
  public PostFilter postFilter(){
    return new PostFilter();
  }

  @Bean
  public RouteFilter routeFilterFilter(){
    return new RouteFilter();
  }

  @Bean
  public CorsFilter corsFilter(){
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.addAllowedOrigin("*");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("OPTIONS");
    configuration.addAllowedMethod("HEAD");
    configuration.addAllowedMethod("GET");
    configuration.addAllowedMethod("PUT");
    configuration.addAllowedMethod("POST");
    configuration.addAllowedMethod("DELETE");
    configuration.addAllowedMethod("PATCH");
    source.registerCorsConfiguration("*",configuration);
    return new CorsFilter(source);
  }
}
