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
            switch (opc) {
                case 1 -> resultado = transportistasTerrestres();
                case 2 -> resultado = transportistasMaritimos();
                case 3 -> resultado = transportistasAereos();
            }
        return resultado;
    }

    private List<Transportista> transportistasTerrestres() {
        List<Transportista> transportistas = new ArrayList<>();
        for (Transportista tr : transportistaRepository.findAll()) {
            if (tr.getTerrestre() && tr.getHabilitado()) {
                transportistas.add(tr);
            }
        }
        return transportistas;
    }

    private List<Transportista> transportistasMaritimos() {
        List<Transportista> transportistas = new ArrayList<>();
        for (Transportista tr : transportistaRepository.findAll()) {
            if (tr.getTerrestre() && tr.getHabilitado()) {
                transportistas.add(tr);
            }
        }
        return transportistas;
    }

    private List<Transportista> transportistasAereos() {
        List<Transportista> transportistas = new ArrayList<>();
        for (Transportista tr : transportistaRepository.findAll()) {
            if (tr.getTerrestre() && tr.getHabilitado()) {
                transportistas.add(tr);
            }
        }
        return transportistas;
    }
}
