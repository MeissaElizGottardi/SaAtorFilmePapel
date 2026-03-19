package s.a.filmes.exceptionFilme;

import s.a.filmes.exception.RequestException;

public class CampoNaoInformadoException extends RequestException{
    public CampoNaoInformadoException(String nomecampo){
        super("FILME_ERROR", nomecampo + " nao informado");
    }

    
}
