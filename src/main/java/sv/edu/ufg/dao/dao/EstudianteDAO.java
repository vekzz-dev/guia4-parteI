package sv.edu.ufg.dao.dao;

import java.util.List;

import sv.edu.ufg.dao.modelo.Estudiante;

/**
 * Contrato de acceso a datos. No menciona JSON, archivos ni SQL:
 * solo declara qué se puede hacer con los datos, no cómo.
 */
public interface EstudianteDAO {

    void insertar(Estudiante e);

    Estudiante buscar(int id);

    List<Estudiante> listar();

    boolean actualizar(Estudiante e);

    boolean eliminar(int id);

    List<Estudiante> buscarPorCarrera(String carrera);
}
