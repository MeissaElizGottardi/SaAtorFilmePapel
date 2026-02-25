package s.a.filmes.exceptionFilme;

import s.a.filmes.exception.RequestException;

public class  Filme extends RequestException {
    public Filme(String id) {
        super("FILME_ERROR", "Filme não encontrado com o ID: " + id );
    }
}
   