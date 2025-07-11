package br.com.entidade;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Long id;
    private String titulo;
    private String idima;
    private Integer numneroDownloads;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AutorEntity autor;

    // Getter e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdima() {
        return idima;
    }

    public void setIdima(String idima) {
        this.idima = idima;
    }

    public Integer getNumneroDownloads() {
        return numneroDownloads;
    }

    public void setNumneroDownloads(Integer numneroDownloads) {
        this.numneroDownloads = numneroDownloads;
    }

    public AutorEntity getAutor() {
        return autor;
    }

    public void setAutor(AutorEntity autor) {
        this.autor = autor;
    }

    //Constructor

}
