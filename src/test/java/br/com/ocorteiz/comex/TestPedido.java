package br.com.ocorteiz.comex;

import br.com.ocorteiz.comex.model.*;
import br.com.ocorteiz.comex.service.CategoriaService;
import br.com.ocorteiz.comex.service.ClienteService;
import br.com.ocorteiz.comex.service.PedidoService;
import br.com.ocorteiz.comex.service.ProdutoService;
import br.com.ocorteiz.comex.util.JpaUTIL;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class TestPedido {

    public static void main(String[] args) {
        popularBanco();
        testeSavePedido();
        testeListPedido();
    }

    public static void testeSavePedido(){
        EntityManager em = JpaUTIL.getEntityManager();

        ProdutoService produtoService = new ProdutoService(em);
        ClienteService clienteService = new ClienteService(em);
        PedidoService pedidoService = new PedidoService(em);

        Cliente cliente = clienteService.show(1L);
        Produto produto = produtoService.show(1L);

        Pedido pedido = new Pedido(cliente);
        pedido.addItem(new ItemPedido(2, TipoDesconto.PROMOCAO, produto, pedido));

        em.getTransaction().begin();

        pedidoService.save(pedido);

        em.getTransaction().commit();
        em.close();
    }

    public static void testeListPedido(){
        EntityManager em = JpaUTIL.getEntityManager();
        PedidoService pedidoService = new PedidoService(em);
        List<Pedido> pedidos = pedidoService.list();
        pedidos.forEach(System.out::println);
    }

    public static void testeShowPedido(){
        EntityManager em = JpaUTIL.getEntityManager();

        PedidoService pedidoService = new PedidoService(em);

        Pedido pedido = pedidoService.show(1L);
        System.out.println(pedido.toString());
    }


    public static void testeDeletePedido(){
        EntityManager em = JpaUTIL.getEntityManager();

        PedidoService pedidoService = new PedidoService(em);

        em.getTransaction().begin();

        pedidoService.delete(1L);

        em.getTransaction().commit();
        em.close();
    }

    public static void popularBanco() {
        EntityManager em = JpaUTIL.getEntityManager();

        CategoriaService categoriaService = new CategoriaService(em);
        ProdutoService produtoService = new ProdutoService(em);
        ClienteService clienteService = new ClienteService(em);

        Cliente cliente = new Cliente("Luis Felipe", "11122233344", "luis@felipe.com");

        Categoria categoria = new Categoria("Informartica", "62219920364");

        Produto produto = new Produto("Geladeira Electrolux", new BigDecimal("1000"));
        produto.addCategoria(categoria);

        em.getTransaction().begin();

        clienteService.save(cliente);
        categoriaService.save(categoria);
        produtoService.save(produto);

        em.getTransaction().commit();
        em.close();
    }

}
