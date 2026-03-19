package s.a.filmes.exceptionAtor;

import java.math.BigDecimal;

import s.a.filmes.exception.RequestException;

public class SalarioMaximoException extends RequestException {
    public SalarioMaximoException(BigDecimal salario) {
        super("SALARIO_ERROR", "Salário " + salario + " é inválido. O ator não pode ter salário maior que 1.000.000.");
    }
}
