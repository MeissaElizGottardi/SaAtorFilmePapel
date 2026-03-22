package s.a.filmes.exceptionFilme;

import s.a.filmes.exception.RequestException;

public class FilmeVinculadoException  extends RequestException{
    public FilmeVinculadoException(Long id) {
        super("FILME_ERROR", "O filme com ID " + id + " está vinculado a papéis e não pode ser excluído. Remova os papéis associados antes de excluir o filme.");
    }

    
}
