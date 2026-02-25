package s.a.filmes.exceptionAtor;

import s.a.filmes.exception.RequestException;

public class  AtorNaoEncontradoException extends RequestException {
    public AtorNaoEncontradoException(String id) {
        super("ATOR_ERROR", "Ator não encontrado com o ID: " + id );
    }
}