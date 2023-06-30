package org.example.repository;

import org.example.model.Transporte;
import org.example.model.Transportista;

import java.util.ArrayList;
import java.util.List;

public class TransportistaRepository implements CRUD<Transportista> {
    private List<Transportista> transportistas;
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
            findOne(TransportistaActualizado.getCuit()).setApellido(TransportistaActualizado.getApellido());
            findOne(TransportistaActualizado.getCuit()).setDireccion(TransportistaActualizado.getDireccion());
            findOne(TransportistaActualizado.getCuit()).setCorreo(TransportistaActualizado.getCorreo());
            findOne(TransportistaActualizado.getCuit()).setTelefono(TransportistaActualizado.getTelefono());
            findOne(TransportistaActualizado.getCuit()).setHabilitado(TransportistaActualizado.getHabilitado());
            findOne(TransportistaActualizado.getCuit()).setTipoDeTransporte(TransportistaActualizado.getTipoDeTransporte());
        }
        return null;
    }

    Transporte tipoDeTransporte1 = new Transporte("Marítimo");
    Transporte tipoDeTransporte2 = new Transporte("Aéreo");
    Transporte tipoDeTransporte3 = new Transporte("Terrestre");






    public void setTransporte(Transportista tr, int tipoDeTransporte) {
        if(tipoDeTransporte == 1) {
            tr.setTipoDeTransporte(tipoDeTransporte1);

        } else if(tipoDeTransporte == 2) {
            tr.setTipoDeTransporte(tipoDeTransporte2);
        } else {
            tr.setTipoDeTransporte(tipoDeTransporte3);
        }
    }

    @Override
    public void upload() {



        Transportista tr1 = new Transportista("Juan", "Perez", "123456789", "Calle 123", "juan@example.com", "1234567890",tipoDeTransporte1, true);
        Transportista tr2 = new Transportista("Maria", "Lopez", "987654321", "Avenida 456", "maria@example.com", "0987654321",tipoDeTransporte2, true);
        Transportista tr3 = new Transportista("Pedro", "Gomez", "456789123", "Plaza 789", "pedro@example.com", "4567891230", tipoDeTransporte3,true);
        Transportista tr4 = new Transportista("Laura", "Rodriguez", "321654987", "Calle 456", "laura@example.com", "3216549870",tipoDeTransporte1, true);
        Transportista tr5 = new Transportista("Carlos", "Gonzalez", "789123456", "Avenida 789", "carlos@example.com", "7891234560",tipoDeTransporte2, true);
        Transportista tr6 = new Transportista("Ana", "Martinez", "654987321", "Plaza 123", "ana@example.com", "6549873210", tipoDeTransporte3,true);

        transportistas.add(tr1);
        transportistas.add(tr2);
        transportistas.add(tr3);
        transportistas.add(tr4);
        transportistas.add(tr5);
        transportistas.add(tr6);

    }


}
