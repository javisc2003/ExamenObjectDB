package org.example;

import org.example.controller.ClienteController;
import org.example.model.Cliente;

public class Main {

    public static void main(String[] args) {
        ClienteController clienteController = new ClienteController();

        var cliente1 = new Cliente(1L, "Javier", 10L, true );
        var cliente2 = new Cliente(2L, "Pablo", 5L, true );
        var cliente3 = new Cliente(3L, "Fernando", 8L, false );
        var cliente4 = new Cliente(4L, "Santiago", 2L, false );
        var cliente5 = new Cliente(5L, "Cayetano", 12L, true );

        clienteController.getCliente(4L);
        clienteController.listarMejoresClientes(11L);
        clienteController.estadisticas();

        clienteController.cerrarConexion();
    }
}
