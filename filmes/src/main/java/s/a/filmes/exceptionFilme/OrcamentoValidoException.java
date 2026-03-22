package s.a.filmes.exceptionFilme;

import s.a.filmes.exception.RequestException;    

public class OrcamentoValidoException extends RequestException {
    public OrcamentoValidoException() {
        super("FILME_ERROR", "O orçamento do filme deve ser um valor positivo.");
    }
}