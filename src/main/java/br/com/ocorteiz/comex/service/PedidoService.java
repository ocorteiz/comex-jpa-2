package br.com.ocorteiz.comex.service;

import br.com.ocorteiz.comex.dao.PedidoDAO;
import br.com.ocorteiz.comex.dao.ProdutoDAO;
import br.com.ocorteiz.comex.model.Pedido;
import br.com.ocorteiz.comex.model.Produto;

import javax.persistence.EntityManager;
import java.util.List;

public class PedidoService {

   private final PedidoDAO pedidoDAO;

   public PedidoService(EntityManager em) {
       this.pedidoDAO = new PedidoDAO(em);
   }

   public void save(Pedido dadosPedido){
       pedidoDAO.save(dadosPedido);
   }

   public List<Pedido> list() {
       return pedidoDAO.list();
   }

   public Pedido show(Long id){
       return pedidoDAO.show(id);
   }

   public void delete(Long id){
       pedidoDAO.delete(id);
   }
}
