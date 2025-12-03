package s.a.filmes.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

    @Entity
    public class Ator {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private BigDecimal salario;
    private String genero;
    private String nacionalidade;
    private String telefone;
;

    public Ator() {}    

    //ID
    public Long getId() {
        return id;}

    public void setId(Long id) {
        this.id = id;}


    //NOME
    public String getNome() {
        return nome;}

    public void setNome(String nome) {
        this.nome = nome;}

    //DATA DE NASCIMENTO
    public LocalDate getDataNascimento() {
        return dataNascimento;}

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;}

    //CPF
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    //EMAIL
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    //SALARIO
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal  salario) {
        this.salario = salario;
    }

    //GENERO
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


    //NACIONALIDADE
    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    //TELEFONE
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}