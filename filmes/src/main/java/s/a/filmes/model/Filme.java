package s.a.filmes.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Filme {

    // Atributos do Filme
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String genero;
    private String diretor;
    private BigDecimal orcamento;
    private String idiomaOriginal;
    private String paisOrigem;
    private Integer duracao; // em minutos
    private String classificacaoIndicativa;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPrevistaInicioGravacoes;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPrevistaFimGravacoes;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPrevistaLancamento;

    // Relacionamento com Ator (muitos para muitos)
    @ManyToMany
    @JoinTable(name = "filme_ator", joinColumns = @JoinColumn(name = "filme_id"), inverseJoinColumns = @JoinColumn(name = "ator_id"))
    private List<Ator> atores;

    // Relação com Papel (um-para-muitos)
    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Papel> papeis;

    // Construtor padrão
    public Filme() {
    }

    // Construtor completo
    public Filme(String descricao, String diretor, String genero, String idiomaOriginal, String nome,
            BigDecimal orcamento, String paisOrigem, Integer duracao, String classificacaoIndicativa,
            LocalDate dataPrevistaInicioGravacoes, LocalDate dataPrevistaFimGravacoes,
            LocalDate dataPrevistaLancamento) {
        this.descricao = descricao;
        this.diretor = diretor;
        this.genero = genero;
        this.idiomaOriginal = idiomaOriginal;
        this.nome = nome;
        this.orcamento = orcamento;
        this.paisOrigem = paisOrigem;
        this.duracao = duracao;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.dataPrevistaInicioGravacoes = dataPrevistaInicioGravacoes;
        this.dataPrevistaFimGravacoes = dataPrevistaFimGravacoes;
        this.dataPrevistaLancamento = dataPrevistaLancamento;
    }

    // GETTERS E SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public BigDecimal getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(BigDecimal orcamento) {
        this.orcamento = orcamento;
    }

    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }

    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(String classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public LocalDate getDataPrevistaInicioGravacoes() {
        return dataPrevistaInicioGravacoes;
    }

    public void setDataPrevistaInicioGravacoes(LocalDate dataPrevistaInicioGravacoes) {
        this.dataPrevistaInicioGravacoes = dataPrevistaInicioGravacoes;
    }

    public LocalDate getDataPrevistaFimGravacoes() {
        return dataPrevistaFimGravacoes;
    }

    public void setDataPrevistaFimGravacoes(LocalDate dataPrevistaFimGravacoes) {
        this.dataPrevistaFimGravacoes = dataPrevistaFimGravacoes;
    }

    public LocalDate getDataPrevistaLancamento() {
        return dataPrevistaLancamento;
    }

    public void setDataPrevistaLancamento(LocalDate dataPrevistaLancamento) {
        this.dataPrevistaLancamento = dataPrevistaLancamento;
    }

    public List<Ator> getAtores() {
        return atores;
    }

    public void setAtores(List<Ator> atores) {
        this.atores = atores;
    }

    public List<Papel> getPapeis() {
        return papeis;
    }

    public void setPapeis(List<Papel> papeis) {
        this.papeis = papeis;
    }
}