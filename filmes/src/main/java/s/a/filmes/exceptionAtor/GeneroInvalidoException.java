package s.a.filmes.exceptionAtor;

import s.a.filmes.exception.RequestException;

public class GeneroInvalidoException extends RequestException {
    public GeneroInvalidoException(String genero) {
        super("GENERO_ERROR", genero + " não é válido. Valores permitidos: Feminino, Masculino, Não Binário.");
    }
}
