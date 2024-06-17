package com.gerfy.literalura.literalura.model;

import jakarta.persistence.*;

@Entity

@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private String idioma;
    private Integer numeroDeDownloads;

    @ManyToOne
    private Autor autor;

    public Livro() {}
    public Livro(DadosLivro dadosLivro) {
        this.nome = dadosLivro.nomeDoLivro();
        this.idioma = String.join(",",dadosLivro.idiomas());
        this.numeroDeDownloads = dadosLivro.numeroDeDownloads();
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Integer numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }

    @Override
    public String toString() {
        return
                "\nNome:" + nome + " | " +
                "\nAutor: " +autor.getNome() + " | " +
                "\nNumero de downloads: " + numeroDeDownloads + " | " +
                "\nIdiomas: " + idioma + " | " ;
    }
}
