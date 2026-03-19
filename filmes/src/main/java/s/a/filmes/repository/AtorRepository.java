package s.a.filmes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import s.a.filmes.model.Ator;


@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {    
             List<Ator> findByFilmes_Id(Long filmeId);
             
            // Verifica se já existe um ator com o CPF informado
            boolean existsByCpf(String cpf);

}
