package br.com.literaturabookapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.modelo.Livro;
import br.com.modelo.RespostaLivros;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class LivroClient {
    private final HttpClient httpClient;

    public LivroClient(){
        this.httpClient = HttpClient.newHttpClient();
    }
    public String pesuisaLivroPorTitulo(String titulo){

        String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "%20");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        //Tratamento de algum erro Caso o Livro não seja encontrado ou o endereço esteja errado
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            //Verifica a Resposta HTTP
            if (response.statusCode() == 200) {
                return response.body();// Aqui deu Certo e retorna o resultado JSON
            }else{
                System.out.println("Erro na solicitação do endereço: " + response.statusCode());
                return null;
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("ATENÇÃO. Você teve um erro ao buscar o livro: " + e.getMessage());
            return null;
        }
    }

    //Mapeado a busca com ObjectMapper
    public List<Livro> buscarLivrosMapeados(String titulo){
        String json = pesuisaLivroPorTitulo(titulo);

        if (json == null) return List.of();

        ObjectMapper mapper = new ObjectMapper();
        try {
            RespostaLivros resposta = mapper.readValue(json, RespostaLivros.class);
            return  resposta.getResults();
        }catch (Exception e) {
            System.out.println("Erro na Coverção do Arquivo Json: " + e.getMessage());
            return List.of();
        }
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }
}
