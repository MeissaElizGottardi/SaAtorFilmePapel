package s.a.filmes.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Papel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String personagemPrincipal;
    private String descricao;
    private LocalDate idade;
    private String tempoCena;
    private String importancia;

    


    public Papel() {}


    //ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //NOME
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    //PERSONAGEM PRINCIPAL
    public String getPersonagemPrincipal() {
        return personagemPrincipal;
    }
    public void setPersonagemPrincipal(String personagemPrincipal) {
        this.personagemPrincipal = personagemPrincipal;
    }

    //DESCRICAO
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //IDADE
    public LocalDate getIdade() {
        return idade;
    }
    public void setIdade(LocalDate idade) {
        this.idade = idade;
    }

    //TEMPO CENA
    public String getTempoCena() {
        return tempoCena;
    }
    public void setTempoCena(String tempoCena) {
        this.tempoCena = tempoCena;
    }

    //IMPORTANCIA
    public String getImportancia() {
        return importancia;
    }
    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }

}
