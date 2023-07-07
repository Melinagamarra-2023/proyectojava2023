package org.example.repository;

import org.example.model.Sector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SectorRepository {
    private final SucursalRepository sucursalRepository;
    private final List<Sector> sectores;

    public SectorRepository() {
        this.sucursalRepository = new SucursalRepository();
        this.sectores = new ArrayList<>();
        this.upload();
    }

    public Sector findOne(String id) {
        for (Sector sector : sectores) {
            if (sector.getSucId().equals(id)) {
                return sector;
            }
        }
        return null;
    }

    public void upload() {

        Sector sector01 = new Sector("Pendiente", sucursalRepository.findOne("7392AB"), "7392AB1");
        Sector sector02 = new Sector("En Proceso", sucursalRepository.findOne("7392AB"), "7392AB2");
        Sector sector03 = new Sector("Completo", sucursalRepository.findOne("7392AB"), "7392AB3");
        Sector sector04 = new Sector("Esperando despacho", sucursalRepository.findOne("7392AB"), "7392AB4");
        Sector sector05 = new Sector("Enviado a despacho", sucursalRepository.findOne("7392AB"), "7392AB5");
        Sector sector06 = new Sector("Transito", sucursalRepository.findOne("7392AB"), "7392AB6");
        Sector sector07 = new Sector("En Sucursal de Destino", sucursalRepository.findOne("7392AB"), "7392AB7");
        Sector sector08 = new Sector("Entregado", sucursalRepository.findOne("7392AB"), "7392AB8");
        Sector sector09 = new Sector("Destino", sucursalRepository.findOne("7392AB"), "7392AB9");

        Sector sector11 = new Sector("Pendiente", sucursalRepository.findOne("1234CD"), "1234CD1");
        Sector sector12 = new Sector("En Proceso", sucursalRepository.findOne("1234CD"), "1234CD2");
        Sector sector13 = new Sector("Completo", sucursalRepository.findOne("1234CD"), "1234CD3");
        Sector sector14 = new Sector("Esperando despacho", sucursalRepository.findOne("1234CD"), "1234CD4");
        Sector sector15 = new Sector("Enviado a despacho", sucursalRepository.findOne("1234CD"), "1234CD5");
        Sector sector16 = new Sector("Transito", sucursalRepository.findOne("1234CD"), "1234CD6");
        Sector sector17 = new Sector("En Sucursal de Destino", sucursalRepository.findOne("1234CD"), "1234CD7");
        Sector sector18 = new Sector("Entregado", sucursalRepository.findOne("1234CD"), "1234CD8");
        Sector sector19 = new Sector("Destino", sucursalRepository.findOne("1234CD"), "1234CD9");

//        Sector sector21 = new Sector("Pendiente", sucursalRepository.findOne("5678EF"), "1");
//        Sector sector22 = new Sector("En Proceso", sucursalRepository.findOne("5678EF"), "2");
//        Sector sector23 = new Sector("Completo", sucursalRepository.findOne("5678EF"), "3");
//        Sector sector24 = new Sector("Esperando despacho", sucursalRepository.findOne("5678EF"), "4");
//        Sector sector25 = new Sector("Enviado a despacho", sucursalRepository.findOne("5678EF"), "5");
//        Sector sector26 = new Sector("Transito", sucursalRepository.findOne("5678EF"), "6");
//        Sector sector27 = new Sector("En Sucursal de Destino", sucursalRepository.findOne("5678EF"), "7");
//        Sector sector28 = new Sector("Entregado", sucursalRepository.findOne("5678EF"), "8");
//        Sector sector29 = new Sector("Destino", sucursalRepository.findOne("5678EF"), "9");
//
//        Sector sector31 = new Sector("Pendiente", sucursalRepository.findOne("9012GH"), "1");
//        Sector sector32 = new Sector("En Proceso", sucursalRepository.findOne("9012GH"), "2");
//        Sector sector33 = new Sector("Completo", sucursalRepository.findOne("9012GH"), "3");
//        Sector sector34 = new Sector("Esperando despacho", sucursalRepository.findOne("9012GH"), "4");
//        Sector sector35 = new Sector("Enviado a despacho", sucursalRepository.findOne("9012GH"), "5");
//        Sector sector36 = new Sector("Transito", sucursalRepository.findOne("9012GH"), "6");
//        Sector sector37 = new Sector("En Sucursal de Destino", sucursalRepository.findOne("9012GH"), "7");
//        Sector sector38 = new Sector("Entregado", sucursalRepository.findOne("9012GH"), "8");
//        Sector sector39 = new Sector("Destino", sucursalRepository.findOne("9012GH"), "9");

        List<Sector> list = Arrays.asList(sector01, sector03, sector02, sector04, sector05, sector06, sector07, sector08, sector09, sector11, sector12, sector13, sector14, sector15, sector16, sector17, sector18, sector19);
    }
}
