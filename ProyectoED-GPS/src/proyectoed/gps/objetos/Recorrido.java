/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoed.gps.objetos;

import java.util.List;

/**
 *
 * @author bryan
 */
public class Recorrido {
    private List<Rutas> listaRutas;
    private NodoCiudad ciudadOrigen;
    private NodoCiudad ciudadDestino;
    private int tiempoVehiculo;
    private int tiempoCaminando;
    private double consumoGas;
    private double desgastePersona;
    private int distancia;
    private String cadenaRutas; 
    private String cadenaCiudades; 
    private int indice;        
    private double gasolinaDistancia;
    private double desgasteFisicoDistancia;
    
    public Recorrido(List<Rutas> listaRutas) {
        this.listaRutas = listaRutas;
        if (!this.listaRutas.isEmpty()) {
            this.ciudadOrigen = listaRutas.get(0).getCiudadOrigen();
            this.ciudadDestino = listaRutas.get(0).getCiudadDestino();
        }
        calcularAtributos();
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    public String getCadenaRutas() {
        return cadenaRutas;
    }

    public void setCadenaRutas(String cadenaRutas) {
        this.cadenaRutas = cadenaRutas;
    }

    public String getCadenaCiudades() {
        return cadenaCiudades;
    }

    public void setCadenaCiudades(String cadenaCiudades) {
        this.cadenaCiudades = cadenaCiudades;
    }
    
    public List<Rutas> getListaRutas() {
        return listaRutas;
    }

    public void setListaRutas(List<Rutas> listaRutas) {
        this.listaRutas = listaRutas;
    }

    public int getTiempoVehiculo() {
        return tiempoVehiculo;
    }

    public void setTiempoVehiculo(int tiempoVehiculo) {
        this.tiempoVehiculo = tiempoVehiculo;
    }

    public int getTiempoCaminando() {
        return tiempoCaminando;
    }

    public void setTiempoCaminando(int tiempoCaminando) {
        this.tiempoCaminando = tiempoCaminando;
    }

    public double getConsumoGas() {
        return consumoGas;
    }

    public void setConsumoGas(double consumoGas) {
        this.consumoGas = consumoGas;
    }

    public double getDesgastePersona() {
        return desgastePersona;
    }

    public void setDesgastePersona(double desgastePersona) {
        this.desgastePersona = desgastePersona;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
    
    public NodoCiudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(NodoCiudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public NodoCiudad getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(NodoCiudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public double getGasolinaDistancia() {
        return gasolinaDistancia;
    }

    public void setGasolinaDistancia(double gasolinaDistancia) {
        this.gasolinaDistancia = gasolinaDistancia;
    }

    public double getDesgasteFisicoDistancia() {
        return desgasteFisicoDistancia;
    }

    public void setDesgasteFisicoDistancia(double desgasteFisicoDistancia) {
        this.desgasteFisicoDistancia = desgasteFisicoDistancia;
    }
    
    public final void calcularAtributos(){
        tiempoCaminando = 0;
        tiempoVehiculo = 0;
        consumoGas = 0;
        desgastePersona = 0;
        distancia = 0;
        gasolinaDistancia = 0;
        desgasteFisicoDistancia = 0;
        listaRutas.stream().map((ruta) -> {
            tiempoCaminando += ruta.getTiempoCaminando();
            return ruta;
        }).map((ruta) -> {
            tiempoVehiculo += ruta.getTiempoVehiculo();
            return ruta;
        }).map((ruta) -> {
            consumoGas += ruta.getConsumoGas();
            return ruta;
        }).map((ruta) -> {
            desgastePersona += ruta.getDesgastePersona();
            return ruta;
        }).map((ruta) -> {
            desgasteFisicoDistancia += ruta.getFactorDesgasteFisicoDistancia();
            return ruta;
        }).map((ruta) -> {
            gasolinaDistancia += ruta.getFactorGaolinaDistancia();
            return ruta;
        }).forEachOrdered((ruta) -> {
            distancia += ruta.getDistancia();
        });
        System.out.println("RE_" + this.indice + " TC " + tiempoCaminando + " TA " + tiempoVehiculo
        + " G " + consumoGas + " DG " + desgastePersona + " D " + distancia);
    }
    
    @Override
    public String toString(){
        return ("\t" + this.cadenaCiudades + "\n" + "\t" + this.cadenaRutas );
    }
}
