package sv.edu.ufg.dao.modelo;

/**
 * Segunda entidad de la Parte I. Existe para comprobar que la clase base
 * genérica sirve para cualquier entidad, no solo para {@link Estudiante}.
 */
public class Curso implements Entidad {

    private int id;
    private String nombre;
    private int creditos;

    public Curso() { }

    public Curso(int id, String nombre, int creditos) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
    }

    @Override
    public int getId() { return id; }

    @Override
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCreditos() { return creditos; }

    public void setCreditos(int creditos) { this.creditos = creditos; }

    @Override
    public String toString() {
        return id + " | " + nombre + " | " + creditos + " créditos";
    }
}
