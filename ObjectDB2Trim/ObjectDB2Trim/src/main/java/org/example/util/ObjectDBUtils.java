package org.example.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ObjectDBUtils {
    private static final String DB_PATH = "clientes.odb";
    public static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory(DB_PATH);
    }

}
