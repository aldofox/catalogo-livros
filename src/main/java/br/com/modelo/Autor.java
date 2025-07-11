package br.com.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {
    @JsonAlias("name")
    private String nome;

    @JsonAlias("birth_year")
    private Integer nascimento;

    @JsonAlias("death_year")
    private Integer falecimento;

    public String getNome() {
        return nome;
    }
    @JsonAlias("birth_year")
    public Integer getNascimento(){
        return nascimento;
    }
    @JsonAlias("death_year")
    public Integer getFalecimento() {
        return falecimento;
    }

    @Override

    public String toString(){
        return nome + " (" + nascimento + " - " + (falecimento != null ? falecimento : "Ainda Vivo") + ")";
    }
}
