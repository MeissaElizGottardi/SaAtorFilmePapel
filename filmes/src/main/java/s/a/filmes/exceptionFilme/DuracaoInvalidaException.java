package s.a.filmes.exceptionFilme;

import s.a.filmes.exception.RequestException;

public class DuracaoInvalidaException extends RequestException {

    public DuracaoInvalidaException(Integer duracao) {
        super("FILME_ERROR", "Duração inválida: " + duracao + " minutos. O máximo permitido é 600 minutos (10 horas).");
    }
}