package libreria.servicios;

import libreria.persistencias.LibroDAO;
import libreria.entidades.Libro;
import java.util.List;
import java.util.Objects;

public class LibroServicios {

    private LibroDAO dao;

    public LibroServicios() {
        dao = new LibroDAO();
    }

    public void guardar(Libro libro) {
        try {
            validarCampos(libro);
            dao.guardar(libro);
            System.out.println("Libro guardado");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void validarCampos(Libro libro) throws Exception {
        if (TraerLibroPorISBN(libro.getIsbn()).toString() != null) {
            throw new Exception("Error el ISBN " + libro.getIsbn() + " figura en la base de datos");
        }
        if (libro.getTitulo().isEmpty()) {
            throw new Exception("El campo nombre es obligatorio");
        }
        if (libro.getIsbn().isEmpty()) {
            throw new Exception("El campo isbn es obligatorio");
        }
        if (libro.getAlta() == null) {
            libro.setAlta(Boolean.FALSE);
        }
        if (libro.getAnio() == null) {
            throw new Exception("El campo año no debe estar vacio");
        }
        if (libro.getEjemplares() == null) {
            throw new Exception("El campo ejemplare no debe estar vacio");
        }
        if (libro.getEjemplaresPrestados() != null) {
            libro.setEjemplaresPrestados(0);
        }
        if (!Objects.equals(libro.getEjemplaresRestantes(), libro.getEjemplares())) {
            libro.setEjemplaresRestantes(libro.getEjemplares());
        }
    }

    private Libro TraerLibroPorISBN(String isbn) {
        try {
            return dao.buscarPorISBN(isbn);
        } catch (Exception e) {
            return null;
        }
    }

    public void editar(Libro libro) {
        try {
            Libro l = dao.buscarPorISBN(libro.getIsbn());
            if (libro.getAlta()) {
                l.setAlta(libro.getAlta());
            }
            if (libro.getAnio() != null) {
                l.setAnio(libro.getAnio());
            }
            if (libro.getTitulo() != null) {
                l.setTitulo(libro.getTitulo());
            }
            dao.modificar(l);
            System.out.println("Libro modificado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminar(String isbn) {
        try {
            Libro l = dao.buscarPorISBN(isbn);
            dao.eliminar(l);
            System.out.println("Libro eliminado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Libro BuscarLibroISBN(String isbn) {
        try {
            return dao.buscarPorISBN(isbn);
        } catch (Exception e) {
            System.out.println("No se encontro el ISBN " + isbn + " en nuestra base de datos");
            return null;
        }
    }

    public List<Libro> BuscarPorTitulo(String titulo) {
        try {
            return dao.BuscarPorTitulo(titulo);
        } catch (Exception e) {
            System.out.println("No se encontro el titulo" + titulo + " en nuestra base de datos");
            return null;
        }
    }

    public List<Libro> BuscarPorAutor(String autor) {
        try {
            return dao.BuscarPorAutor(autor);
        } catch (Exception e) {
            System.out.println("No se encontro el autor " + autor + " en la base de datos.");
            return null;
        }
    }

    public List<Libro> BuscarPorEditorial(String nombre) {
        try {
            return dao.BuscarPorEditorial(nombre);
        } catch (Exception e) {
            System.out.println("No se encontro el nombre de la editorial " + nombre + " en nuestra base de datos");
            return null;
        }
    }

    public List<Libro> listarTodoLosLibros() {
        try {
            return dao.ListarLibros();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void editarPrestamosLibro(String isbn) {
        try {
            Libro l = BuscarLibroISBN(isbn);
            l.setEjemplaresPrestados(l.getEjemplaresPrestados() + 1);
            l.setEjemplaresRestantes(l.getEjemplaresRestantes() - 1);
            dao.modificar(l);
        } catch (Exception e) {
        }
    }

    public void editarDevolucionLibro(String isbn) throws Exception {
        Libro l = BuscarLibroISBN(isbn);

        if (l != null) {
            validacionDelPrestamos(l);
            l.setEjemplaresPrestados(l.getEjemplaresPrestados() - 1);
            l.setEjemplaresRestantes(l.getEjemplaresRestantes() + 1);
            dao.modificar(l);
        } else {
            throw new Exception("No se encontro el ISBN");
        }
    }

    public void PrestamoLibro(String isbn) throws Exception {
        Libro libro = BuscarLibroISBN(isbn);

        if (libro != null) {
            validacionDelPrestamos(libro);
            editarPrestamosLibro(isbn);
        } else {
            throw new Exception("No se encontro el ISBN en la base de datos");
        }
    }

    private void validacionDelPrestamos(Libro libro) throws Exception {
        if (Objects.equals(libro.getEjemplaresPrestados(), libro.getEjemplares())) {
            throw new Exception("No se puede hacer el prestamos, esperen devoluciones");
        }
        if (libro.getEjemplaresRestantes() == 0) {
            throw new Exception("No se puede hacer el prestamos, esperen devoluciones");
        }
    }
}
