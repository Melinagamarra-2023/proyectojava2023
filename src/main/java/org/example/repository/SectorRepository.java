package org.example.repository;

import org.example.model.Sector;

import java.util.ArrayList;
import java.util.List;

public class SectorRepository {
    SucursalRepository sucursalRepository = new SucursalRepository();
    private final List<Sector> sectores;

    public SectorRepository() {
        this.sectores = new ArrayList<>();
        this.upload();
    }

    public void upload(){
        Sector sector = new Sector("Pendiente",sucursalRepository.findOne("7392AB"),1);
        Sector sector1 = new Sector("En Proceso",sucursalRepository.findOne("7392AB"),2);
        Sector sector2= new Sector("Completo",sucursalRepository.findOne("7392AB"),3);
        Sector sector3 = new Sector("Esperando despacho",sucursalRepository.findOne("7392AB"),4);
        Sector sector4 = new Sector("Despacho",sucursalRepository.findOne("7392AB"),5);
        Sector sector5= new Sector("Transito",sucursalRepository.findOne("7392AB"),6);
        Sector sector6= new Sector("En Sucursal de Destino",sucursalRepository.findOne("7392AB"),7);
        Sector sector7 = new Sector("Entregado",sucursalRepository.findOne("7392AB"),8);
        Sector sector8= new Sector("Destino",sucursalRepository.findOne("7392AB"),9);

        sectores.add(sector);
        sectores.add(sector1);
        sectores.add(sector2);
        sectores.add(sector3);
        sectores.add(sector4);
        sectores.add(sector5);
        sectores.add(sector6);
        sectores.add(sector7);
        sectores.add(sector8);


    }

}
