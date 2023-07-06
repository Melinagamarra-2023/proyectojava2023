package org.example.controller;

import org.example.model.Cliente;
import org.example.repository.ClienteRepository;
import org.example.service.ClienteService;

import java.util.List;


public class ClienteController implements CRUD<Cliente, Cliente> {
    private final ClienteService clienteService;


    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;

    }

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
        return clienteService.modificarCliente(cliente);
    }

    @Override
    public Cliente delete(String id) {
        return clienteService.eliminarCliente(id);
    }
}


