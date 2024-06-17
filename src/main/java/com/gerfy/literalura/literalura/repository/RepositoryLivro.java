package com.gerfy.literalura.literalura.repository;

import com.gerfy.literalura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositoryLivro extends JpaRepository<Livro, Long> {

    Boolean existsByNome(String nome);

    @Query("SELECT DISTINCT l.idioma FROM Livro l ORDER BY l.idioma")
    List<String > buscaridiomas();

    @Query("SELECT l FROM Livro l WHERE  idioma = :idiomaSelecionado")
    List<Livro> buscarPorIdioma(String idiomaSelecionado);

    Livro findByNome(String nome);

    @Query("SELECT l FROM Livro l WHERE l.autor.nome ILIKE %:pesquisa%")
    List<Livro> encontrarLivrosPorAutor(String pesquisa);


}
