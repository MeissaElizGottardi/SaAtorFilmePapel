package s.a.filmes.exceptionAtor;

import s.a.filmes.exception.RequestException;

public class CpfInvalidoException extends RequestException{
    public CpfInvalidoException(String cpf) {
        super ("CPF_ERROR",cpf + " Campo CPF deve ter exatamente 11 digitos");
    }
    
}
