package sv.edu.ufg.dao;

import java.util.List;
import java.util.Scanner;

import sv.edu.ufg.dao.dao.EstudianteDAO;
import sv.edu.ufg.dao.dao.EstudianteDAOJson;
import sv.edu.ufg.dao.modelo.Estudiante;

/**
 * Menú por consola. Usa únicamente la interfaz {@link EstudianteDAO}:
 * no menciona JSON, archivos ni la clase concreta.
 *
 * <p>Se ejecuta con {@code mvn exec:java -Dexec.mainClass=sv.edu.ufg.dao.Consola}</p>
 */
public class Consola {

    private final EstudianteDAO dao;
    private final Scanner teclado = new Scanner(System.in);

    public Consola(String rutaArchivo) {
        this.dao = new EstudianteDAOJson(rutaArchivo);
    }

    public static void main(String[] args) {
        new Consola("datos/estudiantes.json").ejecutar();
    }

    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Opción: ");
            switch (opcion) {
                case 1 -> insertar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> actualizar();
                case 5 -> eliminar();
                case 0 -> System.out.println("Fin del programa.");
                default -> System.out.println("Opción inválida. Usa 0 para salir.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("--- Estudiantes ---");
        System.out.println("1. Insertar");
        System.out.println("2. Listar");
        System.out.println("3. Buscar");
        System.out.println("4. Actualizar");
        System.out.println("5. Eliminar");
        System.out.println("0. Salir");
    }

    private void insertar() {
        String nombre = leerTexto("Nombre: ");
        String carrera = leerTexto("Carrera: ");
        String correo = leerTexto("Correo: ");

        if (nombre == null || carrera == null || correo == null) {
            return; // la entrada terminó
        }
        if (nombre.isEmpty()) {
            System.out.println("El nombre es obligatorio.");
            return;
        }
        dao.insertar(new Estudiante(0, nombre, carrera, correo));
        System.out.println("Estudiante insertado.");
    }

    private void listar() {
        List<Estudiante> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        lista.forEach(System.out::println);
    }

    private void buscar() {
        int id = leerEntero("Id a buscar: ");
        Estudiante e = dao.buscar(id);
        if (e == null) {
            System.out.println("No existe el estudiante con id " + id);
            return;
        }
        System.out.println(e);
    }

    private void actualizar() {
        int id = leerEntero("Id a actualizar: ");
        Estudiante e = dao.buscar(id);
        if (e == null) {
            System.out.println("No existe el estudiante con id " + id);
            return;
        }

        String nombre = leerTexto("Nuevo nombre (" + e.getNombre() + "): ");
        String carrera = leerTexto("Nueva carrera (" + e.getCarrera() + "): ");
        String correo = leerTexto("Nuevo correo (" + e.getCorreo() + "): ");

        if (nombre == null || carrera == null || correo == null) {
            return;
        }
        // Enter sin texto deja el valor anterior.
        if (!nombre.isEmpty()) {
            e.setNombre(nombre);
        }
        if (!carrera.isEmpty()) {
            e.setCarrera(carrera);
        }
        if (!correo.isEmpty()) {
            e.setCorreo(correo);
        }

        dao.actualizar(e);
        System.out.println("Estudiante actualizado.");
    }

    private void eliminar() {
        int id = leerEntero("Id a eliminar: ");
        if (dao.eliminar(id)) {
            System.out.println("Estudiante eliminado.");
        } else {
            System.out.println("No existe el estudiante con id " + id);
        }
    }

    // ---------- Lectura de teclado ----------

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return leerLinea();
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        String linea = leerLinea();
        if (linea == null || linea.isEmpty()) {
            return 0; // entrada terminada o Enter: salir
        }
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException ex) {
            System.out.println("Eso no es un número.");
            return -1;
        }
    }

    private String leerLinea() {
        if (!teclado.hasNextLine()) {
            return null;
        }
        return teclado.nextLine().trim();
    }
}
