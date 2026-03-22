package s.a.filmes.exceptionFilme;

import s.a.filmes.exception.RequestException;

public class DatasInvalidasException extends RequestException {

    // Construtor para erro de Fim antes do Início
    public static DatasInvalidasException fimAntesDoInicio() {
        return new DatasInvalidasException("A data prevista de fim das gravações não pode ser antes da data de início.");
    }

    // Construtor para erro de Lançamento
    public static DatasInvalidasException lancamentoPrecoce() {
        return new DatasInvalidasException("A data prevista de lançamento não pode ser antes do início ou fim das gravações.");
    }

    // Construtor base privado
    private DatasInvalidasException(String mensagem) {
        super("FILME_ERROR", mensagem);
    }
}