package s.a.filmes.dto;

import java.time.LocalDate;

//
public class PapelDto {

    // Atributos do PapelDto
    private String nome;
    private Boolean personagemPrincipal;
    private String descricao;
    private LocalDate idade;
    private Integer tempoCena;
    private Long idFilme;
    private Long idAtor;

    // 1- Construtor Padrão
    public PapelDto() {
    }

    // 2- Construtor Completo
    public PapelDto(String nome, Boolean personagemPrincipal, String descricao, LocalDate idade,
            Integer tempoCena, Long idFilme, Long idAtor) {
        this.nome = nome;
        this.personagemPrincipal = personagemPrincipal;
        this.descricao = descricao;
        this.idade = idade;
        this.tempoCena = tempoCena;
        this.idFilme = idFilme;
        this.idAtor = idAtor;
    }

    // GETTERS E SETTERS
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getPersonagemPrincipal() {
        return personagemPrincipal;
    }

    public void setPersonagemPrincipal(Boolean personagemPrincipal) {
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

    public Integer getTempoCena() {
        return tempoCena;
    }

    public void setTempoCena(Integer tempoCena) {
        this.tempoCena = tempoCena;
    }

    // MUITO IMPORTANTE: Getters e Setters para os IDs de relacionamento
    public Long getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Long idFilme) {
        this.idFilme = idFilme;
    }

    public Long getIdAtor() {
        return idAtor;
    }

    public void setIdAtor(Long idAtor) {
        this.idAtor = idAtor;
    }
}