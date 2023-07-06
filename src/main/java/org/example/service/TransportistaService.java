package org.example.service;

import org.example.model.Transportista;
import org.example.repository.TransportistaRepository;

import java.util.ArrayList;
import java.util.List;

public class TransportistaService {
    TransportistaRepository transportistaRepository;

    public TransportistaService(TransportistaRepository transportistaRepository) {
        this.transportistaRepository = transportistaRepository;
    }

    public void crearTransportista(Transportista nuevotr) {
        Transportista trExiste = transportistaRepository.findOne(nuevotr.getCuit());
        if (trExiste == null) {
            transportistaRepository.save(nuevotr);
        }
    }

    public Transportista modificarTransportista(Transportista tr) {
        Transportista trAnterior = transportistaRepository.findOne(tr.getCuit());
        if (trAnterior != null) {
            transportistaRepository.update(tr);
            return transportistaRepository.findOne(tr.getCuit());
        }
        return null;
    }

    public Transportista eliminarTransportista(String cuit) {
        if (transportistaRepository.findOne(cuit) != null) {
            transportistaRepository.delete(cuit);
        }
        return null;
    }

    public Transportista buscarPorCuit(String cuit) {
        return transportistaRepository.findOne(cuit);
    }

    public List<Transportista> buscarTransportistas() {
        List<Transportista> resultado = new ArrayList<>();
        for (Transportista tr : this.transportistaRepository.findAll()) {
            if (tr.getHabilitado()) {
                resultado.add(tr);
            }
        }
        return resultado;
    }
/*
    public List<Transportista> buscarTransportistasPorTipo(int opc) {
        Transportista auxiliar = new Transportista("dou","a","a","a","a","a",null,true);
        setTransporte(auxiliar, opc);
        List<Transportista> resultado = new ArrayList<>();
        for (Transportista tr : transportistaRepository.findAll()) {
            if (tr.getTipoDeTransporte().equals(auxiliar.getTipoDeTransporte()) && tr.getHabilitado()) {
                resultado.add(tr);
            }
        }
        return resultado;
    }

    public void setTransporte(Transportista tr, int tipoDeTransporte) {
        transportistaRepository.setTransporte(tr, tipoDeTransporte);
    }


/*
    public Transportista buscarTransportistaPorTipoDeTransporte(List<Transportista> transportistas, String tipoDeTransporte) {
        for (Transportista transportista : transportistas) {
            if (transportista.getTipoDeTransporte().equals(tipoDeTransporte)) {
                return transportista;
            }
        }
        return null;  // Si no se encuentra ningún transportista con el tipo de transporte especificado
    }

 */


}
