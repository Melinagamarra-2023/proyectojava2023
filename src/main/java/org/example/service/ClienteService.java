package org.example.service;

import org.example.model.Cliente;
import org.example.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;

public class ClienteService implements CRUD<Cliente> {
    private final ClienteRepository clienteRepository;

    public ClienteService() {
        this.clienteRepository = new ClienteRepository();
    }

    @Override
    public void create(Cliente nuevoCliente) {
        Cliente clienteExiste = clienteRepository.findOne(nuevoCliente.getCuit());
        if (clienteExiste == null) {
            clienteRepository.save(nuevoCliente);
        }
    }

    @Override
    public Cliente findOne(String cuit) {
        return clienteRepository.findOne(cuit);
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> resultado = new ArrayList<>();
        for (Cliente cl : this.clienteRepository.findAll()) {
            if (cl.getHabilitado()) {
                resultado.add(cl);
            }
        }
        return resultado;
    }

    @Override
    public Cliente update(Cliente cl) {
        Cliente clienteAnterior = clienteRepository.findOne(cl.getCuit());
        if (clienteAnterior != null) {
            clienteRepository.update(cl);
            return clienteRepository.findOne(cl.getCuit());
        }
        return null;
    }

    @Override
    public void delete(String cuit) {
        if (clienteRepository.findOne(cuit) != null) {
            clienteRepository.delete(cuit);
        }
    }
}







