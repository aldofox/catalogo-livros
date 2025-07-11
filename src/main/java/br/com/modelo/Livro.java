package br.com.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Livro {

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<Autor> autores;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private int qtdDonwloads;

    public String getTitulo(){
        return titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public int getQtdDonwloads() {
        return qtdDonwloads;
    }
    @Override
    public String toString(){
        return  "Livro: " + titulo + "\n" +
                "Autor: " + (autores != null && !autores.isEmpty() ? autores.get(0).getNome() : "Desconhecido") + "\n" +
                "idioma: " + (idiomas != null && !idiomas.isEmpty() ? idiomas.get(0) : "Desconhecido") + "\n" +
                "Downloads: " + qtdDonwloads;
    }
}
