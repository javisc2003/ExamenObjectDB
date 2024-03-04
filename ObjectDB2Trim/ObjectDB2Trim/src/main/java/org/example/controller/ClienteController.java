package org.example.controller;

import org.example.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ClienteController {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public ClienteController() {
        emf = Persistence.createEntityManagerFactory("objectdb:/path/to/your/database.odb");
        em = emf.createEntityManager();
    }

    public ClienteController(EntityManagerFactory emf, EntityManager em) {
        this.emf = emf;
        this.em = em;
    }

    public void cerrarConexion() {
        em.close();
        emf.close();
    }

    public void insertarCliente(Cliente cliente) {
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    public void getCliente(Long id) {
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) {
            System.out.println("Información del Cliente:");
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Total de Ventas: " + cliente.getTotalVentas());
            System.out.println("Estado: " + (cliente.isActivo() ? "Activo" : "Inactivo"));
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void listarMejoresClientes(Long cantidad) {
        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.activo = true AND c.totalVentas > :cantidad");
        query.setParameter("cantidad", cantidad);

        List<Cliente> clientes = query.getResultList();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes activos con total de ventas mayor a " + cantidad);
        } else {
            System.out.println("Clientes activos con total de ventas mayor a " + cantidad + ":");
            for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getId() + ", Nombre: " + cliente.getNombre());
            }
        }
    }

    public void estadisticas() {
        Query queryTotalVentas = em.createQuery("SELECT SUM(c.totalVentas) FROM Cliente c");
        Long totalVentas = (Long) queryTotalVentas.getSingleResult();

        Query queryPromedioVentas = em.createQuery("SELECT AVG(c.totalVentas) FROM Cliente c WHERE c.activo = true");
        Double promedioVentas = (Double) queryPromedioVentas.getSingleResult();

        Query queryClientesInactivos = em.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.activo = false AND c.totalVentas > 0");
        Long clientesInactivosConVentas = (Long) queryClientesInactivos.getSingleResult();

        System.out.println("Resumen Estadístico:");
        System.out.println("Total de Ventas entre todos los clientes: " + totalVentas);
        System.out.println("Promedio de Ventas de los clientes activos: " + promedioVentas);
        System.out.println("Cantidad de clientes inactivos con total de ventas mayor a 0: " + clientesInactivosConVentas);
    }
}

