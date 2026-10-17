# Parte I — DAO con archivo JSON en consola

**Estudiante:** Kevin Flores

Proyecto Maven (`dao-json`) con Gson 2.11.0 que implementa un CRUD sobre archivos
JSON usando el patrón DAO.

## Estructura

```
parte1-consola/
├── pom.xml
├── datos/
│   ├── estudiantes.json
│   └── cursos.json
└── src/main/java/sv/edu/ufg/dao/
    ├── modelo/
    │   ├── Entidad.java
    │   ├── Estudiante.java
    │   └── Curso.java
    ├── dao/
    │   ├── EstudianteDAO.java
    │   ├── CursoDAO.java
    │   ├── JsonDAOBase.java
    │   ├── EstudianteDAOJson.java
    │   └── CursoDAOJson.java
    ├── Main.java
    └── Consola.java
```

## Cómo se ejecuta

Demo de los Pasos 5 y 6 (inserta, lista, actualiza, elimina y muestra el
resultado):

```bash
mvn compile
mvn exec:java
```

Menú interactivo por consola:

```bash
mvn exec:java -Dexec.mainClass=sv.edu.ufg.dao.Consola
```

```
--- Estudiantes ---
1. Insertar
2. Listar
3. Buscar
4. Actualizar
5. Eliminar
0. Salir
```

En la opción 4, dejar un campo vacío conserva el valor anterior.

## Separación de responsabilidades

| Clase | Responsabilidad |
|---|---|
| `modelo/Entidad` | Contrato mínimo `getId()` / `setId()` que necesita la base genérica |
| `modelo/Estudiante` | Entidad: `id`, `nombre`, `carrera`, `correo` |
| `modelo/Curso` | Segunda entidad: `id`, `nombre`, `creditos` |
| `dao/EstudianteDAO` | Contrato sin mencionar JSON ni archivos |
| `dao/CursoDAO` | Contrato independiente de la otra entidad |
| `dao/JsonDAOBase<T>` | Lectura y escritura del archivo, escritas **una sola vez** |
| `dao/*DAOJson` | Heredan el CRUD y solo agregan sus búsquedas propias |
| `Main` / `Consola` | Clientes: declaran la variable como **interfaz** |

Ninguna clase cliente menciona `EstudianteDAOJson` ni `CursoDAOJson`, salvo en la
única línea donde se construye el DAO.

## Actividades de la guía (sección 6, Parte I)

- **Reproducir:** hecho. `Main` genera `datos/estudiantes.json` con el resultado
  del Paso 6.
- **Ampliar la entidad:** atributo `correo` agregado a `Estudiante` y visible en
  el JSON.
- **Nuevo método:** `buscarPorCarrera(String)` en la interfaz y en la
  implementación, usado por `Main`.
- **Menú por consola:** `Consola`, con las seis opciones, usando solo la interfaz
  `EstudianteDAO`.
- **Segunda entidad:** `Curso` con `CursoDAO` y `CursoDAOJson`.
- **Reto:** `JsonDAOBase<T>` elimina el código repetido entre
  `EstudianteDAOJson` y `CursoDAOJson`. `CursoDAOJson` quedó en 14 líneas, de
  las cuales ninguna lee ni escribe un archivo.

## Cambiar de JSON a MySQL sin tocar al cliente

Basta con crear `EstudianteDAOJdbc implements EstudianteDAO` y cambiar una línea
de `Main` o de `Consola`:

```java
EstudianteDAO dao = new EstudianteDAOJson("datos/estudiantes.json");
// Después
EstudianteDAO dao = new EstudianteDAOJdbc();
```

El menú, las validaciones y la impresión no se tocan.
