package com.gerfy.literalura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("title") String nomeDoLivro,
                         @JsonAlias("languages") List<String> idiomas,
                         @JsonAlias("download_count") Integer numeroDeDownloads) {
}
