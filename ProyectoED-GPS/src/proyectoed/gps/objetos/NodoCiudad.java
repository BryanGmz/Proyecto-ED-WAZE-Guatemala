/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoed.gps.objetos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bryan
 */
public class NodoCiudad {
    private String ciudad;
    private final List<Rutas> rutas;

    public NodoCiudad(String ciudad) {
        this.ciudad = ciudad;
        this.rutas = new ArrayList<>();
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Rutas> getCiudadesDestino() {
        return rutas;
    }
    
    public void agregarRuta(Rutas ruta){
        this.rutas.add(ruta);
    }
}
