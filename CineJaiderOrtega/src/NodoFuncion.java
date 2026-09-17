public class NodoFuncion {
    private Funcion dato;
    private NodoFuncion siguiente;

    public NodoFuncion(Funcion dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Funcion getDato() {
        return dato;
    }

    public void setDato(Funcion dato) {
        this.dato = dato;
    }

    public NodoFuncion getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoFuncion siguiente) {
        this.siguiente = siguiente;
    }
}