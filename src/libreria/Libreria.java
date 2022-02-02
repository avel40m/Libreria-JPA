package libreria;

import java.util.List;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.servicios.AutorServicios;
import libreria.servicios.LibroServicios;

public class Libreria {

    public static void main(String[] args) {
        LibroServicios libroServicios = new LibroServicios();
        AutorServicios autorServicios = new AutorServicios();

        Autor a = new Autor(null,"Borge", Boolean.FALSE);
        Editorial e = new Editorial(null, "Ediction IV", Boolean.FALSE);
        
        Libro libro = new Libro("20","Platero y yo","1720",15,0,15, Boolean.TRUE, a, e);

        libroServicios.guardar(libro);
//        List<Autor> autores = autorServicios.BuscarAutor("ce");
//
//        for (Autor a : autores) {
//            System.out.println(a);
//        }
//        Libro libro = libroServicios.BuscarLibroISBN("13");
//        System.out.println(libro);
//        List<Libro> libros = libroServicios.BuscarPorTitulo("ha");
//        for (Libro libro : libros) {
//            System.out.println(libro);
//        }
//        List<Libro> libros = libroServicios.BuscarPorAutor("e");
//        for (Libro libro : libros) {
//            System.out.println(libro);
//        }
//        List<Libro> libros = libroServicios.BuscarPorEditorial("zaBALA");
//        for (Libro libro : libros) {
//            System.out.println(libro);
//        }
    }

}
