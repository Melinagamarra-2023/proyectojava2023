package org.example.repository;

import org.example.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements CRUD<Cliente> {
    private final List<Cliente> clientes;

    public ClienteRepository() {
        this.clientes = new ArrayList<>();
        this.upload();
    }


    @Override
    public Cliente findOne(String cuit) {
        for (Cliente cl : clientes) {
            if (cl.getCuit().equals(cuit)) {
                return cl;
            }
        }
        return null;
    }

    @Override
    public List<Cliente> findAll() {
        return clientes;
    }

    @Override
    public void create(Cliente cl) {
        clientes.add(cl);
    }

    @Override
    public void delete(String cuit) {
        if (findOne(cuit) != null) {
            findOne(cuit).setHabilitado(false);
        }

    }

    @Override
    public Cliente update(Cliente clienteActualizado) {
        if (findOne(clienteActualizado.getCuit()) != null) {
            findOne(clienteActualizado.getCuit()).setNombre(clienteActualizado.getNombre());
            findOne(clienteActualizado.getCuit()).setApellido(clienteActualizado.getApellido());
            findOne(clienteActualizado.getCuit()).setDireccion(clienteActualizado.getDireccion());
            findOne(clienteActualizado.getCuit()).setCorreo(clienteActualizado.getCorreo());
            findOne(clienteActualizado.getCuit()).setTelefono(clienteActualizado.getTelefono());
            findOne(clienteActualizado.getCuit()).setHabilitado(clienteActualizado.getHabilitado());
        }
        return null;
    }

    @Override
    public void upload() {

        Cliente cliente1 = new Cliente("Juan", "Perez", "123456789", "Calle 123", "juan@example.com", "1234567890", true);
        Cliente cliente2 = new Cliente("Maria", "Lopez", "987654321", "Avenida 456", "maria@example.com", "0987654321", true);
        Cliente cliente3 = new Cliente("Pedro", "Gomez", "456789123", "Plaza 789", "pedro@example.com", "4567891230", true);
        Cliente cliente4 = new Cliente("Laura", "Rodriguez", "321654987", "Calle 456", "laura@example.com", "3216549870", true);
        Cliente cliente5 = new Cliente("Carlos", "Gonzalez", "789123456", "Avenida 789", "carlos@example.com", "7891234560", true);
        Cliente cliente6 = new Cliente("Ana", "Martinez", "654987321", "Plaza 123", "ana@example.com", "6549873210", true);

        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);
        clientes.add(cliente4);
        clientes.add(cliente5);
        clientes.add(cliente6);

    }
}
