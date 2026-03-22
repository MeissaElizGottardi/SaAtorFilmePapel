package s.a.filmes.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

// Anotação para evitar problemas de referência circular
@JsonPropertyOrder({ "id", "nome", "personagemPrincipal", "descricao", "idade", "tempoCena", "ator", "filme" })
@Entity
public class Papel {

    // Atributos do Papel
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Boolean personagemPrincipal;
    private String descricao;
    private LocalDate idade;
    private Integer tempoCena;

    // Relacionamento com Filme
    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Filme filme;

    // Relacionamento com Ator
    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Ator ator;

    // Construtor padrão
    public Papel() {
    }

    // Construtor completo
    public Papel(String descricao, Boolean personagemPrincipal, LocalDate idade, String nome, Integer tempo,
            Filme filme, Ator ator) {
        this.descricao = descricao;
        this.personagemPrincipal = personagemPrincipal;
        this.idade = idade;
        this.nome = nome;
        this.tempoCena = tempo;
        this.filme = filme;
        this.ator = ator;
    }

    // Getters e Setters
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

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Ator getAtor() {
        return ator;
    }

    public void setAtor(Ator ator) {
        this.ator = ator;
    }

}
