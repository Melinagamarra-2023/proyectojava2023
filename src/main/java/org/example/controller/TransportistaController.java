package org.example.controller;

import org.example.model.Transportista;
import org.example.service.TransportistaService;

import java.util.List;

public class TransportistaController implements CRUD<Transportista, Transportista> {
    TransportistaService transportistaService = new TransportistaService();

    @Override
    public void create(Transportista transportista) {
        transportistaService.create(transportista);

    }

    @Override
    public Transportista update(Transportista transportista) {
        return transportistaService.update(transportista);
    }

    @Override
    public Transportista delete(String cuit) {
        return transportistaService.delete(cuit);
    }

    @Override
    public List<Transportista> findAll() {
        return transportistaService.findAll();
    }

    @Override
    public Transportista findOne(String cuit) {
        return transportistaService.findOne(cuit);
    }

    public List<Transportista> buscarTransportistasPorTipo(int opc) {
        return transportistaService.buscarTransportistasPorTipo(opc);
    }


}
