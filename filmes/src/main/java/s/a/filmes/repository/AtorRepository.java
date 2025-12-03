package s.a.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import s.a.filmes.model.Ator;


@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {    
}
