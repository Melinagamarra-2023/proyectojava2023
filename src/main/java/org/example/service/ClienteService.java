package org.example.service;

import org.example.model.Cliente;
import org.example.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;



public class ClienteService {
    ClienteRepository clienteRepository;
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    public void crearCuentaCliente(Cliente nuevoCliente) {
        Cliente clienteExiste = clienteRepository.findOne(nuevoCliente.getCuit());
        if(clienteExiste == null){
            clienteRepository.save(nuevoCliente);
        }
    }

    public Cliente modificarCliente(Cliente cl) {
        Cliente clienteAnterior = clienteRepository.findOne(cl.getCuit());
        if (clienteAnterior != null) {
            clienteRepository.update(cl);
            return clienteRepository.findOne(cl.getCuit());
        }
        return null;
    }

    public Cliente eliminarCliente(String cuit){
        if(clienteRepository.findOne(cuit) != null){
            clienteRepository.delete(cuit);
        }
        return null;
    }

    public Cliente buscarPorCuit(String cuit) {

        return clienteRepository.findOne(cuit);
    }

    public List<Cliente> buscarTodosLosClientes() {
        List<Cliente> resultado = new ArrayList<>();
        for (Cliente cl : this.clienteRepository.findAll()) {
            if (cl.getHabilitado()) {
                resultado.add(cl);
            }
        }
        return resultado;
    }
}







