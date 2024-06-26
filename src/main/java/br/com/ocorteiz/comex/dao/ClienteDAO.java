package br.com.ocorteiz.comex.dao;

import br.com.ocorteiz.comex.exception.ClienteNaoEncontradoException;
import br.com.ocorteiz.comex.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class ClienteDAO {

    private final EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Cliente dadosCliente) {
        this.em.persist(dadosCliente);
        System.out.println("Cliente salvo com sucesso!");
    }

    public List<Cliente> list() {
        String jpql = "SELECT c FROM Cliente c";
        return this.em.createQuery(jpql, Cliente.class).getResultList();
    }

    public Cliente show(Long id){
        String jpql = "SELECT c FROM Cliente c WHERE c.id = :id";
        try {
            return this.em.createQuery(jpql, Cliente.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new ClienteNaoEncontradoException("Cliente com ID " + id + " nao encontrado!");
        }
    }

    public void update(Cliente dadosCliente) {
       Cliente cliente = show(dadosCliente.getId());

        if (cliente != null) {

            if (dadosCliente.getNome() != null && !dadosCliente.getNome().isEmpty()) {
                cliente.setNome(dadosCliente.getNome());
            }

            if (dadosCliente.getEmail() != null && !dadosCliente.getEmail().isEmpty()) {
                cliente.setEmail(dadosCliente.getEmail());
            }

            if (dadosCliente.getCpf() != null && !dadosCliente.getCpf().isEmpty()) {
                cliente.setCpf(dadosCliente.getCpf());
            }

            System.out.println("Cliente atualizado com sucesso!");
            this.em.merge(cliente);
        }
    }

    public void delete(Long id) {
        Cliente cliente = show(id);
        if (cliente != null) {
            this.em.remove(cliente);
            System.out.println("Cliente deletado com sucesso");
        }
    }

}
