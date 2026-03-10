package s.a.filmes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FilmeDto {
    
    private String nome;
    private String diretor;
    private String descricao;
    private String genero;
    private LocalDate dataLancamento;
    private BigDecimal orcamento;
    private String idiomaOriginal;
    private String duracao;
    private String paisOrigem;
    private String classificacaoIndicativa;


    public FilmeDto() {

    }

    public FilmeDto( String nome, String diretor, String descricao, String genero, LocalDate dataLancamento,
            BigDecimal orcamento, String idiomaOriginal, String duracao, String paisOrigem,
            String classificacaoIndicativa) {
        this.nome = nome;
        this.diretor = diretor;
        this.descricao = descricao;
        this.genero = genero;
        this.dataLancamento = dataLancamento;
        this.orcamento = orcamento;
        this.idiomaOriginal = idiomaOriginal;
        this.duracao = duracao;
        this.paisOrigem = paisOrigem;
        this.classificacaoIndicativa = classificacaoIndicativa;
    }


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
    public String getDuracao() {
        return duracao;
    }
    public void setDuracao(String duracao) {
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

}


