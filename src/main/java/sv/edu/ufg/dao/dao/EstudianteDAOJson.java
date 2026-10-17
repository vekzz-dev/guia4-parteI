package sv.edu.ufg.dao.dao;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ufg.dao.modelo.Estudiante;

/**
 * DAO de Estudiante. El CRUD completo se hereda de {@link JsonDAOBase};
 * esta clase solo agrega la búsqueda por carrera.
 */
public class EstudianteDAOJson extends JsonDAOBase<Estudiante> implements EstudianteDAO {

    public EstudianteDAOJson(String rutaArchivo) {
        super(rutaArchivo, Estudiante.class);
    }

    @Override
    public List<Estudiante> buscarPorCarrera(String carrera) {
        List<Estudiante> resultado = new ArrayList<>();
        String buscada = carrera.toLowerCase();
        for (Estudiante e : cargar()) {
            if (e.getCarrera().toLowerCase().contains(buscada)) {
                resultado.add(e);
            }
        }
        return resultado;
    }
}
