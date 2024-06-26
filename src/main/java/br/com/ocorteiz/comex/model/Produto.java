package br.com.ocorteiz.comex.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal preco;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "produtos_categorias",
            joinColumns = @JoinColumn(name = "produtos_id"),
            inverseJoinColumns = @JoinColumn(name = "categorias_id")
    )
    private List<Categoria> categorias = new ArrayList<>();


    public Produto() {}

    public Produto(String nome, BigDecimal preco) {
        this.nome = nome;
        this.preco = preco;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public List<Categoria> getCategoria() {
        return categorias;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categorias = categoria;
    }

    public void addCategoria(Categoria categoria) {
        this.categorias.add(categoria);
    }

    public void removeCategoria(Categoria categoria) {
        this.categorias.remove(categoria);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco + '\'' +
                '}';
    }
}
