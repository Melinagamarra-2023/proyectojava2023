package org.example.controller;

import org.example.model.Transportista;
import org.example.repository.TransportistaRepository;
import org.example.service.TransportistaService;

import java.util.List;

public class TransportistaController implements CRUD<Transportista, Transportista> {
    private final TransportistaService transportistaService;
    private final TransportistaRepository transportistaRepository;

    public TransportistaController(TransportistaService transportistaService, TransportistaRepository transportistaRepository) {
        this.transportistaService = transportistaService;
        this.transportistaRepository = transportistaRepository;
    }

    @Override
    public void crear(Transportista transportista) {
        transportistaService.crearTransportista(transportista);

    }

    @Override
    public Transportista modificar(Transportista transportista) {
        return transportistaService.modificarTransportista(transportista);
    }

    @Override
    public Transportista eliminar(String cuit) {
        return transportistaService.eliminarTransportista(cuit);
    }

    @Override
    public List<Transportista> buscarTodos() {
        return transportistaService.buscarTransportistas();
    }

    @Override
    public Transportista buscarPorID(String cuit) {
        return transportistaService.buscarPorCuit(cuit);
    }

    public List<Transportista> buscarTransportistasPorTipo(int opc) {
        return transportistaService.buscarTransportistasPorTipo(opc);
    }

    public void setTransporte(Transportista tr, int tipoDeTransporte) {
        transportistaService.setTransporte(tr, tipoDeTransporte);
    }

}