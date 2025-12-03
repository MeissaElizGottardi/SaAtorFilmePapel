package s.a.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import s.a.filmes.model.Papel;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {
    
}
