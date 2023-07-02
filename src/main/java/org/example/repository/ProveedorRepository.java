package org.example.repository;

import org.example.model.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class ProveedorRepository implements CRUD<Proveedor>{
    private final List<Proveedor> proveedores;

    public ProveedorRepository() {
        this.proveedores = new ArrayList<>();
    }

    @Override
    public Proveedor findOne(String cuit) {
        for (Proveedor pr : proveedores) {
            if (pr.getCuit().equals(cuit)) {
                return pr;
            }
        }
        return null;
    }

    @Override
    public List<Proveedor> findAll() {
        return proveedores;
    }

    @Override
    public void save(Proveedor pr) {
        proveedores.add(pr);
    }

    @Override
    public void delete(String cuit) {
        for (Proveedor pr : proveedores) {
            if (pr.getCuit().equals(cuit) && pr.getHabilitado()) {
                pr.setHabilitado(false);
            }
        }
    }

    @Override
    public Proveedor update(Proveedor proveedorActualizado) {
        if(findOne(proveedorActualizado.getCuit())!= null) {
            findOne(proveedorActualizado.getCuit()).setNombre(proveedorActualizado.getNombre());
            findOne(proveedorActualizado.getCuit()).setDireccion(proveedorActualizado.getDireccion());
            findOne(proveedorActualizado.getCuit()).setTelefono(proveedorActualizado.getTelefono());
            findOne(proveedorActualizado.getCuit()).setCorreo(proveedorActualizado.getCorreo());
            findOne(proveedorActualizado.getCuit()).setHabilitado(proveedorActualizado.getHabilitado());

        }
        return null;
    }

    @Override
    public void upload() {
        Proveedor proveedor1 = new Proveedor("COMPUTADORAS Y TELECOMUNICACIONES S.A","","0001","telecom@gmail.com.ar","0800-974-5667",true);
        Proveedor proveedor2 = new Proveedor("L'Oréal","Av. del Libertador 7208, C1429 CABA","0002","loreal@gmail.com.ar","0800-999-7763",true);
        Proveedor proveedor3 = new Proveedor("KAYLEE COSMETIC"," 204 100 Casa 7255 B. Bicentenario, N3300 Posadas, Misiones","0003","kayleeCosmetics@gmail.com","0376 488-0389",true);
        Proveedor proveedor4 = new Proveedor("AVON","C.143 Posadas Misiones","0004","AVON@gmail.com","0810 555 2866",true);
        Proveedor proveedor5 = new Proveedor("Globant","3 de Febrero 1830 piso 5, N3300 Posadas, Misiones","0005","Globant@gmail.com","0376 474-2128",true);
        Proveedor proveedor6 = new Proveedor("PedidosYA","Virrey Liniers 71, C1174 CABA","0006","PEDIDOSYA@gmail.com.ar","0376 475-2238",true);
        Proveedor proveedor7 = new Proveedor("Samsung Electronics"," San Lorenzo 1888, N3300 Posadas, Misiones","21112312348","Samsung@gmail.com.ar","0376 571-2238",true);
        Proveedor proveedor8 = new Proveedor("HELICONIA SOFTWARE"," Humberto 1º 945, X5000 FCA, Córdoba","0007","Heliconia@gmail.com","0376 443-7686",true);
        Proveedor proveedor9 = new Proveedor("Inthegra","Vidal 4316, C1429 AID, Buenos Aires","0008","integrate@gmail.com","0376 461-3250",true);
        Proveedor proveedor10 = new Proveedor("Gaalsys-SOLUCIONES TECNOLOGICAS","C. 139 7841, N3300 Posadas, Misiones","0009","Gaalsys@gmail.com","0376 460-1370",true);

        proveedores.add(proveedor1);
        proveedores.add(proveedor2);
        proveedores.add(proveedor3);
        proveedores.add(proveedor4);
        proveedores.add(proveedor5);
        proveedores.add(proveedor6);
        proveedores.add(proveedor7);
        proveedores.add(proveedor8);
        proveedores.add(proveedor9);
        proveedores.add(proveedor10);

    }


}
