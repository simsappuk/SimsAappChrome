package com.ebay.load.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SellerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellerApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/get/postJsonData").allowedOrigins("http://localhost:8080","https://simsapp.co.uk");
				registry.addMapping("/getFacebookList/number").allowedOrigins("http://localhost:8080","https://simsapp.co.uk");
				registry.addMapping("/revise/facebookItem").allowedOrigins("http://localhost:8080","https://simsapp.co.uk");
				registry.addMapping("/revise/eBayItem").allowedOrigins("http://localhost:8080","https://simsapp.co.uk");
				registry.addMapping("/gmail/viewOrderDetails/data").allowedOrigins("http://localhost:8080","https://simsapp.co.uk");
				registry.addMapping("/magpie/viewOrderDetails/data").allowedOrigins("http://localhost:8080","https://simsapp.co.uk");
				registry.addMapping("/getFacebookList/number/total").allowedOrigins("http://localhost:8080","https://simsapp.co.uk");
				registry.addMapping("/getEbayList/number/total").allowedOrigins("http://localhost:8080","https://simsapp.co.uk");
				registry.addMapping("/vinted/getJsonData").allowedOrigins("http://localhost:8080","https://simsapp.co.uk");

			}
		};
	}

}
