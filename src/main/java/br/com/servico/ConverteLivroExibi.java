package br.com.servico;

import br.com.literaturabookapi.LivroClient;
import br.com.entidade.AutorEntity;
import br.com.entidade.LivroEntity;
import br.com.modelo.Livro;
import br.com.modelo.Autor;
import br.com.repositorio.LivroRepository;
import br.com.repositorio.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConverteLivroExibi {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutoRepository autoRepository;


    private final LivroClient client = new LivroClient();
    private final List<Livro> livrosBuscados = new ArrayList<>();

    public void buscarExibiLivroPorTitulo(String titulo) {
        List<Livro> livros = client.buscarLivrosMapeados(titulo);

        if (livros.isEmpty()) {
            System.out.println("O Titulo do Livro pesquisado não foi encontrado.");
            return;
        }

        Livro livro = livros.get(0);
        livrosBuscados.add(livro);

        System.out.println("\nResultado da Busca: \n");
        System.out.println(livro);
        System.out.println("------------------------------");
        salvarLivroNoBanco(livro);
    }

    public void listarTodosLivrosBuscados() {
        if (livrosBuscados.isEmpty()) {
            System.out.println("Nenhum Livro buscado até agora.");
            return;
    }
        System.out.println("\n---- Lista  de Livros Buscado -----");
        for (Livro livro: livrosBuscados){
            System.out.println(livro);
            System.out.println("-------------------------------");
        }
    }
    public void filtrarLivroPorIdioma(String idioma){
        if (livrosBuscados.isEmpty()) {
            System.out.println("Nenhum Livro foi Pesquisado até o Momento.");
            return;
        }
        List<Livro> filtrados = livrosBuscados.stream()
        .filter(livro -> !livro.getIdiomas().isEmpty() && idioma.equalsIgnoreCase(livro.getIdiomas().get(0)))
                .toList();

        if (filtrados.isEmpty()) {
            System.out.println("Não foi encontrado nenhum livro com esse idioma: " + idioma);
            return;
        }
        System.out.println("\n----- Livros encontrados no Idioma '" + idioma + "'---");
        for (Livro livro : filtrados) {
            System.out.println(livro);
            System.out.println("---------------------------------------");
        }
    }
    public void listarAutoresDosLivros() {
        if (livrosBuscados.isEmpty()) {
            System.out.println("Nenhum Livro foi Pesquisado no Momento");
            return;
        }
        System.out.println("\n ------------- Autores dos Livros Pesquisados -------");
            for (Livro livro : livrosBuscados) {
                if (!livro.getAutores().isEmpty()) {
                    var autor = livro.getAutores().get(0);
                    System.out.println("Livro: " + livro.getTitulo());
                    System.out.println("Autor: " + autor.getNome());
                    System.out.println("Nascimento: " + autor.getNascimento());
                    System.out.println("Falecido em: " + autor.getFalecimento());
                    System.out.println("-----------------------------------------");
                }
            }
    }
    public void listarAutoresVivoNoAno(int ano){
        if (livrosBuscados.isEmpty()) {
            System.out.println("Nenhum Livro foi Pesquisado no Momento");
            return;
        }
        System.out.println("\n ----------- Autores vivos no ano " + ano + "----");
        livrosBuscados.stream()
                .flatMap(livro -> livro.getAutores().stream())
                .filter(Objects::nonNull)
                        .filter(autor ->
                                autor.getNascimento() != null &&
                                (autor.getFalecimento() == null || autor.getFalecimento() > ano)
                        )
                        .distinct()
                        .forEach(autor -> {
                            System.out.println("Autor: " + autor.getNome());
                            System.out.println("Nascimento: " + autor.getNascimento());
                            System.out.println("Falecimento: " + autor.getFalecimento());
                            System.out.println("------------------------------------------");
                        });
    }

    public void salvarLivroNoBanco(Livro livroaApi){
        try {
            if (livroaApi == null) {
                System.out.println("Atenção naa foi Salvo no banco de Daods.");
                return;
            }

            Autor autorApi = livroaApi.getAutores().isEmpty() ? null : livroaApi.getAutores().get(0);

            AutorEntity autorEntity = new AutorEntity();
            if (autorApi != null) {
                autorEntity.setNome(autorApi.getNome());
                autorEntity.setAnoNascimento(autorApi.getNascimento());
                autorEntity.setAnoFalecimento(autorApi.getFalecimento());
            }
            AutorEntity autorSalvo = autoRepository.save(autorEntity);

            LivroEntity livroEntity = new LivroEntity();
            livroEntity.setTitulo(livroaApi.getTitulo());
            livroEntity.setIdima(livroaApi.getIdiomas().isEmpty() ? null : livroaApi.getIdiomas().get(0));
            livroEntity.setNumneroDownloads(livroaApi.getQtdDonwloads());
            livroEntity.setAutor(autorSalvo);


            livroRepository.save(livroEntity);
            System.out.println("O Livro e Autor foram salvos com sucesso.");
        }catch (Exception e) {
            System.out.println("Erro ao Salvar o Livro no Banco de Dados. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listarLivrosSalvos(){
        List<LivroEntity> livros = livroRepository.findAll();

        if (livros.isEmpty()) {
            System.out.println("Não Livro salvo no bancos de dados.");
            return;
        }else{
            for (LivroEntity livro : livros) {
                System.out.println("Titulo: " + livro.getTitulo());
                System.out.println("Idioma: " + livro.getIdima());
                System.out.println("Download: " + livro.getNumneroDownloads());
                System.out.println("Autor: " + (livro.getAutor() != null ? livro.getAutor().getNome() : "Desconhecido"));
                System.out.println("--------------------------------------------");
            }
        }

        System.out.println("\n ------------ Livros Salvos no Banco de Dados. ---------");
        for (LivroEntity livro : livros){
            System.out.println("Titulo: " + livro.getTitulo());
            System.out.println("Idioma: " + livro.getIdima());
            System.out.println("Nº de Downloads: " + livro.getNumneroDownloads());

            AutorEntity autor = livro.getAutor();
            if(autor != null) {
                System.out.println("Autor: " + autor.getNome());
                System.out.println("Nascimento: " + autor.getAnoNascimento());
                System.out.println("Falecimento: " + autor.getAnoFalecimento());
            }
            System.out.println("------------------------------------------------");
        }
    }

    public void exibirQtdLivrosPorIdioma(String idioma){
        long total = livroRepository.countByIdimaIgnoreCase(idioma);

        if (total == 0){
            System.out.println("Não existe esse "+ idioma + " cadastrado na nossa base de dados.");
        } else {
            System.out.println("Numero de Livro com o Idima pesquisado: " + idioma + "':" + total);
        }
    }
}