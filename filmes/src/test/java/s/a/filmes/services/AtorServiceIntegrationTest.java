package s.a.filmes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import s.a.filmes.exceptionAtor.AtorNaoEncontradoException;
import s.a.filmes.model.Ator;


import s.a.filmes.repository.AtorRepository;

@SpringBootTest
@Transactional
public class AtorServiceIntegrationTest {

    @Autowired
    private AtorService atorService;

    @Autowired
    private AtorRepository atorRepository;


    //ATOR

    @Test
    void adicionarAtorComSucesso(){

        //arrange
        Ator ator = new Ator(
            "12345678900",
            LocalDate.of(1995, 5, 20),
            "ator@email.com",
            "Masculino",
            "Brasileira",
            "João Silva",
            new BigDecimal("5000.00"),
            "11999999999"
    );

        // act
        Ator atorSalvo = atorService.adicionarAtor(ator);

        //asserts
        assertNotNull(atorSalvo);
        assertNotNull(atorSalvo.getId());
        assertEquals("João Silva", atorSalvo.getNome());

    }

    @Test
    void editarAtorExistenteComSucesso(){

        //arrange
        Ator ator = new Ator(
            "12345678900",
            LocalDate.of(1995, 5, 20),
            "ator@email.com",
            "Masculino",
            "Brasileira",
            "João Silva",
            new BigDecimal("5000.00"),
            "11999999999"
    );
        Ator atorEditado = new Ator(
            "12345458988",
            LocalDate.of(1989, 6, 22),
            "atoreditado@email.com",
            "Masculino",
            "Canadense",
            "Joel Miller",
            new BigDecimal("4030.00"),
            "55999999999"
        );

        // act
    Ator atorSalvo = atorService.adicionarAtor(ator);
    Long id = atorSalvo.getId();
    Ator resultado = atorService.editarAtor(id, atorEditado);

        // assert
    assertEquals("Joel Miller", resultado.getNome());
    assertEquals("Masculino", resultado.getGenero());
    assertEquals("Canadense", resultado.getNacionalidade());
    assertEquals(new BigDecimal("4030.00"), resultado.getSalario());
}
    
    //excluir ator adicionado
    @Test
    void deveLancarExcecaoQuandoNaoEncontrarAtor(){

        //arrange
        Ator ator = new Ator(
            "12345678900",
            LocalDate.of(1995, 5, 20),
            "ator@email.com",
            "Masculino",
            "Brasileira",
            "João Silva",
            new BigDecimal("5000.00"),
            "11999999999"
    );
        // act
        Ator atorSalvo = atorService.adicionarAtor(ator);
        Long id = atorSalvo.getId();
        atorService.excluirAtor(id);

        //asserts
        AtorNaoEncontradoException exception = assertThrows(
        AtorNaoEncontradoException.class,
        () -> atorService.getAtorById(id)
);
  
    }

    
}
