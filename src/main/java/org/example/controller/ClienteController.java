package org.example.controller;

import org.example.model.Cliente;
import org.example.service.ClienteService;
import java.util.List;


public class ClienteController implements CRUD<Cliente> {
    ClienteService clienteService = new ClienteService();

    @Override
    public void create(Cliente cliente) {
        clienteService.create(cliente);
    }

    @Override
    public List<Cliente>findAll() {
        return clienteService.findAll();

    }

    @Override
    public Cliente findOne(String id) {
        return clienteService.findOne(id);
    }

    @Override
    public Cliente update(Cliente cliente) {
        clienteService.update(cliente);
        return null;
    }

    @Override
    public void delete(String id) {
        clienteService.delete(id);
    }
}


