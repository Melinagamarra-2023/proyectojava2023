package org.example.controller;

import org.example.model.Cliente;
import org.example.service.ClienteService;
import java.util.List;


public class ClienteController implements CRUD<Cliente> {
    ClienteService clienteService = new ClienteService();

    @Override
    public void create(Cliente cliente) {
        clienteService.crearCuentaCliente(cliente);
    }

    @Override
    public List<Cliente>findAll() {
        return clienteService.buscarTodosLosClientes();

    }

    @Override
    public Cliente findOne(String id) {
        return clienteService.buscarPorCuit(id);
    }

    @Override
    public Cliente update(Cliente cliente) {
        clienteService.modificarCliente(cliente);
        return null;
    }

    @Override
    public void delete(String id) {
        clienteService.eliminarCliente(id);
    }
}


