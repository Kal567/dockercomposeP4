package com.pucmm.compose;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {
    public SpringBootApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("user1");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/barcampcomposedb");

        return dataSource;
    }
}
