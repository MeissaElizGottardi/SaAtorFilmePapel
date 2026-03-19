package s.a.filmes.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

    @Entity
    public class Ator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private BigDecimal salario;
    private String genero;
    private String nacionalidade;
    private String telefone;

    
@ManyToMany(mappedBy = "atores")
private List<Filme> filmes;
    public Ator() {}    

    public Ator(String cpf, LocalDate dataNascimento, String email, String genero, String nacionalidade, String nome, BigDecimal salario, String telefone) {
        this.cpf = cpf;

        this.dataNascimento = dataNascimento;
        this.email = email;
        this.genero = genero;
        this.nacionalidade = nacionalidade;
        this.nome = nome;
        this.salario = salario;
        this.telefone = telefone;
    }

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