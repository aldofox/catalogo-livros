package br.com.bancodelivros;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import br.com.servico.ConverteLivroExibi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "br.com")
@EnableJpaRepositories(basePackages = "br.com.repositorio")
@EntityScan(basePackages = "br.com.entidade")

public class Principal implements CommandLineRunner{

    private final Scanner scanner = new Scanner(System.in);
    private final ConverteLivroExibi livroExibi;

    public Principal(ConverteLivroExibi livroExibi) {
        this.livroExibi = livroExibi;
    }

    public static void main(String[] args) {
        SpringApplication.run(Principal.class, args);
    }

    @Override
    public void run(String... args) {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> buscarLivroPorTitulo();
                case 2 -> listarLivrosBuscado();
                case 3 -> filtrarLivroPorIdioma();
                case 4 -> listarAutores();
                case 5 -> listarAutoresVivoNoAno();
                case 6 -> listarLivrosSalvoNobanco();
                case 0 -> System.out.println("Programa está sendo encerrado....");
                default -> System.out.println("OPS. Opção Invalida.");
            }
        } while (opcao != 0);
    }
    private void exibirMenu() {
        System.out.println("\n----- Menu ------");
        System.out.println("1 - Buscar livro por Titulo");
        System.out.println("2 - Listar todos os livros buscados.");
        System.out.println("3 - Listar por Idioma.");
        System.out.println("4 - Listar Autores dos Livros Buscados");
        System.out.println("5 - Autores Vivos.");
        System.out.println("6 - Listar Livros Cadastrados.");
        System.out.println("0 - Sair");
        System.out.println("Escolha uma Opção");
    }
    private void buscarLivroPorTitulo() {
        System.out.println("Digite o Titulo do Livro a ser Buscado: ");
        String titulo = scanner.nextLine();
        livroExibi.buscarExibiLivroPorTitulo(titulo);
    }
    private void listarLivrosBuscado() {
        livroExibi.listarTodosLivrosBuscados();
    }
    private void filtrarLivroPorIdioma() {
        System.out.println("Digite o idioma da sua preferencia: EXE-> en, pt, es");
        String idioma = scanner.nextLine();
        livroExibi.exibirQtdLivrosPorIdioma(idioma);
    }
    private void listarAutores(){
        livroExibi.listarAutoresDosLivros();
    }
    private  void listarAutoresVivoNoAno(){
        System.out.println("Digite o Ano desejado: EX: 1900-> ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        livroExibi.listarAutoresVivoNoAno(ano);
    }
    private void listarLivrosSalvoNobanco(){
        livroExibi.listarLivrosSalvos();
    }

}