package br.com.ocorteiz.comex.dao;

import br.com.ocorteiz.comex.exception.CategoriaNaoEncontradoException;
import br.com.ocorteiz.comex.model.Categoria;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class CategoriaDAO {

    private final EntityManager em;

    public CategoriaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Categoria dadosCategoria) {
        this.em.persist(dadosCategoria);
        System.out.println("Categoria salvo com sucesso!");
    }

    public List<Categoria> list() {
        String jpql = "SELECT c FROM Categoria c";
        return this.em.createQuery(jpql, Categoria.class).getResultList();
    }

    public Categoria show(Long id){
        String jpql = "SELECT c FROM Categoria c WHERE c.id = :id";
        try {
            return this.em.createQuery(jpql, Categoria.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new CategoriaNaoEncontradoException("Categoria com ID " + id + " nao encontrado!");
        }
    }

    public void update(Categoria dadosCategoria) {
       Categoria categoria = show(dadosCategoria.getId());

        if (categoria != null) {

            if (dadosCategoria.getNome() != null && !dadosCategoria.getNome().isEmpty()) {
                categoria.setNome(dadosCategoria.getNome());
            }

            if (dadosCategoria.getDescricao() != null && !dadosCategoria.getDescricao().isEmpty()) {
                categoria.setDescricao(dadosCategoria.getDescricao());
            }

            System.out.println("Categoria atualizado com sucesso!");
            this.em.merge(categoria);
        }
    }

    public void delete(Long id) {
        Categoria categoria = show(id);
        if (categoria != null) {
            this.em.remove(categoria);
            System.out.println("Categoria deletado com sucesso");
        }
    }

}
