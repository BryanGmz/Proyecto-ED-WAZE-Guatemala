/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoed.gps.objetos;

/**
 *
 * @author bryan
 */
public class Rutas {
    private NodoCiudad ciudadOrigen;
    private NodoCiudad ciudadDestino;
    private int id;
    private int tiempovehiculo;
    private int tiempoCaminando;
    private double consumoGas;
    private double desgastePersona;
    private int distancia;
    private boolean dobleVia;
    private String descripcion;
    private double factorDesgasteFisicoDistancia;
    private double factorGaolinaDistancia;
    
    public Rutas(NodoCiudad ciudadOrigen, NodoCiudad ciudadDestino, int id, int tiempovehiculo, int tiempoCaminando, double consumoGas, double desgastePersona) {
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.id = id;
        this.tiempovehiculo = tiempovehiculo;
        this.tiempoCaminando = tiempoCaminando;
        this.consumoGas = consumoGas;
        this.desgastePersona = desgastePersona;
    }

    public double getFactorDesgasteFisicoDistancia() {
        return factorDesgasteFisicoDistancia;
    }

    public double getFactorGaolinaDistancia() {
        return factorGaolinaDistancia;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean isDobleVia() {
        return dobleVia;
    }

    public void setDobleVia(boolean dobleVia) {
        this.dobleVia = dobleVia;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
        this.factorDesgasteFisicoDistancia = (this.tiempoCaminando*this.desgastePersona*distancia);
        this.factorGaolinaDistancia = (this.tiempovehiculo*this.consumoGas*distancia);
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

    public int getTiempoVehiculo() {
        return tiempovehiculo;
    }

    public void setTiempovehiculo(int tiempovehiculo) {
        this.tiempovehiculo = tiempovehiculo;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
