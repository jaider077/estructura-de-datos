
public class Funcion {

    private String codigo;
    private String pelicula;
    private String hora;
    private boolean[] puestos; 

    public Funcion(String codigo, String pelicula, String hora) {
        this.codigo = codigo;
        this.pelicula = pelicula;
        this.hora = hora;
        this.puestos = new boolean[20]; 
    }

    public String getCodigo() {
        return codigo;
    }

    public String getPelicula() {
        return pelicula;
    }

    public String getHora() {
        return hora;
    }

    public boolean puestoValido(int num) {
        return num >= 1 && num <= 20;
    }

    public boolean estaDisponible(int num) {
        return !puestos[num - 1];
    }

    public void venderPuesto(int num) {
        puestos[num - 1] = true;
    }

    public void reiniciarPuestos() {
        for (int i = 0; i < puestos.length; i++) {
            puestos[i] = false;
        }
    }

    public int contarVendidos() {
        int count = 0;
        for (boolean p : puestos) {
            if (p) {
                count++;
            }
        }
        return count;
    }

    public int contarDisponibles() {
        return 20 - contarVendidos();
    }

    public void mostrarPuestos() {
        for (int i = 0; i < 20; i++) {
            int num = i + 1;
            System.out.printf("%2d [%c] ", num, puestos[i] ? 'O' : 'D');
            if (num % 5 == 0) {
                System.out.println();
            }
        }
    }

    public void mostrarInformacion() {
        System.out.println("=============================================");
        System.out.println("Película: " + pelicula);
        System.out.println("Hora: " + hora);
        System.out.println("Puestos:");
        mostrarPuestos();
        System.out.println("D = Disponible");
        System.out.println("O = Ocupado");
        System.out.println("=============================================");
    }
}
