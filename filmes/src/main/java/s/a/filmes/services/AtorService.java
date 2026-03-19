package s.a.filmes.services;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import s.a.filmes.dto.AtorDto;
import s.a.filmes.exceptionAtor.AtorNaoEncontradoException;
import s.a.filmes.exceptionAtor.CpfDuplicadoException;
import s.a.filmes.exceptionAtor.CpfInvalidoException;
import s.a.filmes.exceptionAtor.GeneroInvalidoException;
import s.a.filmes.exceptionAtor.IdadeInvalidaException;
import s.a.filmes.exceptionAtor.SalarioMaximoException;
import s.a.filmes.exceptionFilme.CampoNaoInformadoException;
import s.a.filmes.model.Ator;
import s.a.filmes.repository.AtorRepository;

@Service
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;

        //////////////////////////////////////////////////////////////
        //                       ADICIONAR ATOR
        ////////////////////////////////////////////////////////////////
        
        public Ator adicionarAtor(AtorDto atorDto) {

        //                     VALIDAÇÕES OBRIGATORIAS
        if (StringUtils.isBlank(atorDto.getNome())) {
            throw new CampoNaoInformadoException("Nome");
        }
        if (atorDto.getDataNascimento() == null) {
            throw new CampoNaoInformadoException("Data de Nascimento");
        }
        if (StringUtils.isBlank(atorDto.getCpf())) {
            throw new CampoNaoInformadoException("CPF");
        }
        if (StringUtils.isBlank(atorDto.getEmail())) {
            throw new CampoNaoInformadoException("Email");
        }
        if (atorDto.getSalario() == null || atorDto.getSalario().doubleValue() <= 0) {
            throw new CampoNaoInformadoException("Salário");
        }
        if (StringUtils.isBlank(atorDto.getGenero())) {
            throw new CampoNaoInformadoException("Gênero");
        }
        if (StringUtils.isBlank(atorDto.getNacionalidade())) {
            throw new CampoNaoInformadoException("Nacionalidade");
        }
        if (StringUtils.isBlank(atorDto.getTelefone())) {
            throw new CampoNaoInformadoException("Telefone");
        }

        //                      VALIDAÇÕES PREVENTIVA DE ERROS

        // VALIDAÇÃO PARA USARIO NAO PODER CADASTRAR ATOR COM MAIS DE 120 ANOS
        LocalDate hoje = LocalDate.now();
        Period idade = Period.between(atorDto.getDataNascimento(), hoje);

        if (idade.getYears() > 120) {
            throw new IdadeInvalidaException(idade.getYears());
        }

        // VALIDACAO PARA USARIO PODER CADASTRAR SOMENTE 3 GENEROS
        String genero = atorDto.getGenero().trim();
        if (!(genero.equalsIgnoreCase("Feminino")
                || genero.equalsIgnoreCase("Masculino")
                || genero.equalsIgnoreCase("Não Binário"))) {
            throw new GeneroInvalidoException(genero);
        }

        // CPF TER 11 DIGITOS
        if (atorDto.getCpf().length() != 11) {
            throw new CpfInvalidoException(atorDto.getCpf());
        }

        // CPF UNICO
        if (atorRepository.existsByCpf(atorDto.getCpf())) {
            throw new CpfDuplicadoException(atorDto.getCpf());
        }

        // SALARIO MAXIMO
        if (atorDto.getSalario().doubleValue() > 1_000_000) {
            throw new SalarioMaximoException(atorDto.getSalario());
        }

        // CRIACAO DO ATOR
        Ator ator = new Ator();
        ator.setNome(atorDto.getNome());
        ator.setDataNascimento(atorDto.getDataNascimento());
        ator.setCpf(atorDto.getCpf());
        ator.setEmail(atorDto.getEmail());
        ator.setSalario(atorDto.getSalario());
        ator.setGenero(atorDto.getGenero());
        ator.setNacionalidade(atorDto.getNacionalidade());
        ator.setTelefone(atorDto.getTelefone());

        // SALVA ATOR NO REPOSITORIO
        return atorRepository.save(ator);

    }

        //////////////////////////////////////////////////////////
        //                       EDIÇÃO DE ATOR
        ///////////////////////////////////////////////////////////
        
    public Ator editarAtor(Long id, AtorDto atorEditadoDto) {
    Ator atorExistente = atorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ator não encontrado: " + id));

                
    // CPF COM 11 DIGITOS OBRIGATORIOS
    if (atorEditadoDto.getCpf().length() != 11) {
        throw new CpfInvalidoException(atorEditadoDto.getCpf());
    }

    // GENEROS PERMITIDOS
    String genero = atorEditadoDto.getGenero().trim();
    if (!(genero.equalsIgnoreCase("Feminino")
       || genero.equalsIgnoreCase("Masculino")
       || genero.equalsIgnoreCase("Não Binário"))) {
        throw new GeneroInvalidoException(genero);
    }

    // IDADE MAXIMA
    LocalDate hoje = LocalDate.now();
    Period idade = Period.between(atorEditadoDto.getDataNascimento(), hoje);
    if (idade.getYears() > 120) {
        throw new IdadeInvalidaException(idade.getYears());
    }

    // CPF UNICO
    if (atorRepository.existsByCpf(atorEditadoDto.getCpf())) {
        throw new CpfDuplicadoException(atorEditadoDto.getCpf());
    }
   
    // SALÁRIO MAXIMO
    if (atorEditadoDto.getSalario().doubleValue() > 1_000_000) {
        throw new SalarioMaximoException(atorEditadoDto.getSalario());
    }

    // ATUALIZAÇÃO DOS DADOS
    atorExistente.setNome(atorEditadoDto.getNome());
    atorExistente.setDataNascimento(atorEditadoDto.getDataNascimento());
    atorExistente.setCpf(atorEditadoDto.getCpf());
    atorExistente.setEmail(atorEditadoDto.getEmail());
    atorExistente.setSalario(atorEditadoDto.getSalario());
    atorExistente.setGenero(genero);
    atorExistente.setNacionalidade(atorEditadoDto.getNacionalidade());
    atorExistente.setTelefone(atorEditadoDto.getTelefone());


    //SALVA A ALTERAÇÃO NO REPOSITORIO
    return atorRepository.save(atorExistente);
}

    // BUSCAR ATOR POR ID
    public Ator getAtorById(Long id) {
        return atorRepository.findById(id)
                .orElseThrow(() -> new AtorNaoEncontradoException("Ator não encontrado: " + id));
    }

    //EXCLUIR ATOR
    public void excluirAtor(Long id) {
        try {
            atorRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro ao excluir ator: " + e.getMessage());
        }
    }

}
