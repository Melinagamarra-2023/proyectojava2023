package org.example.repository;
import org.example.model.Transportista;
import java.util.ArrayList;
import java.util.List;

public class TransportistaRepository implements CRUD<Transportista> {
    private final List<Transportista> transportistas;

    public TransportistaRepository() {
        this.transportistas = new ArrayList<>();
        this.upload();
    }

    @Override
    public Transportista findOne(String cuit) {
        for (Transportista tr : transportistas) {
            if (tr.getCuit().equals(cuit)) {
                return tr;
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
            findOne(cuit).setHabilitado(false);
    }

    @Override
    public Transportista update(Transportista trActualizado) {
        findOne(trActualizado.getCuit()).setNombre(trActualizado.getNombre());
        findOne(trActualizado.getCuit()).setHabilitado(trActualizado.getHabilitado());
        findOne(trActualizado.getCuit()).setTerrestre(trActualizado.getTerrestre());
        findOne(trActualizado.getCuit()).setMaritimo(trActualizado.getMaritimo());
        findOne(trActualizado.getCuit()).setAereo(trActualizado.getAereo());
        return trActualizado;
    }

    @Override
    public void upload() {
        Transportista tr1 = new Transportista("Alpha", "11111111", "11111", true, true, true, true);
        Transportista tr2 = new Transportista("Beta", "22222222", "22222", true, true, true, false);
        Transportista tr3 = new Transportista("Gamma", "33333333", "33333", true, true, false, true);
        Transportista tr4 = new Transportista("Delta", "33333333", "44444", true, true, false, false);
        Transportista tr5 = new Transportista("Epsilon", "44444444", "55555", true, true, false, true);
        Transportista tr6 = new Transportista("Zeta", "55555555", "66666", true, true, true, false);
        Transportista tr7 = new Transportista("Eta", "66666666", "77777", true, true, true, true);
        Transportista tr8 = new Transportista("Iota", "77777777", "88888", true, true, true, false);
        Transportista tr9 = new Transportista("Kappa", "88888888", "99999", true, true, true, true);
        Transportista tr10 = new Transportista("Lambda", "99999999", "21111", true, true, true, false);
        Transportista tr11 = new Transportista("Omicron", "21111111", "32222", true, true, false, true);
        Transportista tr12 = new Transportista("Sigma", "32222222", "43333", true, true, true, false);
        Transportista tr13 = new Transportista("Ypsilon", "43333333", "54444", true, true, false, false);
        Transportista tr14 = new Transportista("Omega", "54444444", "65555", true, true, true, true);

        transportistas.add(tr1);
        transportistas.add(tr2);
        transportistas.add(tr3);
        transportistas.add(tr4);
        transportistas.add(tr5);
        transportistas.add(tr6);
        transportistas.add(tr7);
        transportistas.add(tr8);
        transportistas.add(tr9);
        transportistas.add(tr10);
        transportistas.add(tr11);
        transportistas.add(tr12);
        transportistas.add(tr13);
        transportistas.add(tr14);
    }
}
