package s.a.filmes.exceptionAtor;

import s.a.filmes.exception.RequestException;

public class EmailInvalidoException extends RequestException {
    public EmailInvalidoException(String email) {
        super("EMAIL_INVALIDO", "O e-mail '" + email + "' não é um formato válido.");
    }
}