package s.a.filmes.exceptionAtor;

import s.a.filmes.exception.RequestException;

public class CpfDuplicadoException extends RequestException {
    public CpfDuplicadoException(String cpf) {
        super("CPF_DUPLICADO", "Já existe um ator cadastrado com o CPF: " + cpf);
    }
}