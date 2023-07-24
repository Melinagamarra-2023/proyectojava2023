package org.example.controller;

import org.example.model.Cliente;
import org.example.service.ClienteService;

import java.util.List;


public class ClienteController implements CRUD<Cliente> {
    private final ClienteService clienteService;

    public ClienteController() {
        this.clienteService = new ClienteService();
    }

    @Override
    public void create(Cliente cliente) {
        clienteService.create(cliente);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteService.findAll();
    }

    @Override
    public Cliente findOne(String id) {
        return clienteService.findOne(id);
    }

    @Override
    public Cliente update(Cliente cliente) {
        return clienteService.update(cliente);
    }

    @Override
    public void delete(String id) {
        clienteService.delete(id);
    }
}


