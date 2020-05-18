package proyectoed.gps.objetos;

public class LlaveCadena extends Llave {
    
    public LlaveCadena(String pValor) {
        mLlave = pValor;
    }    
    private String mLlave = null;    

    @Override
    public Object getKey() {
        return mLlave;
    }
    
    @Override
    public boolean igualA(Llave pObjeto) {
        return mLlave.equals(pObjeto.getKey());
    }

    @Override
    public boolean menorQue(Llave pObjeto) {
        return mLlave.compareTo((String)pObjeto.getKey()) < 0;
    }
    
    @Override
    public boolean mayorQue(Llave pObjeto) {
        return mLlave.compareTo((String)pObjeto.getKey()) > 0;
    }
    
    @Override
    public boolean menorOIgualQue(Llave pObjeto) {
        return mLlave.compareTo((String)pObjeto.getKey()) <= 0;
    }
    
    @Override
    public boolean mayorOIgualQue(Llave pObjeto) {
        return mLlave.compareTo((String)pObjeto.getKey()) >= 0;
    }
    
    @Override
    public Llave minKey() {
        return new LlaveCadena("");
    }

}
