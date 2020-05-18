/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoed.gps.objetos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bryan
 */
public class Grafo {
    private List<NodoCiudad> listaCiudades;
    private List<Recorrido> listaRecorridos;
    private int contador;
    private String salidaRutas;
    private String salidaCiudades;
    
    
    public Grafo() {
        this.contador = 0;
        this.listaCiudades = new ArrayList<>();
    }

    public String getSalidaRutas() {
        return salidaRutas;
    }

    public String getSalidaCiudades() {
        return salidaCiudades;
    }
    
    public List<NodoCiudad> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<NodoCiudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    
    public void llenarGrafo(String entrada){
        String[] datosEntrada; 
        String[] datosCiudad;
        entrada = entrada.replaceAll("<", "");
        entrada = entrada.replaceAll(">", "");
        datosEntrada = entrada.split("\n");
        for (String dato : datosEntrada) {
            datosCiudad = dato.split("\\|");
            Rutas ruta = crearRuta(datosCiudad, buscarCiudadEnLista(listaCiudades, datosCiudad[0]), buscarCiudadEnLista(listaCiudades, datosCiudad[1]), contador);
            ruta.setDistancia(Integer.parseInt(datosCiudad[6]));
            agregarRutaANodo(ruta, listaCiudades);
            contador++;
        }     
    }
    
