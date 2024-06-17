package com.gerfy.literalura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String nome;
    private Integer anoNascimento;
    private Integer anoMorte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor() {}
    public Autor(Autor dados) {
        this.nome = dados.nomeAutor();
        this.anoNascimento = dados.anoNascimento();
        this.anoMorte = dados.anoMorte();
    }

    private Integer anoMorte() {
        return anoMorte;
    }

    private Integer anoNascimento() {
        return anoNascimento;
    }

    private String nomeAutor() {
        return nome;
    }

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

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoMorte() {
        return anoMorte;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    private void setAutor(Autor autor) {
    }

    public void setAnoMorte(Integer anoMorte) {
        this.anoMorte = anoMorte;
    }
    @Override
    public String toString() {
        return
                "\nNome: " + nome + " | " +
                        "\nAno de nascimento" + anoNascimento + " | " +
                        "\nAno de falecimento" + anoMorte + " | ";
    }
}
