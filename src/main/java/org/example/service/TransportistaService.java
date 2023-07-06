package org.example.service;

import org.example.model.Transportista;
import org.example.repository.TransportistaRepository;
import java.util.ArrayList;
import java.util.List;

public class TransportistaService implements CRUD<Transportista> {

    TransportistaRepository transportistaRepository = new TransportistaRepository();

    @Override
    public void create(Transportista nuevotr) {
        Transportista trExiste = transportistaRepository.findOne(nuevotr.getCuit());
        if (trExiste == null) {
            transportistaRepository.create(nuevotr);
        }
    }

    @Override
    public Transportista findOne(String id) {
        return transportistaRepository.findOne(id);
    }

    @Override
    public List<Transportista> findAll() {
        List<Transportista> resultado = new ArrayList<>();
        for (Transportista tr : this.transportistaRepository.findAll()) {
            if (tr.getHabilitado()) {
                resultado.add(tr);
            }
        }
        return resultado;
    }

    @Override
    public Transportista update(Transportista tr) {
        if (transportistaRepository.findOne(tr.getCuit()) != null) {
            return transportistaRepository.update(tr);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        if (transportistaRepository.findOne(id) != null) {
            transportistaRepository.delete(id);
        }
    }

    public List<Transportista> buscarTransportistasPorTipo(int opc) {
        List<Transportista> resultado = new ArrayList<>();
        for (Transportista tr : transportistaRepository.findAll()) {
            if (opc==1) {
                if (tr.getTerrestre() && tr.getHabilitado()) {
                    resultado.add(tr);
                }
            }else if (opc==2) {
                if (tr.getMaritimo() && tr.getHabilitado()) {
                    resultado.add(tr);
                }
            }else if (opc==3) {
                if (tr.getAereo() && tr.getHabilitado()) {
                    resultado.add(tr);
                }
            }
        }
        return resultado;
    }

}
