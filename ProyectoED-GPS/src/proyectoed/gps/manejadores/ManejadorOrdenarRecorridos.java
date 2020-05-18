/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoed.gps.manejadores;

import java.util.ArrayList;
import java.util.List;
import proyectoed.gps.objetos.Recorrido;

/**
 *
 * @author bryan
 */
public class ManejadorOrdenarRecorridos {
    private int indiceMejorPorGasolina;
    private int indiceMejorPorDistancia;
    private int indiceMejorPorDistanciaYGasolina;
    private int indiceMejorPorDesgasteFisico;
    private int indiceMejorPorDesgasteFisicoYDitancia;
    private int indicePeorPorGasolina;
    private int indicePeorPorDistancia;
    private int indicePeorPorDistanciaYGasolina;
    private int indicePeorPorDesgasteFisico;
    private int indicePeorPorDesgasteFisicoYDitancia;

    public ManejadorOrdenarRecorridos() {}
    
    public int getIndiceMejorPorGasolina() {
        return indiceMejorPorGasolina;
    }

    public int getIndiceMejorPorDistancia() {
        return indiceMejorPorDistancia;
    }

    public int getIndiceMejorPorDistanciaYGasolina() {
        return indiceMejorPorDistanciaYGasolina;
    }

    public int getIndiceMejorPorDesgasteFisico() {
        return indiceMejorPorDesgasteFisico;
    }

    public int getIndiceMejorPorDesgasteFisicoYDitancia() {
        return indiceMejorPorDesgasteFisicoYDitancia;
    }

    public int getIndicePeorPorGasolina() {
        return indicePeorPorGasolina;
    }

    public int getIndicePeorPorDistancia() {
        return indicePeorPorDistancia;
    }

    public int getIndicePeorPorDistanciaYGasolina() {
        return indicePeorPorDistanciaYGasolina;
    }

    public int getIndicePeorPorDesgasteFisico() {
        return indicePeorPorDesgasteFisico;
    }

    public int getIndicePeorPorDesgasteFisicoYDitancia() {
        return indicePeorPorDesgasteFisicoYDitancia;
    }
    
    public void calcularMejores(List<Recorrido> lista){
        ordenarMejorYPeor(lista, 1);
        ordenarMejorYPeor(lista, 2);
        ordenarMejorYPeor(lista, 3);
        ordenarMejorYPeor(lista, 4);
        ordenarMejorYPeor(lista, 5);
    }
    
    public void ordenarMejorYPeor(List<Recorrido> lista, int caso){
        Recorrido auxiliar;
        List<Recorrido> aux = new ArrayList<>();
        lista.forEach((recorrido) -> { aux.add(recorrido); });
        int cont1;
        int cont2;
        switch (caso) {
            case 1://Gasolina
                for(cont1 = 1; cont1 < lista.size(); cont1++) {
                    auxiliar = lista.get(cont1);
                    for (cont2 = cont1-1; cont2 >= 0 
                            && lista.get(cont2).getConsumoGas() > auxiliar.getConsumoGas(); cont2--){
                        aux.add(cont2 + 1, lista.get(cont2)) ;
                        aux.add(cont2, auxiliar);
                    }
                }
                indiceMejorPorGasolina = lista.indexOf(aux.get(0));
                indicePeorPorGasolina = lista.indexOf(aux.get(lista.size() - 1));
                break;
            case 2://Desgaste Fisico
                for(cont1 = 1; cont1 < lista.size(); cont1++) {
                    auxiliar = lista.get(cont1);
                    for (cont2 = cont1-1; cont2 >= 0 
                            && lista.get(cont2).getDesgastePersona() > auxiliar.getDesgastePersona(); cont2--){
                        aux.add(cont2 + 1, lista.get(cont2)) ;
                        aux.add(cont2, auxiliar);
                    }
                }
                indiceMejorPorDesgasteFisico = lista.indexOf(aux.get(0));
                indicePeorPorDesgasteFisico = lista.indexOf(aux.get(lista.size() - 1));
                break;
            case 3://Distancia
                for(cont1 = 1; cont1 < lista.size(); cont1++) {
                    auxiliar = lista.get(cont1);
                    for (cont2 = cont1-1; cont2 >= 0 
                            && lista.get(cont2).getDistancia() > auxiliar.getDistancia(); cont2--){
                        aux.add(cont2 + 1, lista.get(cont2)) ;
                        aux.add(cont2, auxiliar);
                    }
                }
                indiceMejorPorDistancia = lista.indexOf(aux.get(0));
                indicePeorPorDistancia = lista.indexOf(aux.get(lista.size() - 1));
                break;
            case 4://Gasolina y Distancia
                for(cont1 = 1; cont1 < lista.size(); cont1++) {
                    auxiliar = lista.get(cont1);
                    for (cont2 = cont1-1; cont2 >= 0 
                            && lista.get(cont2).getGasolinaDistancia()> auxiliar.getGasolinaDistancia(); cont2--){
                        aux.add(cont2 + 1, lista.get(cont2)) ;
                        aux.add(cont2, auxiliar);
                    }
                }
                indiceMejorPorDistanciaYGasolina = lista.indexOf(aux.get(0));
                indicePeorPorDistanciaYGasolina = lista.indexOf(aux.get(lista.size() - 1));
                break;
            case 5://Desgaste Fisico y Distancia
                                for(cont1 = 1; cont1 < lista.size(); cont1++) {
                    auxiliar = lista.get(cont1);
                    for (cont2 = cont1-1; cont2 >= 0 
                            && lista.get(cont2).getDesgasteFisicoDistancia()> auxiliar.getDesgasteFisicoDistancia(); cont2--){
                        aux.add(cont2 + 1, lista.get(cont2)) ;
                        aux.add(cont2, auxiliar);
                    }
                }
                indiceMejorPorDesgasteFisicoYDitancia = lista.indexOf(aux.get(0));
                indicePeorPorDesgasteFisicoYDitancia = lista.indexOf(aux.get(lista.size() - 1));
                break;
            default:
                throw new AssertionError();
        }
        
        
    }
    
    public void nuevoAnalisis(){
        indiceMejorPorGasolina = 0;
        indiceMejorPorDistancia = 0;
        indiceMejorPorDistanciaYGasolina = 0;
        indiceMejorPorDesgasteFisico = 0;
        indiceMejorPorDesgasteFisicoYDitancia = 0;
        indicePeorPorGasolina = 0;
        indicePeorPorDistancia = 0;
        indicePeorPorDistanciaYGasolina = 0;
        indicePeorPorDesgasteFisico = 0;
        indicePeorPorDesgasteFisicoYDitancia = 0;
    }
    
}
