package com.tinqin.cms;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableDiscoveryClient
@EntityScan(basePackages = {"com.tinqin.cms.entities"})
@EnableJpaRepositories(basePackages = {"com.tinqin.cms.repositories"})
@EnableFeignClients
@OpenAPIDefinition(
        info = @Info(
                title = "Asset Management Service Documentation",
                description = "This is a documentation for my asset management service",
                version = "v1.0",
                contact = @Contact(
                        name = "Alex Orozov",
                        email = "alexorozov@gmail.com",
                        url = "https://www.linkedin.com/in/alex-orozov-34440624b/"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.linkedin.com/in/alex-orozov-34440624b/license"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot Employee Management Service",
                url = "https://www.linkedin.com/in/alex-orozov-34440624b/Employee-Management-System.html"
        )
)
public class AssetServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetServiceApplication.class, args);
    }

}
