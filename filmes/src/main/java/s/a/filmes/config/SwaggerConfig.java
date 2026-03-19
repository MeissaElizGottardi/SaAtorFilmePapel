package s.a.filmes.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Filmes.",
                description = "API para gerenciamento de atores, papeis e filmes."
        )
)
public class SwaggerConfig {
}
