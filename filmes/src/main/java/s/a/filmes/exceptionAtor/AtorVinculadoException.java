package s.a.filmes.exceptionAtor;

import s.a.filmes.exception.RequestException;

public class AtorVinculadoException  extends RequestException {
    public AtorVinculadoException(Long id) {
        super("ATOR_VINCULADO", "O ator com ID " + id + " está vinculado a um papel e não pode ser excluído. Remova os papéis associados antes de excluir o ator.");
    }
}