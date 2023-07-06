package org.example.repository;

import org.example.model.Transporte;
import org.example.model.Transportista;

import java.util.ArrayList;
import java.util.List;

public class TransportistaRepository implements CRUD<Transportista> {
    private final List<Transportista> transportistas;
    public TransportistaRepository( ) {
        this.transportistas = new ArrayList<>();
        this.upload();
    }


    @Override
    public Transportista findOne(String cuit) {
        for (Transportista cl : transportistas) {
            if (cl.getCuit().equals(cuit)) {
                return cl;
            }
        }
        return null;
    }

    @Override
    public List<Transportista> findAll() {
        return transportistas;
    }

    @Override
    public void save(Transportista tr) {
        transportistas.add(tr);
    }

    @Override
    public void delete(String cuit) {
        if (findOne(cuit) != null) {
            findOne(cuit).setHabilitado(false);
        }

    }

    @Override
    public Transportista update(Transportista TransportistaActualizado) {
        if (findOne(TransportistaActualizado.getCuit()) != null) {
            findOne(TransportistaActualizado.getCuit()).setNombre(TransportistaActualizado.getNombre());
            findOne(TransportistaActualizado.getCuit()).setHabilitado(TransportistaActualizado.getHabilitado());
            findOne(TransportistaActualizado.getCuit()).setTipoDeTransporte(TransportistaActualizado.getTipoDeTransporte());
        }
        return null;
    }

    Transporte tipoDeTransporte1 = new Transporte("Marítimo");
    Transporte tipoDeTransporte2 = new Transporte("Aéreo");
    Transporte tipoDeTransporte3 = new Transporte("Terrestre");





   /* public void setTransporte(Transportista tr, int tipoDeTransporte) {
        if(tipoDeTransporte == 1) {
            tr.setTipoDeTransporte(tipoDeTransporte1);

        } else if(tipoDeTransporte == 2) {
            tr.setTipoDeTransporte(tipoDeTransporte2);
        } else {
            tr.setTipoDeTransporte(tipoDeTransporte3);
        }
    }*/

    @Override
    public void upload() {



        Transportista tr1 = new Transportista("Juan", "435435435", tipoDeTransporte1,  true, null);
        Transportista tr2 = new Transportista("Maria", "454354534", tipoDeTransporte1, true, null);
        Transportista tr3 = new Transportista("Pedro", "4567891230", tipoDeTransporte3,true,null);
        Transportista tr4 = new Transportista("Laura", "3216549870",tipoDeTransporte1, true,null);
        Transportista tr5 = new Transportista("Carlos","7891234560",tipoDeTransporte2, true,null);
        Transportista tr6 = new Transportista("Ana", "40456345435", tipoDeTransporte3, true, null);

        transportistas.add(tr1);
        transportistas.add(tr2);
        transportistas.add(tr3);
        transportistas.add(tr4);
        transportistas.add(tr5);
        transportistas.add(tr6);

    }


}
