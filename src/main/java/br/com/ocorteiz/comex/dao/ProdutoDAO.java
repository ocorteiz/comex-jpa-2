package br.com.ocorteiz.comex.dao;

import br.com.ocorteiz.comex.exception.ClienteNaoEncontradoException;
import br.com.ocorteiz.comex.exception.ProdutoNaoEncontradoException;
import br.com.ocorteiz.comex.model.Cliente;
import br.com.ocorteiz.comex.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class ProdutoDAO {

    private final EntityManager em;

    public ProdutoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Produto dadosProduto) {
        this.em.persist(dadosProduto);
        System.out.println("Produto salvo com sucesso!");
    }

    public List<Produto> list() {
        String jpql = "SELECT c FROM Produto c";
        return this.em.createQuery(jpql, Produto.class).getResultList();
    }

    public Produto show(Long id){
        String jpql = "SELECT c FROM Produto c WHERE c.id = :id";
        try {
            return this.em.createQuery(jpql, Produto.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new ProdutoNaoEncontradoException("Produto com ID " + id + " nao encontrado!");
        }
    }

    public void update(Produto dadosProduto) {
        Produto produto = show(dadosProduto.getId());

        if (produto != null) {

            if (dadosProduto.getNome() != null && !dadosProduto.getNome().isEmpty()) {
                produto.setNome(dadosProduto.getNome());
            }

            if (dadosProduto.getPreco() != null) {
                produto.setPreco(dadosProduto.getPreco());
            }

            if (dadosProduto.getCategoria() != null) {
                produto.setCategoria(dadosProduto.getCategoria());
            }


            System.out.println("Produto atualizado com sucesso!");
            this.em.merge(produto);
        }
    }

    public void delete(Long id) {
        Produto produto = show(id);
        if (produto != null) {
            this.em.remove(produto);
            System.out.println("Produto deletado com sucesso");
        }
    }



}
