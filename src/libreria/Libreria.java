package libreria;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Prestamo;
import libreria.entidades.Libro;
import libreria.entidades.Cliente;
import libreria.servicios.AutorServicios;
import libreria.servicios.ClienteServicios;
import libreria.servicios.EditorialServicios;
import libreria.servicios.LibroServicios;
import java.util.List;
import libreria.servicios.PrestamoServicios;

public class Libreria {
    
    public static void main(String[] args) throws Exception {
        LibroServicios libroServicios = new LibroServicios();
        AutorServicios autorServicios = new AutorServicios();
        EditorialServicios editorialServicios = new EditorialServicios();
        Scanner leer = new Scanner(System.in);
        ClienteServicios clienteServicios = new ClienteServicios();
        PrestamoServicios prestamoServicios = new PrestamoServicios();
        
//       Faltaría crear el menu
        
    }
    
    private static Date convertirStringADate(String fecha) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.parse(fecha);
    }
    
}
