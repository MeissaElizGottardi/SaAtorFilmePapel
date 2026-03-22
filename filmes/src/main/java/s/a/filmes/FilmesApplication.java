package s.a.filmes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilmesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmesApplication.class, args);
		// Mensagem de inicialização
		System.out.println("Sistema iniciado com sucesso!");
		System.out.println("Acesse: http://localhost:8080/swagger-ui/index.html");
	}

}
