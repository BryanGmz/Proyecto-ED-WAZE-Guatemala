package proyectoed.gps.objetos;

public class Nodo {

    int grado;
    int cantidadDatos;
    Llave[] llaves;
    Nodo[] punteros;
    
    public Nodo(int grado) {
        this.grado = grado;
        cantidadDatos = 0;
        llaves = new Llave[2 * grado + 1];
        punteros = new Nodo[(2 * grado) + 2];
    }

    public Nodo(int grado, Llave pLlave) {
        this(grado);
        cantidadDatos = 1;
        llaves[0] = pLlave;
    }

    public Llave getLlaves() {
        return llaves[0];
    }
    
    public String getNombreNodoG() {
        return "Nodo" + this.hashCode();
    }
    
    public void setGrado(int grado) {
        this.grado = grado;
    }

    public int getGrado() {
        return grado;
    }
    
    public String graphviz() {
        StringBuilder salidaGraphviz = new StringBuilder();
        salidaGraphviz.append(getNombreNodoG() );
        salidaGraphviz.append("[label=\"<P0>");
        for( int i = 0; i < cantidadDatos; i++ ) {
            salidaGraphviz.append("|").append(llaves[i].getKey().toString());
            salidaGraphviz.append("|<P").append(i).append(1).append(">");                
        }
        salidaGraphviz.append("\"];\n");
        for( int i = 0; i <= cantidadDatos ; i++ ) {
            if( punteros[i] != null )   {
                salidaGraphviz.append(punteros[i].graphviz() );
                salidaGraphviz.append(getNombreNodoG()).append(":P").append(i).append(" -> ").append(punteros[i].getNombreNodoG()).append(";\n");
            }
        }
        return salidaGraphviz.toString();
    }
   
}
