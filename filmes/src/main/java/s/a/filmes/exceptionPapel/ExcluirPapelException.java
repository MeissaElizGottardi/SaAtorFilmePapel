package s.a.filmes.exceptionPapel;

import s.a.filmes.exception.RequestException;

public class ExcluirPapelException extends RequestException {

    public ExcluirPapelException(Long id) {
        super("PAPEL_ERROR", "Não é possível excluir o papel com ID " + id + " porque ele está associado a um filme ou ator. Edite o papel para remover as associações. Como remover: \"idFilme\": null,");
    }

}
