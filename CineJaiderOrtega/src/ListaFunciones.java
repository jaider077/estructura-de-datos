public class ListaFunciones {

    private NodoFuncion primero;

    public ListaFunciones() {
        this.primero = null;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public NodoFuncion getPrimero() {
        return primero;
    }

    public boolean existeCodigo(String codigo) {
        NodoFuncion actual = primero;
        while (actual != null) {
            if (actual.getDato().getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public void insertar(Funcion f) {
        NodoFuncion nuevo = new NodoFuncion(f);
        if (estaVacia()) {
            primero = nuevo;
        } else {
            NodoFuncion actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    public Funcion obtenerPorPosicion(int pos) {
        NodoFuncion actual = primero;
        int cont = 1;
        while (actual != null) {
            if (cont == pos) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
            cont++;
        }
        return null;
    }
}
