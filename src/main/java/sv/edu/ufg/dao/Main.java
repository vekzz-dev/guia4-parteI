package sv.edu.ufg.dao;

import sv.edu.ufg.dao.dao.CursoDAO;
import sv.edu.ufg.dao.dao.CursoDAOJson;
import sv.edu.ufg.dao.dao.EstudianteDAO;
import sv.edu.ufg.dao.dao.EstudianteDAOJson;
import sv.edu.ufg.dao.modelo.Curso;
import sv.edu.ufg.dao.modelo.Estudiante;

/**
 * Cliente del patrón DAO. Observa que las variables son del tipo interfaz,
 * no de las clases concretas: el programa no sabe que los datos viven en JSON.
 */
public class Main {

    public static void main(String[] args) {
        EstudianteDAO dao = new EstudianteDAOJson("datos/estudiantes.json");

        dao.insertar(new Estudiante(0, "Ana Martínez", "Ingeniería en Sistemas",
                "ana.martinez@ufg.edu.sv"));
        dao.insertar(new Estudiante(0, "Luis Pérez", "Ingeniería en Sistemas",
                "luis.perez@ufg.edu.sv"));

        System.out.println("--- Listado ---");
        dao.listar().forEach(System.out::println);

        Estudiante e = dao.buscar(2);
        if (e != null) {
            e.setCarrera("Ingeniería Industrial");
            dao.actualizar(e);
        }

        dao.eliminar(1);

        System.out.println("--- Después de actualizar y eliminar ---");
        dao.listar().forEach(System.out::println);

        System.out.println("--- Estudiantes de Ingeniería Industrial ---");
        dao.buscarPorCarrera("industrial").forEach(System.out::println);

        // Segunda entidad: mismo patrón y la misma clase base genérica.
        CursoDAO cursos = new CursoDAOJson("datos/cursos.json");
        cursos.insertar(new Curso(0, "Programación II", 4));
        cursos.insertar(new Curso(0, "Base de Datos", 3));

        System.out.println("--- Cursos ---");
        cursos.listar().forEach(System.out::println);
    }
}
