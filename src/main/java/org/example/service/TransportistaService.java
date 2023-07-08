package org.example.service;

import org.example.model.Transportista;
import org.example.repository.TransportistaRepository;

import java.util.ArrayList;
import java.util.List;

public class TransportistaService implements CRUD<Transportista> {

    private final TransportistaRepository transportistaRepository;

    public TransportistaService() {
        this.transportistaRepository = new TransportistaRepository();
    }

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
            case 1 -> resultado = terrestres();
            case 2 -> resultado = maritimos();
            case 3 -> resultado = aereos();
            default -> {
                return resultado;
            }
        }
        return resultado;
    }

    private List<Transportista> terrestres() {
        List<Transportista> terrestres = new ArrayList<>();
        for (Transportista tr : transportistaRepository.findAll()) {
            if (tr.getTerrestre() && tr.getHabilitado()) {
                terrestres.add(tr);
            }
        }
        return terrestres;
    }

    private List<Transportista> maritimos() {
        List<Transportista> maritimos = new ArrayList<>();
        for (Transportista tr : transportistaRepository.findAll()) {
            if (tr.getTerrestre() && tr.getHabilitado()) {
                maritimos.add(tr);
            }
        }
        return maritimos;
    }

    private List<Transportista> aereos() {
        List<Transportista> aereos = new ArrayList<>();
        for (Transportista tr : transportistaRepository.findAll()) {
            if (tr.getTerrestre() && tr.getHabilitado()) {
                aereos.add(tr);
            }
        }
        return aereos;
    }

}