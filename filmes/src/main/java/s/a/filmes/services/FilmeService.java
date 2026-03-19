package s.a.filmes.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import s.a.filmes.dto.FilmeDto;
import s.a.filmes.exceptionFilme.CampoNaoInformadoException;
import s.a.filmes.exceptionFilme.FilmeNaoEncontradoException;
import s.a.filmes.model.Ator;
import s.a.filmes.model.Filme;
import s.a.filmes.repository.AtorRepository;
import s.a.filmes.repository.FilmeRepository;
import s.a.filmes.repository.PapelRepository;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private AtorRepository atorRepository;

    @Autowired
    private PapelRepository papelRepository;

    // 01 - Adicionar filme
    public Filme adicionarFilme(FilmeDto filmeDto) {

        // Validações obrigatórias
        if (StringUtils.isBlank(filmeDto.getNome())) {
            throw new CampoNaoInformadoException("Nome");
        }
        if (StringUtils.isBlank(filmeDto.getDescricao())) {
            throw new CampoNaoInformadoException("Descricao");
        }
        if (StringUtils.isBlank(filmeDto.getDiretor())) {
            throw new CampoNaoInformadoException("Diretor");
        }
        if (StringUtils.isBlank(filmeDto.getGenero())) {
            throw new CampoNaoInformadoException("Genero");
        }
        if (filmeDto.getDuracao() == null || filmeDto.getDuracao() <= 0) {
            throw new CampoNaoInformadoException("Duração");
        }
        if (filmeDto.getOrcamento() == null || filmeDto.getOrcamento().intValue() <= 0) {
            throw new CampoNaoInformadoException("Orçamento");
        }
        if (StringUtils.isBlank(filmeDto.getIdiomaOriginal())) {
            throw new CampoNaoInformadoException("Idioma");
        }
        if (StringUtils.isBlank(filmeDto.getPaisOrigem())) {
            throw new CampoNaoInformadoException("Pais de Origem");
        }
        if (StringUtils.isBlank(filmeDto.getClassificacaoIndicativa())) {
            throw new CampoNaoInformadoException("Classificação Indicativa");
        }
        if (filmeDto.getDataLancamento() == null) {
            throw new CampoNaoInformadoException("Data de lançamento");
        }
        if (filmeDto.getDataFimGravacoes() == null) {
            throw new CampoNaoInformadoException("Data de Fim das gravações");
        }

        // Regra 5: duração válida (máximo 10 horas)
        if (filmeDto.getDuracao() <= 0 || filmeDto.getDuracao() > 600) {
            throw new IllegalArgumentException("Duração inválida.");
        }

        // Criação do objeto Filme
        Filme filme = new Filme();
        filme.setNome(filmeDto.getNome());
        filme.setDescricao(filmeDto.getDescricao());
        filme.setDiretor(filmeDto.getDiretor());
        filme.setGenero(filmeDto.getGenero());
        filme.setDuracao(filmeDto.getDuracao());
        filme.setOrcamento(filmeDto.getOrcamento());
        filme.setIdiomaOriginal(filmeDto.getIdiomaOriginal());
        filme.setPaisOrigem(filmeDto.getPaisOrigem());
        filme.setClassificacaoIndicativa(filmeDto.getClassificacaoIndicativa());
        filme.setDataLancamento(filmeDto.getDataLancamento());
        filme.setDataFimGravacoes(filmeDto.getDataFimGravacoes());
        filme.setGravacoesIniciadas(false);
        filme.setPublicado(false);

        return filmeRepository.save(filme);

    }

    // 02 - Editar filme
    public Filme editarFilme(Long id, FilmeDto filmeDto) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new FilmeNaoEncontradoException("Filme não encontrado: " + id));

        // SALVA NOVAS INFORMAÇÕES
        return filmeRepository.save(filme);

    }

    // 03 INICIAR AS GRAVAÇÕES
    public Filme iniciarGravacoes(Long id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new FilmeNaoEncontradoException("Filme não encontrado: " + id));

        // Regra 1: pelo menos 5 atores cadastrados
        List<Ator> atores = atorRepository.findByFilmes_Id(filme.getId());
        if (atores.size() < 5) {
            throw new IllegalStateException("O filme precisa ter no mínimo 5 atores cadastrados.");
        }

        filme.setGravacoesIniciadas(true);
        return filmeRepository.save(filme);
    }

    // 04 - Publicar filme
    public Filme publicarFilme(Long id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new FilmeNaoEncontradoException("Filme não encontrado: " + id));

        // Regra 6: data de lançamento >= data de fim das gravações
        LocalDate fimGravacoes = filme.getDataFimGravacoes();
        LocalDate lancamento = filme.getDataLancamento();
        if (fimGravacoes != null && lancamento != null && lancamento.isBefore(fimGravacoes)) {
            throw new IllegalStateException("Data de lançamento não pode ser anterior ao fim das gravações.");
        }

        filme.setPublicado(true);
        return filmeRepository.save(filme);
    }

    // 05 - Buscar filme
    public Filme getFilmeById(Long id) {
        Filme filmeTeste = filmeRepository.findById(id)
                .orElseThrow(() -> new FilmeNaoEncontradoException("Filme não encontrado: " + id));
        return filmeTeste;
    }

    // 06 - Remover filme
    public void excluirFilme(Long id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new FilmeNaoEncontradoException("Filme não encontrado: " + id));

        // Regra 4: não remover se houver papéis vinculados
        if (!papelRepository.findByFilme_Id(filme.getId()).isEmpty()) {
            throw new IllegalStateException("Filme não pode ser removido, possui papéis vinculados.");
        }

        filmeRepository.delete(filme);

    }
}
