package s.a.filmes.exceptionPapel;

import s.a.filmes.exception.RequestException;

public class DescricaoMinimaException extends RequestException {
    public DescricaoMinimaException(int tamanhoMinimo) {
        super("PAPEL_ERROR", "A descrição do papel deve conter no mínimo " + tamanhoMinimo + " caracteres.");
    }    
}