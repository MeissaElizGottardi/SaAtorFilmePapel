package s.a.filmes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FilmeDto {

    private String nome;
    private String diretor;
    private String descricao;
    private String genero;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLancamento;
    private BigDecimal orcamento;
    private String idiomaOriginal;
    private Integer duracao;
    private String paisOrigem;
    private String classificacaoIndicativa;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFimGravacoes;
    private boolean publicado = false;
    private boolean gravacoesIniciadas = false;

    public FilmeDto() {

    }

    public FilmeDto(String classificacaoIndicativa, LocalDate dataFimGravacoes, LocalDate dataLancamento, String descricao, String diretor, Integer duracao, String genero, String idiomaOriginal, String nome, BigDecimal orcamento, String paisOrigem) {
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.dataFimGravacoes = dataFimGravacoes;
        this.dataLancamento = dataLancamento;
        this.descricao = descricao;
        this.diretor = diretor;
        this.duracao = duracao;
        this.genero = genero;
        this.idiomaOriginal = idiomaOriginal;
        this.nome = nome;
        this.orcamento = orcamento;
        this.paisOrigem = paisOrigem;
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

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
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

    public LocalDate getDataFimGravacoes() {
        return dataFimGravacoes;
    }

    public void setDataFimGravacoes(LocalDate dataFimGravacoes) {
        this.dataFimGravacoes = dataFimGravacoes;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    public boolean isGravacoesIniciadas() {
        return gravacoesIniciadas;
    }

    public void setGravacoesIniciadas(boolean gravacoesIniciadas) {
        this.gravacoesIniciadas = gravacoesIniciadas;
    }

}
