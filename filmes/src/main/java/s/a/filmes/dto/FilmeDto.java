package s.a.filmes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FilmeDto {

    // Atributos do FilmeDto
    private String nome;
    private String diretor;
    private String descricao;
    private String genero;
    private BigDecimal orcamento;
    private String idiomaOriginal;
    private Integer duracao;
    private String paisOrigem;
    private String classificacaoIndicativa;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPrevistaInicioGravacoes;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPrevistaFimGravacoes;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPrevistaLancamento;

    // 1- Construtor Padrão
    public FilmeDto() {
    }

    // 2- Construtor Completo
    public FilmeDto(String nome, String diretor, String descricao, String genero, BigDecimal orcamento,
            String idiomaOriginal, Integer duracao, String paisOrigem, String classificacaoIndicativa,
            LocalDate dataPrevistaInicioGravacoes, LocalDate dataPrevistaFimGravacoes,
            LocalDate dataPrevistaLancamento) {
        this.nome = nome;
        this.diretor = diretor;
        this.descricao = descricao;
        this.genero = genero;
        this.orcamento = orcamento;
        this.idiomaOriginal = idiomaOriginal;
        this.duracao = duracao;
        this.paisOrigem = paisOrigem;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.dataPrevistaInicioGravacoes = dataPrevistaInicioGravacoes;
        this.dataPrevistaFimGravacoes = dataPrevistaFimGravacoes;
        this.dataPrevistaLancamento = dataPrevistaLancamento;
    }

    // GETTERS E SETTERS
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
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

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
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
}