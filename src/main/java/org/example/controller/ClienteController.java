package org.example.controller;

import org.example.model.Cliente;
import org.example.repository.ClienteRepository;
import org.example.service.ClienteService;

import java.util.List;


public class ClienteController implements CRUD<Cliente, Cliente> {
    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteService clienteService, ClienteRepository clienteRepository) {
        this.clienteService = clienteService;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void crear(Cliente cliente) {
        clienteService.crearCuentaCliente(cliente);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return clienteService.buscarTodosLosClientes();

    }

    @Override
    public Cliente buscarPorID(String id) {
        return clienteService.buscarPorCuit(id);
    }

    @Override
    public Cliente modificar(Cliente cliente) {
        return clienteService.modificarCliente(cliente);
    }

    @Override
    public Cliente eliminar(String id) {
        return clienteService.eliminarCliente(id);
    }
}


