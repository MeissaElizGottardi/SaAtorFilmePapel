package s.a.filmes.dto;

import java.time.LocalDate;

public class PapelDto {

    private String nome;
    private String personagemPrincipal;
    private String descricao;
    private LocalDate idade;
    private String tempoCena;
    private String importancia;

    public PapelDto() {
    }

    public PapelDto(String nome, String personagemPrincipal, String descricao, LocalDate idade, String tempoCena,
            String importancia) {
        this.nome = nome;
        this.personagemPrincipal = personagemPrincipal;
        this.descricao = descricao;
        this.idade = idade;
        this.tempoCena = tempoCena;
        this.importancia = importancia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPersonagemPrincipal() {
        return personagemPrincipal;
    }

    public void setPersonagemPrincipal(String personagemPrincipal) {
        this.personagemPrincipal = personagemPrincipal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getIdade() {
        return idade;
    }

    public void setIdade(LocalDate idade) {
        this.idade = idade;
    }

    public String getTempoCena() {
        return tempoCena;
    }

    public void setTempoCena(String tempoCena) {
        this.tempoCena = tempoCena;
    }

    public String getImportancia() {
        return importancia;
    }

    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }
}
