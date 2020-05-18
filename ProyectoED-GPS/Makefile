JFLAGS -g
JC = javac
.SUFFIXES: .java.class
.java.class: 
        $(JC) $(JFLAGS) $*.java
CLASSES = \
        /src/proyectoed/gps/objetos/ArbolB.java
        /src/proyectoed/gps/objetos/Grafo.java
        /src/proyectoed/gps/objetos/Llave.java
        /src/proyectoed/gps/objetos/LlaveCadena.java
        /src/proyectoed/gps/objetos/Nodo.java
        /src/proyectoed/gps/objetos/NodoCiudad.java
        /src/proyectoed/gps/objetos/Recorrido.java
        /src/proyectoed/gps/objetos/Rutas.java
        /src/proyectoed/gps/objetos/Separar.java
        /src/proyectoed/gps/gui/Principal.java
        /src/proyectoed/gps/manejadores/ManejadorOrdenarRecorridos.java
        /src/proyectoed/gps/ProyectoEDGPS.java

default: classes

classes: $(CLASSES:.java=.class)

clean: 
        $(RM) src/proyectoed/gps/*.cla
