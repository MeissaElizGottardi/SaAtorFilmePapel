package s.a.filmes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilmesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmesApplication.class, args);
		System.out.println("Sistema iniciado com sucesso!");
		System.out.println("Acesse: http://localhost:8080");
	}

}
