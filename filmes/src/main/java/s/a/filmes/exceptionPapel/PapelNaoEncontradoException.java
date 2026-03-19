package s.a.filmes.exceptionPapel;

import s.a.filmes.exception.RequestException;

public class  PapelNaoEncontradoException extends RequestException {
    public PapelNaoEncontradoException(String id) {
        super("PAPEL_ERROR", "Papel não encontrado com o ID: " + id );
    }
}
  