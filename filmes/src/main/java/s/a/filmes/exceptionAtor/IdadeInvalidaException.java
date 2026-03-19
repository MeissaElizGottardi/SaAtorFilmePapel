package s.a.filmes.exceptionAtor;

import s.a.filmes.exception.RequestException;

public class IdadeInvalidaException extends RequestException {
    public IdadeInvalidaException(int idade) {
        super("IDADE_ERROR", "Idade " + idade + " anos é inválida. O ator não pode ter mais de 120 anos.");
    }
}