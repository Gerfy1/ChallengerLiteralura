package com.gerfy.literalura.literalura;

import com.gerfy.literalura.literalura.principal.Main;
import com.gerfy.literalura.literalura.repository.RepositoryAutor;
import com.gerfy.literalura.literalura.repository.RepositoryLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	RepositoryAutor repositoryAutor;
	@Autowired
	RepositoryLivro repositoryLivro;

	public LiteraluraApplication(RepositoryAutor repositoryAutor, RepositoryLivro repositoryLivro) {
		this.repositoryAutor = repositoryAutor;
		this.repositoryLivro = repositoryLivro;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repositoryAutor, repositoryLivro);
		main.menuLiteralura();
	}
}
