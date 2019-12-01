package com.orbisfn.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableJpaRepositories(basePackages={"com.orbisfn.repository"})
@EntityScan(basePackages = {"com.orbisfn.entity"})
@ComponentScan(basePackages = {"com.orbisfn.*"})
public class AppApplication {

//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://localhost:3306/orbisfn?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false");
//		dataSource.setUsername("root");
//		dataSource.setPassword("root");
//		return dataSource;
//	}

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
