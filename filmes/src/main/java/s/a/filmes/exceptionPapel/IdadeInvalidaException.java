package s.a.filmes.exceptionPapel;


import s.a.filmes.exception.RequestException;

public class IdadeInvalidaException extends RequestException {

    public IdadeInvalidaException() {
        super("PAPEL_ERROR", "Um ator menor de idade não pode interpretar um papel de adulto (18+).");
    }
    
}
