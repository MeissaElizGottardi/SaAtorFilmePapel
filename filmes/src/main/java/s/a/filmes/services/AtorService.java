package s.a.filmes.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import s.a.filmes.dto.AtorDto;
import s.a.filmes.exceptionAtor.AtorNaoEncontradoException;
import s.a.filmes.exceptionAtor.AtorVinculadoException;
import s.a.filmes.exceptionAtor.CpfDuplicadoException;
import s.a.filmes.exceptionAtor.CpfInvalidoException;
import s.a.filmes.exceptionAtor.EmailInvalidoException;
import s.a.filmes.exceptionAtor.GeneroInvalidoException;
import s.a.filmes.exceptionAtor.IdadeInvalidaException;
import s.a.filmes.exceptionAtor.NomeInvalidoException;
import s.a.filmes.exceptionAtor.SalarioInvalidoException;
import s.a.filmes.exceptionAtor.SalarioMaximoException;
import s.a.filmes.exceptionFilme.CampoNaoInformadoException;
import s.a.filmes.model.Ator;
import s.a.filmes.repository.AtorRepository;
import s.a.filmes.repository.PapelRepository;

@Service
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;

    @Autowired
    private PapelRepository papelRepository;

    // ADICIONAR ATOR
    public Ator adicionarAtor(AtorDto atorDto) {

        // 1 - Valida se campos obrigatórios estão presentes
        validarCamposObrigatorios(atorDto);

        // 2 - Verifica CPF duplicado antes de criar
        if (atorRepository.existsByCpf(atorDto.getCpf())) {
            throw new CpfDuplicadoException(atorDto.getCpf());
        }

        // 3 - Cria a entidade e popula os dados
        Ator ator = new Ator();
        mapDtoToEntity(atorDto, ator);

        // 4 - Valida as regras de negócio (Datas e Duração)
        validarRegrasDeNegocio(ator);

        // 5 - Salva o ator no repositório
        return atorRepository.save(ator);
    }

    // EDIÇÃO DE ATOR
    public Ator editarAtor(Long id, AtorDto atorDto) {

        // 1 - Procura o ator caso não encontrado lança exceção
        Ator ator = atorRepository.findById(id)
                .orElseThrow(() -> new AtorNaoEncontradoException(id));

        // 2 - Atualiza os campos se presentes
        atualizarAtor(atorDto.getNome(), "nome", ator::setNome);
        atualizarAtor(atorDto.getEmail(), "email", ator::setEmail);
        atualizarAtor(atorDto.getGenero(), "gênero", ator::setGenero);
        atualizarAtor(atorDto.getNacionalidade(), "nacionalidade", ator::setNacionalidade);
        atualizarAtor(atorDto.getTelefone(), "telefone", ator::setTelefone);

        // 3 - Atualiza o CPF se presente e diferente do atual
        if (atorDto.getCpf() != null) {
            String novoCpf = atorDto.getCpf().trim();
            if (novoCpf.isEmpty())
                throw new CampoNaoInformadoException("cpf");

            // 4 - Verifica se o novo CPF é diferente do atual para evitar validação
            // desnecessária
            if (!novoCpf.equals(ator.getCpf())) {
                if (atorRepository.existsByCpf(novoCpf)) {
                    throw new CpfDuplicadoException(novoCpf);
                }
                ator.setCpf(novoCpf);
            }
        }

        // 5 - Atualiza campos numéricos/datas se presentes
        if (atorDto.getSalario() != null)
            ator.setSalario(atorDto.getSalario());
        if (atorDto.getDataNascimento() != null)
            ator.setDataNascimento(atorDto.getDataNascimento());

        // 6 - Valida as regras com os novos dados aplicados
        validarRegrasDeNegocio(ator);

        // 7 - Salva as alterações
        return atorRepository.save(ator);

    }

    // BUSCAR ATOR POR ID
    public Ator getAtorById(Long id) {
        // 1 - Busca o ator, caso não encontrado lança exceção
        return atorRepository.findById(id)
                .orElseThrow(() -> new AtorNaoEncontradoException(id));
    }

    // EXCLUIR ATOR
    public void excluirAtor(Long id) {

        // 1 - Verifica se o ator existe, caso contrário lança exceção
        Ator ator = atorRepository.findById(id)
                .orElseThrow(() -> new AtorNaoEncontradoException(id));

        // 2 - Verifica se o ator está vinculado a algum papel, caso esteja lança
        // exceção
        if (papelRepository.findByAtor_Id(id).size() > 0) {
            throw new AtorVinculadoException(id);
        }

        // 3 - Se não houver vínculos, exclui o ator
        atorRepository.delete(ator);

    }

    // MÉTODOS AUXILIARES E VALIDAÇÕES
    private void validarCamposObrigatorios(AtorDto dto) {

        // 1 - Campos de Texto (Strings)
        if (StringUtils.isBlank(dto.getNome()))
            throw new CampoNaoInformadoException("Nome");
        if (StringUtils.isBlank(dto.getEmail()))
            throw new CampoNaoInformadoException("Email");
        if (StringUtils.isBlank(dto.getGenero()))
            throw new CampoNaoInformadoException("Genero");
        if (StringUtils.isBlank(dto.getNacionalidade()))
            throw new CampoNaoInformadoException("Nacionalidade");
        if (StringUtils.isBlank(dto.getTelefone()))
            throw new CampoNaoInformadoException("Telefone");
        if (StringUtils.isBlank(dto.getCpf()))
            throw new CampoNaoInformadoException("CPF");

        // 2 - Campos de Data
        if (dto.getDataNascimento() == null)
            throw new CampoNaoInformadoException("Data de Nascimento");

        // 3 - Campos Numéricos
        if (dto.getSalario() == null)
            throw new CampoNaoInformadoException("Salário");
    }

    // Método auxiliar para atualizar campos de texto de forma genérica, evitando
    // repetição de código
    private void atualizarAtor(String valor, String nomeCampo, Consumer<String> setter) {
        if (valor != null) {
            if (valor.trim().isEmpty())
                throw new CampoNaoInformadoException(nomeCampo);
            setter.accept(valor);
        }
    }

    // Validações de regras de negócio específicas para Ator (CPF, Gênero, Email,
    // Salário, Idade, etc.)
    private void validarRegrasDeNegocio(Ator ator) {

        // 1 - Valida formato do CPF (deve conter exatamente 11 dígitos numéricos)
        if (ator.getCpf().length() != 11) {
            throw new CpfInvalidoException(ator.getCpf());
        }

        // 2 - Valida o gênero (deve ser "Feminino", "Masculino" ou "Não Binário")
        String genero = ator.getGenero().trim();
        if (!(genero.equalsIgnoreCase("Feminino")
                || genero.equalsIgnoreCase("Masculino")
                || genero.equalsIgnoreCase("Não Binário"))) {
            throw new GeneroInvalidoException(genero);
        }

        // 3 - Valida formato do email (deve conter "@" e um domínio válido)
        String email = ator.getEmail();
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new EmailInvalidoException(email);
        }

        // 4 - Valida salário (não pode ser negativo)
        if (ator.getSalario().doubleValue() < 0) {
            throw new SalarioInvalidoException();
        }

        // 5 - Valida idade (não pode ser maior que 120 anos)
        LocalDate hoje = LocalDate.now();
        Period idade = Period.between(ator.getDataNascimento(), hoje);
        if (idade.getYears() > 120) {
            throw new IdadeInvalidaException(idade.getYears());
        }

        // 6 - Valida salário máximo (não pode ser maior que 1 milhão)
        if (ator.getSalario().doubleValue() > 1_000_000) {
            throw new SalarioMaximoException(ator.getSalario());
        }

        // 7 - Valida nome (não pode conter caracteres especiais ou acentos)
        if (ator.getNome() != null && !ator.getNome().matches("^[a-zA-Z0-9 ]+$")) {
            throw new NomeInvalidoException(ator.getNome());
        }

    }

    // Método auxiliar para mapear os dados do DTO para a entidade, evitando
    // repetição de código
    private void mapDtoToEntity(AtorDto dto, Ator entity) {
        entity.setNome(dto.getNome());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setSalario(dto.getSalario());
        entity.setGenero(dto.getGenero());
        entity.setNacionalidade(dto.getNacionalidade());
        entity.setTelefone(dto.getTelefone());

    }
}
