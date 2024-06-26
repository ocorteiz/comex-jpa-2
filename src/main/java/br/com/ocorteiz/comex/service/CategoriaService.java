package br.com.ocorteiz.comex.service;

import br.com.ocorteiz.comex.dao.CategoriaDAO;
import br.com.ocorteiz.comex.model.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaService {

   private final CategoriaDAO categoriaDAO;

   public CategoriaService(EntityManager em) {
       this.categoriaDAO = new CategoriaDAO(em);
   }

   public void save(Categoria dadosCategoria){
       categoriaDAO.save(dadosCategoria);
   }

   public List<Categoria> list() {
       return categoriaDAO.list();
   }

   public Categoria show(Long id){
       return categoriaDAO.show(id);
   }

   public void update(Categoria dadosCategoria){
       categoriaDAO.update(dadosCategoria);
   }

   public void delete(Long id){
       categoriaDAO.delete(id);
   }
}
