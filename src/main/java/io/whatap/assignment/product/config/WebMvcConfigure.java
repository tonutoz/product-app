package io.whatap.assignment.product.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * API 게이트웨이 CORS 허용
 */
@Slf4j
@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {

  @Value("${product-app,rest,gateway,url:'http://localhost:8888'}")
  private String gatewayUrl;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins(gatewayUrl);
  }
}
