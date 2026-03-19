package s.a.filmes.exceptionFilme;

import s.a.filmes.exception.RequestException;

public class  FilmeNaoEncontradoException extends RequestException {
    public FilmeNaoEncontradoException(String id) {
        super("FILME_ERROR", "" + id );
    }
}
   