package sv.edu.ufg.dao.modelo;

/**
 * Entidad del ejemplo: un registro de estudiante.
 *
 * <p>El constructor vacío es necesario porque Gson reconstruye los objetos
 * leyendo el archivo, sin llamar a un constructor con argumentos.</p>
 */
public class Estudiante implements Entidad {

    private int id;
    private String nombre;
    private String carrera;
    private String correo;

    public Estudiante() { }

    public Estudiante(int id, String nombre, String carrera, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.correo = correo;
    }

    @Override
    public int getId() { return id; }

    @Override
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCarrera() { return carrera; }

    public void setCarrera(String carrera) { this.carrera = carrera; }

    public String getCorreo() { return correo; }

    public void setCorreo(String correo) { this.correo = correo; }

    @Override
    public String toString() {
        return id + " | " + nombre + " | " + carrera + " | " + correo;
    }
}
