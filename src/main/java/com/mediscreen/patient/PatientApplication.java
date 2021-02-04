package com.mediscreen.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableDiscoveryClient
public class PatientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}
/*	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source =
				new UrlBasedCorsConfigurationSource();

		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);

		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		source.registerCorsConfiguration("/**", config);

		FilterRegistrationBean<CorsFilter> bean =
				new FilterRegistrationBean<>(new CorsFilter(source));

		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

		return bean;
	}

 */
}
