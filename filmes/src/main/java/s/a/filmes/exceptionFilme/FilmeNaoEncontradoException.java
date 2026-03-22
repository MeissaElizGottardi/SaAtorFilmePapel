package s.a.filmes.exceptionFilme;

import s.a.filmes.exception.RequestException;

public class  FilmeNaoEncontradoException extends RequestException {
    public FilmeNaoEncontradoException(Long id) {
        super("FILME_ERROR", "Filme com o id " + id + " não existe" );
    }
}
   