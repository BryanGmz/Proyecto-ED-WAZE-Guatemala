package proyectoed.gps.objetos;

public class ArbolB {

    private Nodo raiz = null;
    private int valorM = 2;
    private int altura = 0;

    public ArbolB() {}

    public ArbolB(int m) {
        this.valorM = m;
    }

    public ArbolB(Nodo nodoRaiz) {
        valorM = nodoRaiz.getGrado();
        this.raiz = nodoRaiz;
        altura = 1;
    }

    public void insert(Llave llave) {
        if (this.altura == 0) {
            this.raiz = new Nodo(this.valorM, llave);
            this.altura = 1;
            return;
        }

        Separar separar = insert(this.raiz, llave, 1);

        if (separar != null) {
            Nodo puntero = this.raiz;
            this.raiz = new Nodo(this.valorM, separar.getLlave());
            this.raiz.punteros[0] = puntero;
            this.raiz.punteros[1] = separar.getPuntero();
            this.altura++;
        } 
    }

    private Separar insert(Nodo node, Llave llave, int nivel) {
        Separar separar = null;
        int i = 0;
        while ((i < node.cantidadDatos) && (llave.mayorQue(node.llaves[i])))
            i++;

        if (nivel < this.altura) {
            separar = insert(node.punteros[i], llave, nivel + 1);
            if (separar == null){
                return null;
            } else {
                llave = separar.mLlave;
            }
        }
        i = node.cantidadDatos - 1;
        
        while ((i >= 0) &&
               (node.llaves[i] == null || llave.menorOIgualQue(node.llaves[i]))) {
            node.llaves[i + 1] = node.llaves[i];
            node.punteros[i + 2] = node.punteros[i + 1];
            i--;
        }
        node.llaves[i + 1] = llave;
        
        if (separar != null)
            node.punteros[i + 2] = separar.mPuntero;
        node.cantidadDatos++;

        if (node.cantidadDatos > 2 * valorM) {
            Nodo nuevoNodo = new Nodo(this.valorM);
            nuevoNodo.punteros[this.valorM] = node.punteros[node.cantidadDatos];
            node.punteros[node.cantidadDatos] = null;
            node.cantidadDatos = this.valorM + 1;
            for (i = 0; i < this.valorM; i++) {
                nuevoNodo.llaves[i] = node.llaves[i + node.cantidadDatos];
                node.llaves[i + node.cantidadDatos] = null;
                nuevoNodo.punteros[i] = node.punteros[i + node.cantidadDatos];
                node.punteros[i + node.cantidadDatos] = null;
            }
            node.cantidadDatos--;
            separar = new Separar(nuevoNodo, node.llaves[node.cantidadDatos]);
            node.llaves[node.cantidadDatos] = null;
            nuevoNodo.cantidadDatos = this.valorM;
            node.cantidadDatos = this.valorM;

            return separar;
        }

        return null;
    }

    public String graphviz() {
        StringBuilder b = new StringBuilder();
        b.append("digraph g { \n node [shape=record];\n");
        b.append(raiz.graphviz());
        b.append("}");
        return b.toString();
    }
    
    public int getAltura() {
        return altura;
    }

    public Nodo getRaiz() {
        return raiz;
    }
    
    public Object eliminacion(Llave eliminar) {
        return reordenar(eliminar, raiz);
    }

    public Object reordenar(Llave llaveEliminar, Nodo apuntador) {
        if ((apuntador == null) || (apuntador.cantidadDatos < 1)) {
            System.err.println("error");
            return null;
        }

        if (llaveEliminar.menorQue(apuntador.llaves[0]))
            return reordenar(llaveEliminar, apuntador.punteros[0]);

        if (llaveEliminar.mayorQue(apuntador.llaves[apuntador.cantidadDatos - 1]))
            return reordenar(llaveEliminar, apuntador.punteros[apuntador.cantidadDatos]);

        int i = 0;
        while ((i < apuntador.cantidadDatos - 1) && (llaveEliminar.mayorQue(apuntador.llaves[i])))
            i++;

        if (llaveEliminar.igualA(apuntador.llaves[i])){
            apuntador.llaves[i] = null;
            return null;
        }

        return reordenar(llaveEliminar, apuntador.punteros[i]);

    }
}