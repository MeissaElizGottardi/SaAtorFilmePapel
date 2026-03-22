package s.a.filmes.exceptionPapel;


import s.a.filmes.exception.RequestException;

public class CaracteresInvalidosException extends RequestException {
    public CaracteresInvalidosException(String campo){
        super("PAPEL_ERROR", "O campo " + campo + " contém caracteres inválidos. Por favor, remova caracteres especiais ou acentos.");
    }
    }
    
