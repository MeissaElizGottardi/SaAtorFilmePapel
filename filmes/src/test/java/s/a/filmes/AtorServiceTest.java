package s.a.filmes;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import s.a.filmes.model.Ator;
import s.a.filmes.repository.AtorRepository;
import s.a.filmes.services.AtorService;

@ExtendWith(MockitoExtension.class)
class AtorServiceTest {

    @Mock
    private AtorRepository atorRepository;

    @InjectMocks
    private AtorService atorService;

    @Test
    void deveCadastrarAtorComSucesso() {
        // Entrada
        Ator atorEntrada = new Ator();
        atorEntrada.setNome("Robert Downey Jr.");
        atorEntrada.setDataNascimento(LocalDate.of(1965, 4, 4));
        atorEntrada.setNacionalidade("Americano");
        atorEntrada.setCpf("123.456.789-00");
        atorEntrada.setEmail("robertdowney@gmail.com");
        atorEntrada.setTelefone("(11) 91234-5678");
        atorEntrada.setSalario(new BigDecimal("5000000.00"));
        atorEntrada.setGenero("Masculino");

        // Retorno simulado do repositório
        Ator atorSalvo = new Ator();
        atorSalvo.setId(1L);
        atorSalvo.setNome(atorEntrada.getNome());
        atorSalvo.setDataNascimento(atorEntrada.getDataNascimento());
        atorSalvo.setNacionalidade(atorEntrada.getNacionalidade());
        atorSalvo.setCpf(atorEntrada.getCpf());
        atorSalvo.setEmail(atorEntrada.getEmail());
        atorSalvo.setTelefone(atorEntrada.getTelefone());
        atorSalvo.setSalario(atorEntrada.getSalario());
        atorSalvo.setGenero(atorEntrada.getGenero());

        when(atorRepository.save(any(Ator.class))).thenReturn(atorSalvo);

        // Execução
        Ator resultado = atorService.adicionarAtor(atorEntrada);

        // Verificações
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Robert Downey Jr.", resultado.getNome());
        assertEquals(0, resultado.getSalario().compareTo(new BigDecimal("5000000.00")));
    }
}