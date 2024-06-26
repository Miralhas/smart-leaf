package fatec.sp.gov.br.smartleaf.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("SmartLeaf API")
                .version("v1.0.0")
                .description("SmartLeaf RESTful API Documentation")
        )
                .tags(
                        Arrays.asList(
                                new Tag()
                                        .name("Solar Panel")
                                        .description("Solar Panel Endpoints"),
                                new Tag()
                                        .name("Solar Panel Image")
                                        .description("Solar Panel Images Endpoints"),
                                new Tag()
                                        .name("Login")
                                        .description("Login Endpoints")
                        )
                );
    }

}
