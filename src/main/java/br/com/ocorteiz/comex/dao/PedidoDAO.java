package br.com.ocorteiz.comex.dao;

import br.com.ocorteiz.comex.exception.PedidoNaoEncontradoException;
import br.com.ocorteiz.comex.exception.ProdutoNaoEncontradoException;
import br.com.ocorteiz.comex.model.Pedido;
import br.com.ocorteiz.comex.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class PedidoDAO {

    private final EntityManager em;

    public PedidoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Pedido dadosPedido) {
        this.em.persist(dadosPedido);
        System.out.println("Pedido salvo com sucesso!");
    }

    public List<Pedido> list() {
        String jpql = "SELECT c FROM Pedido c";
        return this.em.createQuery(jpql, Pedido.class).getResultList();
    }

    public Pedido show(Long id){
        String jpql = "SELECT c FROM Pedido c WHERE c.id = :id";
        try {
            return this.em.createQuery(jpql, Pedido.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new PedidoNaoEncontradoException("Pedido com ID " + id + " nao encontrado!");
        }
    }

    public void delete(Long id) {
        Pedido pedido = show(id);
        if (pedido != null) {
            this.em.remove(pedido);
            System.out.println("Produto deletado com sucesso");
        }
    }



}
