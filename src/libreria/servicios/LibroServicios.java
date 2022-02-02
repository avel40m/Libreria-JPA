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
    
    private void validarCampos(Libro libro) throws Exception{
        if (BuscarLibroISBN(libro.getIsbn()).toString() != null) {
            throw new Exception("Error el ISBN " + libro.getIsbn() + " figura en la base de datos");
        }
        if(libro.getTitulo().isEmpty()){
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

    public void eliminar(Libro libro) {
        try {
            Libro l = dao.buscarPorISBN(libro.getIsbn());
            dao.eliminar(l);
            System.out.println("Libro eliminado");
        } catch (Exception e) {
            System.out.println("No se encontro el id del libro");
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
}
