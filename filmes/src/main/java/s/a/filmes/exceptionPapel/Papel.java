package s.a.filmes.exceptionPapel;

import s.a.filmes.exception.RequestException;

public class  Papel extends RequestException {
    public Papel(String id) {
        super("PAPEL_ERROR", "Papel não encontrado com o ID: " + id );
    }
}
  