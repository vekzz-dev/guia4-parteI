package sv.edu.ufg.dao.modelo;

/**
 * Contrato mínimo que necesita la clase base genérica para asignar y leer el
 * identificador sin conocer el tipo concreto de la entidad.
 */
public interface Entidad {

    int getId();

    void setId(int id);
}
