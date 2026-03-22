package s.a.filmes.exceptionAtor;

import s.a.filmes.exception.RequestException;

public class NomeInvalidoException extends RequestException {
    public NomeInvalidoException(String nome) {
        super("NOME_INVALIDO", "O nome '" + nome + "' contém caracteres inválidos. Por favor, remova caracteres especiais ou acentos.");
    }
}