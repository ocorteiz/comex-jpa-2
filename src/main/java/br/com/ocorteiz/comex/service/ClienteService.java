package br.com.ocorteiz.comex.service;

import br.com.ocorteiz.comex.dao.ClienteDAO;
import br.com.ocorteiz.comex.model.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteService {

   private final ClienteDAO clienteDAO;

   public ClienteService(EntityManager em) {
       this.clienteDAO = new ClienteDAO(em);
   }

   public void save(Cliente dadosCliente){
       clienteDAO.save(dadosCliente);
   }

   public List<Cliente> list() {
       return clienteDAO.list();
   }

   public Cliente show(Long id){
       return clienteDAO.show(id);
   }

   public void update(Cliente dadosCliente){
       clienteDAO.update(dadosCliente);
   }

   public void delete(Long id){
       clienteDAO.delete(id);
   }
}
