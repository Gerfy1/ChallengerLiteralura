package com.gerfy.literalura.literalura.principal;

import com.gerfy.literalura.literalura.model.Autor;
import com.gerfy.literalura.literalura.model.DadosAutor;
import com.gerfy.literalura.literalura.model.DadosLivro;
import com.gerfy.literalura.literalura.model.Livro;
import com.gerfy.literalura.literalura.repository.RepositoryAutor;
import com.gerfy.literalura.literalura.repository.RepositoryLivro;
import com.gerfy.literalura.literalura.service.ConsumoAPI;
import com.gerfy.literalura.literalura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();
    private final String EnderecoAPI = "https://gutendex.com/books/?search=";
    private List<DadosLivro> dadosLivros = new ArrayList<>();

    private RepositoryAutor repositoryAutor;
    private RepositoryLivro repositoryLivro;
    private List<Livro> livros = new ArrayList<>();

    public Main(RepositoryAutor repositoryAutor, RepositoryLivro repositoryLivro) {
        this.repositoryAutor = repositoryAutor;
        this.repositoryLivro = repositoryLivro;
    }

    public void menuLiteralura() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    Escolha dentre as seguintes opções:
                                        
                    1 - Buscar livro pelo o nome
                    2 - Listar livros registrado na database
                    3 - Listar autores registrados
                    4 - Listando autores vivos em determinado ano
                    5 - Listar livros em determinado idioma
                                        
                    0 - Sair
                                        
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    buscarLivrosRegistrados();
                    break;
                case 3:
                    buscarAutoresRegistrados();
                    break;
                case 4:
                    buscarAutoresVivosPorAno();
                    break;
                case 5:
                    buscarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo da aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarLivrosPorIdioma() {
        var idiomas = repositoryLivro.buscaridiomas();
        System.out.println("Idiomas cadastrados no banco de dados: ");
        idiomas.forEach(System.out::println);
        System.out.println("Selecione um dos idiomas: ");
        var idiomaSelecionado = leitura.nextLine();
        repositoryLivro.buscarPorIdioma(idiomaSelecionado).forEach(System.out::println);
    }

    private void buscarAutoresVivosPorAno() {
        System.out.println("Qual ano você deseja?");
        var anoSelecionado = leitura.nextInt();
        leitura.nextLine();
        var buscar = repositoryAutor.buscarPorAnoDeFalecimenti(anoSelecionado);
        if (buscar.isEmpty()) {
            System.out.println("Autores vivos no ano de: " + anoSelecionado);
            buscar.forEach(System.out::println);
        } else {
            System.out.println("Nenhum autor encontrado!");
        }
    }

    private void buscarAutoresRegistrados() {
        var buscar = repositoryAutor.findAll();
        if (!buscar.isEmpty()) {
            System.out.println("Autores cadastrados em nosso banco de dados: ");
            buscar.forEach(System.out::println);
        } else {
            System.out.println("Nenhum autor foi encontrado em nosso banco de dados!");
        }
    }

    private void buscarLivrosRegistrados() {
        var buscar = repositoryLivro.findAll();
        if (!buscar.isEmpty()) {
            System.out.println("Livros registrados no banco de dados: ");
            buscar.forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro encontrado no banco de dados!");
        }
    }

    private void buscarLivro() {
        System.out.println("Qual livro você deseja?: ");
        var busca = leitura.nextLine();
        var dados = consumo.obterDados(EnderecoAPI+busca.replace(" ", "%20"));
        salvarDados(dados);
    }

    private void salvarDados(String dados) {
        try {
            Livro livro = new Livro(converteDados.obterDados(dados, DadosLivro.class));
            Autor autor = new Autor(converteDados.obterDados(dados, DadosAutor.class));
            Autor autorData = null;
            Livro livroData = null;
            if (!repositoryAutor.existsByNome(autor.getNome())) {
                repositoryAutor.save(autor);
                autorData = autor;
            } else {
                autorData = repositoryAutor.findByNome(autor.getNome());
            }
            if (!repositoryLivro.existsByNome(livro.getNome())) {
                livro.setAutor(autorData);
                repositoryLivro.save(livro);
                livroData = livro;
            } else {
                livroData = repositoryLivro.findByNome(livro.getNome());
            }
            System.out.println(livroData);
        } catch (NullPointerException e) {
            System.out.println("Livro não localizado, tente novamente!");
        }
    }
}
