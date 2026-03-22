package s.a.filmes.exceptionFilme;

import s.a.filmes.exception.RequestException;

public class DescriçãoMinimaException  extends RequestException {
    public DescriçãoMinimaException(int tamanhoMinimo) {
        super("FILME_ERROR", "A descrição do filme deve conter no mínimo " + tamanhoMinimo + " caracteres.");
    }
    
}
