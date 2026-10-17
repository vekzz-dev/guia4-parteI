package sv.edu.ufg.dao.dao;

import sv.edu.ufg.dao.modelo.Curso;

/**
 * DAO de Curso. No repite ni una línea de lectura o escritura de archivo:
 * todo viene de {@link JsonDAOBase}.
 */
public class CursoDAOJson extends JsonDAOBase<Curso> implements CursoDAO {

    public CursoDAOJson(String rutaArchivo) {
        super(rutaArchivo, Curso.class);
    }
}
