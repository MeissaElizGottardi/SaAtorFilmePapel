package s.a.filmes.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import s.a.filmes.dto.PapelDto;
import s.a.filmes.exceptionAtor.AtorNaoEncontradoException;
import s.a.filmes.exceptionPapel.IdadeInvalidaException;
import s.a.filmes.exceptionFilme.CampoNaoInformadoException;
import s.a.filmes.exceptionFilme.FilmeNaoEncontradoException;
import s.a.filmes.exceptionPapel.CaracteresInvalidosException;
import s.a.filmes.exceptionPapel.DescricaoMinimaException;
import s.a.filmes.exceptionPapel.ExcluirPapelException;
import s.a.filmes.exceptionPapel.PapelNaoEncontradoException;

import s.a.filmes.model.Ator;
import s.a.filmes.model.Filme;
import s.a.filmes.model.Papel;
import s.a.filmes.repository.AtorRepository;
import s.a.filmes.repository.FilmeRepository;
import s.a.filmes.repository.PapelRepository;

@Service
public class PapelService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private AtorRepository atorRepository;

    @Autowired
    private PapelRepository papelRepository;

    // ADICIONAR PAPEL
    public Papel adicionarPapel(PapelDto papelDto) {

        // 1 - valida se campos obrigatórios estão presentes
        validarCamposObrigatorios(papelDto);

        // 2 - Cria a entidade e popula os dados
        Papel papel = new Papel();
        mapDtoToEntity(papelDto, papel);

        // 3 - valida as regras de negócio
        validarRegrasDeNegocio(papel);

        // 3 - Salva o papel no repositório
        return papelRepository.save(papel);
    }

    // EDITAR PAPEL
    public Papel editarPapel(Long id, PapelDto papelEditadoDto) {

        // 1 - Busca o papel existente e lança exception se não encontrar
        Papel papelDto = papelRepository.findById(id)
                .orElseThrow(() -> new PapelNaoEncontradoException(id));

        // 2 - Atualiza campos básicos
        atualizarPapel(papelEditadoDto.getNome(), "Nome", papelDto::setNome);
        atualizarPapel(papelEditadoDto.getDescricao(), "Descrição", papelDto::setDescricao);

        if (papelEditadoDto.getTempoCena() != null) {
            papelDto.setTempoCena(papelEditadoDto.getTempoCena());
        }

        if (papelEditadoDto.getPersonagemPrincipal() != null) {
            papelDto.setPersonagemPrincipal(papelEditadoDto.getPersonagemPrincipal());
        }

        if (papelEditadoDto.getIdade() != null) {
            papelDto.setIdade(papelEditadoDto.getIdade());
        }

        // 3 - Valida e Atualiza o Ator
        if (papelEditadoDto.getIdAtor() != null) {
            // Se enviou um ID, busca e vincula
            Ator ator = atorRepository.findById(papelEditadoDto.getIdAtor())
                    .orElseThrow(() -> new AtorNaoEncontradoException(papelEditadoDto.getIdAtor()));
            papelDto.setAtor(ator);
        } else {
            // se veio nulo no JSON, limpa o vínculo no banco
            papelDto.setAtor(null);
        }

        // 4 - Valida e Atualiza o Filme
        if (papelEditadoDto.getIdFilme() != null) {
            // Se enviou um ID, busca e vincula
            Filme filme = filmeRepository.findById(papelEditadoDto.getIdFilme())
                    .orElseThrow(() -> new FilmeNaoEncontradoException(papelEditadoDto.getIdFilme()));
            papelDto.setFilme(filme);
        } else {
            // se veio nulo no JSON, limpa o vínculo no banco
            papelDto.setFilme(null);
        }

        // 5 - Valida as regras
        validarRegrasDeNegocio(papelDto);

        // 6 - Salva as alterações
        return papelRepository.save(papelDto);
    }

    // BUSCAR PAPEL POR ID
    public Papel getPapelById(Long id) {

        // 1 - Busca o papel pelo id, se nao encontrar lança a exception de papel nao
        // encontrado
        return papelRepository.findById(id)
                .orElseThrow(() -> new PapelNaoEncontradoException(id));
    }

    // EXCLUIR PAPEL
    public void excluirPapel(Long id) {

        // 1- Procura o papel pelo id, se nao encontrar lança a exception de papel nao
        // encontrado
        Papel papel = papelRepository.findById(id)
                .orElseThrow(() -> new PapelNaoEncontradoException(id));

        // 2 - Verifica se o papel está associado a um filme ou ator antes de excluir,
        // caso
        // tiver nao exclui e lança a exception.
        if (papel.getAtor() != null || papel.getFilme() != null) {
            throw new ExcluirPapelException(id);

        }
        // 3 - Se não tiver associações, pode excluir normalmente.
        papelRepository.delete(papel);
    }

    // MÉTODOS AUXILIARES E VALIDAÇÕES
    private void validarCamposObrigatorios(PapelDto dto) {

        // 1 - Campos de Texto (Strings)
        if (StringUtils.isBlank(dto.getNome()))
            throw new CampoNaoInformadoException("Nome");
        if (StringUtils.isBlank(dto.getDescricao()))
            throw new CampoNaoInformadoException("Descrição");

        // 2 - campos numericos
        if (dto.getTempoCena() == null)
            throw new CampoNaoInformadoException("Tempo de Cena");

        // 3 - Campos Booleanos
        if (dto.getPersonagemPrincipal() == null)
            throw new CampoNaoInformadoException("Personagem Principal");

        // 4 - Campos de Data
        if (dto.getIdade() == null)
            throw new CampoNaoInformadoException("Idade");

        // 5 - Validação do ID do Filme
        if (dto.getIdFilme() == null || dto.getIdFilme() <= 0) {
            throw new CampoNaoInformadoException("ID do Filme");
        }

        // 6 - Validação do ID do Ator
        if (dto.getIdAtor() == null || dto.getIdAtor() <= 0) {
            throw new CampoNaoInformadoException("ID do Ator");
        }

    }

    // VALIDAÇÕES DE REGRAS DE NEGOCIO
    private void validarRegrasDeNegocio(Papel papel) {

        // 1 - Regra: A descrição do papel deve conter no mínimo 50 caracteres
        if (papel.getDescricao() != null && papel.getDescricao().length() < 50) {
            throw new DescricaoMinimaException(50);
        }

        // 2 - Regra: O nome do papel não pode conter caracteres especiais ou acentos
        if (papel.getNome() != null && !papel.getNome().matches("^[a-zA-Z0-9 ]+$")) {
            throw new CaracteresInvalidosException("Nome");
        }

        // 3 - Regra: Um ator menor de idade não pode interpretar um papel de adulto
        // (18+)
        if (papel.getAtor() != null && papel.getIdade() != null) {

            // Calcula a idade REAL do Ator
            int idadeRealAtor = Period.between(papel.getAtor().getDataNascimento(), LocalDate.now()).getYears();

            // Calcula a idade do PERSONAGEM (baseado na data de nascimento fictícia do
            // papel)
            int idadeFicticiaPersonagem = Period.between(papel.getIdade(), LocalDate.now()).getYears();

            // REGRA: Se o Ator for menor de 18 anos
            if (idadeRealAtor < 18) {

                // Ele NÃO pode interpretar um personagem que tenha 18 anos ou mais
                if (idadeFicticiaPersonagem >= 18) {
                    throw new IdadeInvalidaException();
                }

            }
        }
    }

    // MÉTODO AUXILIAR PARA ATUALIZAÇÃO INTELIGENTE DE CAMPOS (SÓ ATUALIZA SE O
    // VALOR FOR DIFERENTE E NÃO VAZIO)
    private void atualizarPapel(String valor, String nomeCampo, Consumer<String> setter) {
        if (valor != null) {
            if (valor.trim().isEmpty())
                throw new CampoNaoInformadoException(nomeCampo);
            setter.accept(valor);
        }
    }

    // MÉTODO AUXILIAR PARA MAPEAR DADOS DO DTO PARA A ENTIDADE (TANTO PARA ADIÇÃO
    // QUANTO PARA EDIÇÃO)
    private void mapDtoToEntity(PapelDto dto, Papel entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPersonagemPrincipal(dto.getPersonagemPrincipal());
        entity.setIdade(dto.getIdade());
        entity.setTempoCena(dto.getTempoCena());
        entity.setPersonagemPrincipal(dto.getPersonagemPrincipal());

        // 1 - Mapeamento do Ator (busca o ator pelo ID e associa, ou lança exception se
        // não encontrar)
        if (dto.getIdAtor() != null) {
            var ator = atorRepository.findById(dto.getIdAtor())
                    .orElseThrow(() -> new AtorNaoEncontradoException(dto.getIdAtor()));
            entity.setAtor(ator);
        }

        // 2 - Mapeamento do Filme (busca o filme pelo ID e associa, ou lança exception
        // se não encontrar)
        if (dto.getIdFilme() != null) { // <--- Use getIdFilme() em vez de getFilmeId()
            var filme = filmeRepository.findById(dto.getIdFilme())
                    .orElseThrow(() -> new FilmeNaoEncontradoException(dto.getIdFilme()));
            entity.setFilme(filme);
        }
    }
}