package com.gerfy.literalura.literalura.repository;

import com.gerfy.literalura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositoryAutor extends JpaRepository <Autor, Long> {

    Boolean existsByNome(String nome);
    Autor findByNome(String nome);

    @Query("SELECT a FROM Autor a WHERE a.anoMorte >= :anoSelecionado AND :anoSelecionado >= a.anoNascimento")
    List<Autor> buscarPorAnoDeFalecimenti(int anoSelecionado);

    @Query("SELECT a FROM Autor a WHERE a.nome ILIKE %:pesquisa%")
    List<Autor> encontrarPorNome(String pesquisa);

}