    public Rutas crearRuta(String [] datos, NodoCiudad origen, NodoCiudad destino, int contador){
        return new Rutas(origen, destino, contador, Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), Double.parseDouble(datos[4]), Double.parseDouble(datos[5]));
    }
    
    public void agregarRutaANodo(Rutas ruta, List<NodoCiudad> lista){
        lista.stream().filter((ciudad) -> (ciudad.getCiudad().equalsIgnoreCase(ruta.getCiudadOrigen().getCiudad()))).forEachOrdered((ciudad) -> {
            Rutas aux = comprobarSiExisteRuta(ruta.getCiudadDestino().getCiudadesDestino(), ruta);
            if (aux != null) {
                ruta.setId(aux.getId());
                aux.setDobleVia(true);
                ruta.setDobleVia(true);
            } ciudad.agregarRuta(ruta);
        });
    }
    
    public Rutas comprobarSiExisteRuta(List<Rutas> lista, Rutas ruta){
        for (Rutas comprobar : lista) {
            if (comprobar.getCiudadDestino().getCiudad().equalsIgnoreCase(ruta.getCiudadOrigen().getCiudad())) {
                return comprobar;
            }
        } return null;
    }
    
    public NodoCiudad buscarCiudadEnLista(List<NodoCiudad> lista, String ciudad) {
        NodoCiudad nuevaCiudad;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCiudad().equalsIgnoreCase(ciudad)) {
                return lista.get(i);
            }
        } 
        nuevaCiudad = new NodoCiudad(ciudad);
        lista.add(nuevaCiudad);
        return nuevaCiudad;
    }
    
    public void generarGraphvizGrafo() throws IOException {
        String graphviz = "digraph g { \nrankdir=LR;\n";
        for (NodoCiudad ciudad : listaCiudades) {
            System.out.println("Ciudad: " + ciudad.getCiudad());
            for (Rutas ruta : ciudad.getCiudadesDestino()) {
                String auxCadena = ruta.getCiudadOrigen().getCiudad().replaceAll(" ", "_");
                graphviz += auxCadena +"-> R_" + ruta.getId() + ";\n";
                auxCadena = ruta.getCiudadDestino().getCiudad().replaceAll(" ", "_");
                graphviz += "R_" + ruta.getId() +"->" + auxCadena + ";\n";
                String temp = ("\n    Ruta: " + ruta.getId());
                temp += ("\n        Origen: " + ruta.getCiudadOrigen().getCiudad());
                temp += ("\n        Destino: " + ruta.getCiudadDestino().getCiudad());
                temp += ("\n        Distancia: " + ruta.getDistancia());
                temp += ("\n        Tiempo Auto: " + ruta.getTiempoVehiculo());
                temp += ("\n        Tiempo Caminando: " + ruta.getTiempoCaminando());
                temp += ("\n        Doble Vía: " + ruta.isDobleVia());
                temp += ("\n        Consumo Gas: " + ruta.getConsumoGas());
                temp += ("\n        Desgaste Físico: " + ruta.getDesgastePersona());
                ruta.setDescripcion(temp);
                System.out.println(temp);
            }
        }
        graphviz += "}";
        try (FileWriter f = new FileWriter("./archivoGrafo.dot")) {
            f.write(graphviz);
        }
    }
 
 public List<Recorrido> rutasARecorrer(NodoCiudad origen, NodoCiudad destino){
        int contadorRutas = 1;
        String recorridos = "";
        String salidaCiudadesAux = "";
        String salidaRutasAux = "";
        salidaCiudades = "";
        salidaRutas = "";
        listaRecorridos = new ArrayList<>();
        recorridos += origen.getCiudad();
        Recorrido recorridoAux = new Recorrido(new ArrayList<>());
        recorrer(destino, recorridos, origen, recorridoAux.getListaRutas());
        System.out.println("Recorridos: " + recorridos);
        recorridos = "";
        if (!recorridoAux.getListaRutas().isEmpty()) {
            listaRecorridos.add(recorridoAux);
        }
        System.out.println("Posibles Caminos: " + listaRecorridos.size());

        for (Recorrido recorrido : listaRecorridos) {
            System.out.println("Recorido Cant: " + recorrido.getListaRutas().size());
            salidaCiudadesAux += "[" + contadorRutas + "]";
            salidaRutasAux += "[" + contadorRutas + "]";
            recorrido.setIndice(contadorRutas);
            for (Rutas rutas : recorrido.getListaRutas()) {
                salidaCiudadesAux += "-> " + rutas.getCiudadOrigen().getCiudad();
                salidaRutasAux += "-> R_" + rutas.getId();
            }
            salidaCiudadesAux += "-> " + destino.getCiudad();
            recorrido.setCadenaCiudades(salidaCiudadesAux);
            recorrido.setCadenaRutas(salidaRutasAux);
            recorrido.setIndice(contadorRutas);
            salidaCiudades += salidaCiudadesAux + "\n";
            salidaRutas += salidaRutasAux + "\n";
            salidaCiudadesAux = "";
            salidaRutasAux = "";
            contadorRutas++;
        }
    return listaRecorridos;
    }
    
    
    private boolean recorrer(NodoCiudad destino, String recorridos, NodoCiudad nodoSiguiente, List<Rutas> rutasPosibles){
        List<Rutas> auxRutas = new ArrayList<>();
        String auxRecorridos = recorridos;
        rutasPosibles.forEach((rutasPosible) -> {
            auxRutas.add(rutasPosible);
        });
        nodoSiguiente.getCiudadesDestino().forEach((rutas) -> {
            List<Rutas> auxList = new ArrayList<>();
            auxRutas.forEach((auxRuta) -> {
                auxList.add(auxRuta);
            });
            String aux = auxRecorridos;
            if (!recorridos.contains(rutas.getCiudadDestino().getCiudad()) && !nodoSiguiente.getCiudadesDestino().isEmpty()) {
                aux += ", " + rutas.getCiudadDestino().getCiudad();
                auxList.add(rutas);
                if (rutas.getCiudadDestino().getCiudad().equalsIgnoreCase(destino.getCiudad())) {
                    listaRecorridos.add(new Recorrido(auxList));
                } else {
                    recorrer(destino, aux, rutas.getCiudadDestino(), auxList);
                }
            }
            if (rutas.getCiudadDestino().getCiudad().equalsIgnoreCase(destino.getCiudad()) &&
                    !auxList.get(auxList.size() - 1).getCiudadOrigen().equals(rutas.getCiudadOrigen())) {
                auxList.add(rutas);
                listaRecorridos.add(new Recorrido(auxList));
            }
        });
        return false;
    }
    
    public void destructor(){
        listaCiudades.stream().map((lista) -> {//Recorre toda la lista ciudades
            lista.getCiudadesDestino().stream().map((nodoCiudad) -> {
                nodoCiudad.setCiudadDestino(null);
                return nodoCiudad;
            }).map((nodoCiudad) -> {
                nodoCiudad.setCiudadOrigen(null);
                return nodoCiudad;
            }).forEachOrdered((nodoCiudad) -> {
                lista.getCiudadesDestino().remove(nodoCiudad);
            });
            return lista;
        }).forEachOrdered((lista) -> {
            listaCiudades.remove(lista);
        });
    }
}
