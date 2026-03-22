package s.a.filmes.exceptionAtor;

import s.a.filmes.exception.RequestException;

// Ao herdar de RequestException, o GlobalExceptionHandler já cuida de tudo!
public class SalarioInvalidoException extends RequestException {
    
    public SalarioInvalidoException() {
        // Passamos um "código de erro" e a mensagem para o construtor do pai
        super("SALARIO_INVALIDO", "O salário do ator deve ser um valor positivo.");
    }
}