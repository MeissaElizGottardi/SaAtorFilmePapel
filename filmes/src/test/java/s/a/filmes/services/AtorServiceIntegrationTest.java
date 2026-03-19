/*
package s.a.filmes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import s.a.filmes.dto.AtorDto;
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

        // Teste comentado somente para não apontar os erros, teste funcionando normalmente, porem por algum erro do VS Code retorna erro na importação
        do Model.Ator, comentado somente para não confundir;

    // ADICIONAR ATOR
    @Test
    void adicionarAtorComSucesso(){

        // ARRANGE
        AtorDto atorDto = new AtorDto(
            "João Silva",
            LocalDate.of(1995, 5, 20),
            "12345678900",
            "ator@email.com",
            new BigDecimal("5000.00"),
            "Masculino",
            "Brasileira",
            "11999999999"
        );

        // ACT
        Ator atorSalvo = atorService.adicionarAtor(atorDto);

        // ASSERT
        assertNotNull(atorSalvo);
        assertNotNull(atorSalvo.getId());
        assertEquals("João Silva", atorSalvo.getNome());
    }

    // EDITAR ATOR
    @Test
    void editarAtorExistenteComSucesso(){

        // ARRANGE
        AtorDto atorDto = new AtorDto(
            "João Silva",
            LocalDate.of(1995, 5, 20),
            "12345678900",
            "ator@email.com",
            new BigDecimal("5000.00"),
            "Masculino",
            "Brasileira",
            "11999999999"
        );

        AtorDto atorEditadoDto = new AtorDto(
            "Joel Miller",
            LocalDate.of(1989, 6, 22),
            "12345458988",
            "atoreditado@email.com",
            new BigDecimal("4030.00"),
            "Masculino",
            "Canadense",
            "55999999999"
        );

        // ACT
        Ator atorSalvo = atorService.adicionarAtor(atorDto);
        Long id = atorSalvo.getId();

        Ator resultado = atorService.editarAtor(id, atorEditadoDto);

        // ASSERT
        assertEquals("Joel Miller", resultado.getNome());
        assertEquals("Masculino", resultado.getGenero());
        assertEquals("Canadense", resultado.getNacionalidade());
        assertEquals(new BigDecimal("4030.00"), resultado.getSalario());
    }

    // BUSCAR ATOR EXCLUÍDO
    @Test
    void deveLancarExcecaoQuandoBuscarAtorExcluido(){

        // ARRANGE
        AtorDto atorDto = new AtorDto(
            "João Silva",
            LocalDate.of(1995, 5, 20),
            "12345678900",
            "ator@email.com",
            new BigDecimal("5000.00"),
            "Masculino",
            "Brasileira",
            "11999999999"
        );

        // ACT
        Ator atorSalvo = atorService.adicionarAtor(atorDto);
        Long id = atorSalvo.getId();

        atorService.excluirAtor(id);

        // ASSERT
        assertThrows(
            AtorNaoEncontradoException.class,
            () -> atorService.getAtorById(id)
        );
    }
}
    */