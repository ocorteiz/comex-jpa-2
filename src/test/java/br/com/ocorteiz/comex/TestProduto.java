package br.com.ocorteiz.comex;

import br.com.ocorteiz.comex.model.Categoria;
import br.com.ocorteiz.comex.model.Produto;
import br.com.ocorteiz.comex.service.CategoriaService;
import br.com.ocorteiz.comex.service.ProdutoService;
import br.com.ocorteiz.comex.util.JpaUTIL;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class TestProduto {

    public static void main(String[] args) {
        testeSaveProduto();
        testeListProduto();
    }

    public static void testeSaveProduto(){
        EntityManager em = JpaUTIL.getEntityManager();

        Categoria dadosCategoria1 = new Categoria("Informartica", "62219920364");
        Categoria dadosCategoria2 = new Categoria("Eletrodomestico", "99988811123");

        CategoriaService categoriaService = new CategoriaService(em);

        Produto produto1 = new Produto("Iphone 12 Plus", new BigDecimal("2000"));
        Produto produto2 = new Produto("Geladeira Electrolux", new BigDecimal("4000"));
        produto1.addCategoria(dadosCategoria1);
        produto1.addCategoria(dadosCategoria2);
        produto2.addCategoria(dadosCategoria2);

        ProdutoService produtoService = new ProdutoService(em);

        em.getTransaction().begin();

        categoriaService.save(dadosCategoria1);
        categoriaService.save(dadosCategoria2);

        produtoService.save(produto1);
        produtoService.save(produto2);

        em.getTransaction().commit();
        em.close();
    }

    public static void testeListProduto(){
        EntityManager em = JpaUTIL.getEntityManager();
        ProdutoService produtoService = new ProdutoService(em);
        List<Produto> produtos = produtoService.list();
        produtos.forEach(p -> System.out.println(p.getCategoria()));
    }

    public static void testeShowProduto(){
        EntityManager em = JpaUTIL.getEntityManager();

        ProdutoService produtoService = new ProdutoService(em);

        Produto produto = produtoService.show(1L);
        System.out.println(produto.toString());
    }

    public static void testeUpdateProduto(){
        EntityManager em = JpaUTIL.getEntityManager();

        ProdutoService produtoService = new ProdutoService(em);

        Produto dadosProduto = new Produto("Samsung S20", null);
        dadosProduto.setId(1L);

        em.getTransaction().begin();

        produtoService.update(dadosProduto);

        em.getTransaction().commit();
        em.close();
    }

    public static void testeDeleteProduto(){
        EntityManager em = JpaUTIL.getEntityManager();

        ProdutoService produtoService = new ProdutoService(em);

        em.getTransaction().begin();

        produtoService.delete(1L);

        em.getTransaction().commit();
        em.close();
    }

}
