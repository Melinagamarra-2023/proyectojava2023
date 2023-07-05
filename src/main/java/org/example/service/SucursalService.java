package org.example.service;

import org.example.model.Sucursal;
import org.example.repository.SucursalRepository;

import java.util.ArrayList;
import java.util.List;

public class SucursalService {
    SucursalRepository sucursalRepository = new SucursalRepository();



    public void create(Sucursal sucursal) {
        Sucursal sucursalexistente = sucursalRepository.findOne(sucursal.getSucId());
        if (sucursalexistente == null) {
            sucursalRepository.create(sucursal);
        }

    }

    public List<Sucursal> findAll() {
        List<Sucursal> resultado = new ArrayList<>();
        for (Sucursal sucu : sucursalRepository.findAll()) {
            if (sucu.getHabilitado()) {
                resultado.add(sucu);
            }

        }

        return resultado;
    }

    public Sucursal findOne(String sucId) {
        for (Sucursal suc : sucursalRepository.findAll()) {
            if (suc.getSucId().equals(sucId)) {
                return suc;
            }

        }

        return null;


    }

    public Sucursal update(Sucursal sucursal) {
        Sucursal sucursalvieja = sucursalRepository.findOne(sucursal.getSucId());
        if (sucursalvieja != null) {
            sucursalRepository.update(sucursal);
            return sucursalRepository.findOne(sucursal.getSucId());
        }

        return null;

    }

    public Sucursal delete(String sucId) {
        if (sucursalRepository.findOne(sucId) != null) {
            sucursalRepository.delete(sucId);
        }

        return null;
    }

}
