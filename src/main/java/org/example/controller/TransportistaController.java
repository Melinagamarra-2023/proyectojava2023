package org.example.controller;

import org.example.model.Transportista;
import org.example.repository.TransportistaRepository;
import org.example.service.TransportistaService;

import java.util.List;

public class TransportistaController implements CRUD<Transportista, Transportista> {
    private final TransportistaService transportistaService;


    public TransportistaController(TransportistaService transportistaService) {
        this.transportistaService = transportistaService;

    }

    @Override
    public void create(Transportista transportista) {
        transportistaService.crearTransportista(transportista);

    }

    @Override
    public Transportista update(Transportista transportista) {
        return transportistaService.modificarTransportista(transportista);
    }

    @Override
    public Transportista delete(String cuit) {
        return transportistaService.eliminarTransportista(cuit);
    }

    @Override
    public List<Transportista> findAll() {
        return transportistaService.buscarTransportistas();
    }

    @Override
    public Transportista findOne(String cuit) {
        return transportistaService.buscarPorCuit(cuit);
    }
/*
    public List<Transportista> buscarTransportistasPorTipo(int opc) {
        return transportistaService.buscarTransportistasPorTipo(opc);
    }

    public void setTransporte(Transportista tr, int tipoDeTransporte) {
        transportistaService.setTransporte(tr, tipoDeTransporte);
    }

 */

}