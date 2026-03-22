package s.a.filmes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import s.a.filmes.model.Papel;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {
    
    // Métodos personalizados para buscar papéis por filme ou ator
    List<Papel> findByFilme_Id(Long filmeId); 
    List<Papel> findByAtor_Id(Long atorId); 

}
