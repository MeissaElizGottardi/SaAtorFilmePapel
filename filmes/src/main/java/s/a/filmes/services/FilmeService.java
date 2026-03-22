package s.a.filmes.services;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import s.a.filmes.dto.FilmeDto;
import s.a.filmes.exceptionFilme.CampoNaoInformadoException;
import s.a.filmes.exceptionFilme.DatasInvalidasException;
import s.a.filmes.exceptionFilme.DescriçãoMinimaException;
import s.a.filmes.exceptionFilme.DuracaoInvalidaException;
import s.a.filmes.exceptionFilme.FilmeNaoEncontradoException;
import s.a.filmes.exceptionFilme.FilmeVinculadoException;
import s.a.filmes.exceptionFilme.OrcamentoValidoException;
import s.a.filmes.model.Filme;
import s.a.filmes.repository.FilmeRepository;
import s.a.filmes.repository.PapelRepository;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private PapelRepository papelRepository;

    // ADICIONAR FILME
    public Filme adicionarFilme(FilmeDto filmeDto) {

        // 1 - Valida se campos obrigatórios estão presentes
        validarCamposObrigatorios(filmeDto);

        // 2 - Cria a entidade e popula os dados
        Filme filme = new Filme();
        mapDtoToEntity(filmeDto, filme);

        // 3 - Valida as regras de negócio (Datas e Duração)
        validarRegrasDeNegocio(filme);

        return filmeRepository.save(filme);
    }

    // EDIÇÃO DE FILME
    public Filme editarFilme(Long id, FilmeDto filmeDto) {

        // 1 - Procura o filme caso não encontrado lança exceção
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new FilmeNaoEncontradoException(id));

        // 2 - Atualiza os campos se presentes
        atualizarFilme(filmeDto.getNome(), "nome", filme::setNome);
        atualizarFilme(filmeDto.getDescricao(), "descrição", filme::setDescricao);
        atualizarFilme(filmeDto.getDiretor(), "diretor", filme::setDiretor);
        atualizarFilme(filmeDto.getGenero(), "gênero", filme::setGenero);
        atualizarFilme(filmeDto.getIdiomaOriginal(), "idioma", filme::setIdiomaOriginal);
        atualizarFilme(filmeDto.getPaisOrigem(), "país de origem", filme::setPaisOrigem);
        atualizarFilme(filmeDto.getClassificacaoIndicativa(), "classificação", filme::setClassificacaoIndicativa);

        // 3 - Atualiza campos numéricos/datas se presentes
        if (filmeDto.getDuracao() != null)
            filme.setDuracao(filmeDto.getDuracao());
        if (filmeDto.getOrcamento() != null)
            filme.setOrcamento(filmeDto.getOrcamento());

        if (filmeDto.getDataPrevistaInicioGravacoes() != null)
            filme.setDataPrevistaInicioGravacoes(filmeDto.getDataPrevistaInicioGravacoes());
        if (filmeDto.getDataPrevistaFimGravacoes() != null)
            filme.setDataPrevistaFimGravacoes(filmeDto.getDataPrevistaFimGravacoes());
        if (filmeDto.getDataPrevistaLancamento() != null)
            filme.setDataPrevistaLancamento(filmeDto.getDataPrevistaLancamento());

        // 4 - Valida as regras de negócio
        validarRegrasDeNegocio(filme);

        // 5 - Salva as alterações
        return filmeRepository.save(filme);
    }

    // BUSCAR FILME POR ID
    public Filme getFilmeById(Long id) {
        // 1 - Busca o filme, caso não encontrado lança exceção
        return filmeRepository.findById(id)
                .orElseThrow(() -> new FilmeNaoEncontradoException(id));
    }

    // EXCLUIR FILME POR ID
    public void excluirFilme(Long id) {

        // 1 - Verifica se o filme existe, caso contrário lança exceção
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new FilmeNaoEncontradoException(id));

        // 2 - não exclui se houver papéis vinculados
        if (papelRepository.findByFilme_Id(id).size() > 0) {
            throw new FilmeVinculadoException(id);
        }

        // 3 - Exclui o filme
        filmeRepository.delete(filme);
    }

    // MÉTODOS AUXILIARES E VALIDAÇÕES
    private void validarCamposObrigatorios(FilmeDto dto) {

        // 1 - Campos de Texto (Strings)
        if (StringUtils.isBlank(dto.getNome()))
            throw new CampoNaoInformadoException("Nome");
        if (StringUtils.isBlank(dto.getDiretor()))
            throw new CampoNaoInformadoException("Diretor");
        if (StringUtils.isBlank(dto.getDescricao()))
            throw new CampoNaoInformadoException("Descrição");
        if (StringUtils.isBlank(dto.getGenero()))
            throw new CampoNaoInformadoException("Gênero");
        if (StringUtils.isBlank(dto.getIdiomaOriginal()))
            throw new CampoNaoInformadoException("Idioma Original");
        if (StringUtils.isBlank(dto.getPaisOrigem()))
            throw new CampoNaoInformadoException("País de Origem");
        if (StringUtils.isBlank(dto.getClassificacaoIndicativa()))
            throw new CampoNaoInformadoException("Classificação Indicativa");

        // 2- Campos Numéricos (Integer/Double/BigDecimal)
        if (dto.getDuracao() == null)
            throw new CampoNaoInformadoException("Duração");
        if (dto.getOrcamento() == null)
            throw new CampoNaoInformadoException("Orçamento");

        // 3 -Campos de Data (LocalDate)
        if (dto.getDataPrevistaInicioGravacoes() == null)
            throw new CampoNaoInformadoException("Data Prevista de Início das Gravações");
        if (dto.getDataPrevistaFimGravacoes() == null)
            throw new CampoNaoInformadoException("Data Prevista de Fim das Gravações");
        if (dto.getDataPrevistaLancamento() == null)
            throw new CampoNaoInformadoException("Data Prevista de Lançamento");

    }

    // VALIDAÇÃO DE REGRAS DE NEGOCIO
    private void validarRegrasDeNegocio(Filme filme) {

        // 1 - validação de duração (deve ser maior que 0 e menor que 600 minutos)
        if (filme.getDuracao() <= 0 || filme.getDuracao() > 600) {
            throw new DuracaoInvalidaException(filme.getDuracao());
        }

        // 2 - validação de descrição (deve ter no mínimo 50 caracteres)
        if (filme.getDescricao() != null && filme.getDescricao().length() < 50) {
            throw new DescriçãoMinimaException(50);
        }

        // 3 - validação de orçamento (não pode ser negativo)
        if (filme.getOrcamento().doubleValue() < 0) {
            throw new OrcamentoValidoException();
        }

        // 4 - validação de datas (data de fim das gravações não pode ser antes da data
        // de início, e data de lançamento não pode ser antes da data de fim das
        // gravações)
        if (filme.getDataPrevistaFimGravacoes().isBefore(filme.getDataPrevistaInicioGravacoes())) {
            throw DatasInvalidasException.fimAntesDoInicio();
        }
        if (filme.getDataPrevistaLancamento().isBefore(filme.getDataPrevistaFimGravacoes())) {
            throw DatasInvalidasException.lancamentoPrecoce();
        }
    }

    // Método auxiliar para atualizar campos de texto de forma genérica, evitando
    // repetição de código
    private void atualizarFilme(String valor, String nomeCampo, Consumer<String> setter) {
        if (valor != null) {
            if (valor.trim().isEmpty())
                throw new CampoNaoInformadoException(nomeCampo);
            setter.accept(valor);
        }
    }

    // método genérico para atualizar campos numéricos e de data (sem validação de
    // string vazia, apenas verifica se é diferente de null)
    private void mapDtoToEntity(FilmeDto dto, Filme entity) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setDiretor(dto.getDiretor());
        entity.setGenero(dto.getGenero());
        entity.setDuracao(dto.getDuracao());
        entity.setOrcamento(dto.getOrcamento());
        entity.setIdiomaOriginal(dto.getIdiomaOriginal());
        entity.setPaisOrigem(dto.getPaisOrigem());
        entity.setClassificacaoIndicativa(dto.getClassificacaoIndicativa());
        entity.setDataPrevistaInicioGravacoes(dto.getDataPrevistaInicioGravacoes());
        entity.setDataPrevistaFimGravacoes(dto.getDataPrevistaFimGravacoes());
        entity.setDataPrevistaLancamento(dto.getDataPrevistaLancamento());
    }
}