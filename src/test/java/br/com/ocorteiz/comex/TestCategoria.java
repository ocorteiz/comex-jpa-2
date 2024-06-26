package br.com.ocorteiz.comex;

import br.com.ocorteiz.comex.model.Categoria;
import br.com.ocorteiz.comex.model.Produto;
import br.com.ocorteiz.comex.service.CategoriaService;
import br.com.ocorteiz.comex.service.ProdutoService;
import br.com.ocorteiz.comex.util.JpaUTIL;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class TestCategoria {

    public static void main(String[] args) {
        testeSaveCategoria();
        testeListCategoria();
    }

    public static void testeSaveCategoria(){
        EntityManager em = JpaUTIL.getEntityManager();

        Produto produto1 = new Produto("Iphone 12 Plus", new BigDecimal("2000"));
        Produto produto2 = new Produto("Geladeira Electrolux", new BigDecimal("4000"));

        ProdutoService produtoService = new ProdutoService(em);

        Categoria dadosCategoria1 = new Categoria("Informartica", "62219920364");
        Categoria dadosCategoria2 = new Categoria("Eletrodomestico", "99988811123");

        CategoriaService categoriaService = new CategoriaService(em);

        em.getTransaction().begin();

        categoriaService.save(dadosCategoria1);
        categoriaService.save(dadosCategoria2);

        em.getTransaction().commit();
        em.close();
    }

    public static void testeListCategoria(){
        EntityManager em = JpaUTIL.getEntityManager();
        CategoriaService categoriaService = new CategoriaService(em);
        List<Categoria> categorias = categoriaService.list();
        categorias.forEach(System.out::println);
    }

    public static void testeShowCategoria(){
        EntityManager em = JpaUTIL.getEntityManager();

        CategoriaService categoriaService = new CategoriaService(em);

        Categoria categoria = categoriaService.show(1L);
        System.out.println(categoria.toString());
    }

    public static void testeUpdateCategoria(){
        EntityManager em = JpaUTIL.getEntityManager();

        CategoriaService categoriaService = new CategoriaService(em);

        Categoria dadosCategoria = new Categoria("Jardim", "62219920364");
        dadosCategoria.setId(1L);

        em.getTransaction().begin();

        categoriaService.update(dadosCategoria);

        em.getTransaction().commit();
        em.close();
    }

    public static void testeDeleteCategoria(){
        EntityManager em = JpaUTIL.getEntityManager();

        CategoriaService categoriaService = new CategoriaService(em);

        em.getTransaction().begin();

        categoriaService.delete(2L);

        em.getTransaction().commit();
        em.close();
    }

}
