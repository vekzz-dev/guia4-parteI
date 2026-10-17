package sv.edu.ufg.dao.dao;

import java.util.List;

import sv.edu.ufg.dao.modelo.Curso;

/**
 * Contrato de la segunda entidad. Es independiente del de {@link EstudianteDAO}
 * y ambos se implementan sobre la misma clase base genérica.
 */
public interface CursoDAO {

    void insertar(Curso c);

    Curso buscar(int id);

    List<Curso> listar();

    boolean actualizar(Curso c);

    boolean eliminar(int id);
}
