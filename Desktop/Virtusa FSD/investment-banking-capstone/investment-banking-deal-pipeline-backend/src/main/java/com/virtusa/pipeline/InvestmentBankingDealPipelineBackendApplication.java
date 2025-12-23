package com.virtusa.pipeline;

import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class InvestmentBankingDealPipelineBackendApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(InvestmentBankingDealPipelineBackendApplication.class, args);
	}
	
	@Bean
	CommandLineRunner checkDb(MongoTemplate mongoTemplate)
	{
	    return args -> 
	    {
	        System.out.println("Mongo DB Name = " + mongoTemplate.getDb().getName());
	    };
	}

}
