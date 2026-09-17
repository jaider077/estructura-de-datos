import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static ListaFunciones lista = new ListaFunciones();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n========== CINE ==========");
            System.out.println("1. Registrar función");
            System.out.println("2. Comprar entrada");
            System.out.println("3. Iniciar labores");
            System.out.println("0. Salir");
            System.out.println("===========================");
            System.out.print("Seleccione una opción: ");
            
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    registrarFuncion();
                    break;
                case 2:
                    comprarEntrada();
                    break;
                case 3:
                    iniciarLabores();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida, intenta otra.");
            }
        } while (opcion != 0);
    }

    public static void registrarFuncion() {
        System.out.println("\n--- Registrar Función ---");
        String codigo;
        while (true) {
            System.out.print("Código de función: ");
            codigo = sc.nextLine().trim();
            if (codigo.isEmpty()) {
                System.out.println("El código no puede estar vacío.");
                continue;
            }
            if (lista.existeCodigo(codigo)) {
                System.out.println("Ya existe una función con ese código. Ingresa otro.");
                continue;
            }
            break;
        }

        System.out.print("Nombre de la película: ");
        String pelicula = sc.nextLine().trim();

        System.out.print("Hora de inicio (ej: 7:00 PM): ");
        String hora = sc.nextLine().trim();

        Funcion nueva = new Funcion(codigo, pelicula, hora);
        lista.insertar(nueva);
        System.out.println("¡Función registrada con éxito!");
    }

    public static void comprarEntrada() {
        if (lista.estaVacia()) {
            System.out.println("\nNo hay funciones registradas todavía.");
            return;
        }

        System.out.println("\n========== FUNCIONES DISPONIBLES ==========");
        NodoFuncion actual = lista.getPrimero();
        int i = 1;
        while (actual != null) {
            System.out.println(i + ". " + actual.getDato().getPelicula() + " (" + actual.getDato().getHora() + ")");
            actual = actual.getSiguiente();
            i++;
        }
        System.out.println("===========================================");

        System.out.print("Seleccione una función: ");
        int sel = leerEntero();
        Funcion f = lista.obtenerPorPosicion(sel);

        if (f == null) {
            System.out.println("Selección inválida.");
            return;
        }

        f.mostrarInformacion();

        boolean comprado = false;
        while (!comprado) {
            System.out.print("Seleccione el número del puesto que desea comprar (1-20): ");
            int puesto = leerEntero();

            if (!f.puestoValido(puesto)) {
                System.out.println("El puesto debe estar entre 1 y 20.");
                continue;
            }

            if (!f.estaDisponible(puesto)) {
                System.out.println("El puesto " + puesto + " ya está ocupado. Elige otro.");
                continue;
            }

            f.venderPuesto(puesto);
            System.out.println("¡Puesto " + puesto + " comprado con éxito!");
            comprado = true;
        }
    }

    public static void iniciarLabores() {
        if (lista.estaVacia()) {
            System.out.println("\nNo hay funciones registradas para iniciar labores.");
            return;
        }

        NodoFuncion actual = lista.getPrimero();
        while (actual != null) {
            Funcion f = actual.getDato();
            System.out.println("\n====================================");
            System.out.println("       REPRODUCIENDO FUNCIÓN        ");
            System.out.println("====================================");
            System.out.println("Película: " + f.getPelicula());
            System.out.println("Hora: " + f.getHora());
            System.out.println("Puestos vendidos: " + f.contarVendidos());
            System.out.println("Puestos disponibles: " + f.contarDisponibles());
            System.out.println("====================================");

            actual = actual.getSiguiente();

            if (actual != null) {
                System.out.print("Presione una tecla para reproducir la siguiente función...");
                sc.nextLine();
            }
        }

        
        NodoFuncion temp = lista.getPrimero();
        while (temp != null) {
            temp.getDato().reiniciarPuestos();
            temp = temp.getSiguiente();
        }

        System.out.println("\n¡Todas las funciones han sido reproducidas");
    }

    public static int leerEntero() {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Entrada no válida, ingresa un número: ");
        }
        int val = sc.nextInt();
        sc.nextLine(); 
        return val;
    }
}