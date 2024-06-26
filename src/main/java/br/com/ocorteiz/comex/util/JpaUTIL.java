package br.com.ocorteiz.comex.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUTIL {

    public static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("comex");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

}
