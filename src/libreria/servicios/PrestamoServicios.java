package libreria.servicios;

import java.util.Date;
import java.util.List;
import libreria.persistencias.PrestamoDAO;
import libreria.entidades.Prestamo;

public class PrestamoServicios {

    private PrestamoDAO dao;
    private LibroServicios libroServicios;

    public PrestamoServicios() {
        dao = new PrestamoDAO();
        libroServicios = new LibroServicios();
    }

    public void guardar(Prestamo prestamo) {
        try {
            ValidarElPrestamo(prestamo);
            libroServicios.PrestamoLibro(prestamo.getLibro().getIsbn());
            dao.guardar(prestamo);
            System.out.println("Se genero el prestamo del libro: " + prestamo.getLibro().getTitulo());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void ValidarElPrestamo(Prestamo prestamo) throws Exception{
        if (prestamo.getFechaPrestamo() == null) {
            throw new Exception("Fecha del prestamo no puede ser campo vacio");
        }
        if (prestamo.getFechaDevolucion() == null) {
            throw new Exception("Fecha del devolucion no puede ser campo vacio");
        }
        if (prestamo.getLibro() == null) {
            throw new Exception("No se selecciono ningun libro");
        }
        if (prestamo.getCliente() == null) {
            throw new Exception("No se selecciono ningun cliente");
        }
        if (prestamo.getFechaPrestamo().before(new Date())) {
            throw new Exception("La fecha que puso es anterior a la fecha actual");
        }
        
    }

    public void devolucion(Integer id) {
        try {
            Prestamo prestamo = dao.BuscarPorId(id);
            libroServicios.editarDevolucionLibro(prestamo.getLibro().getIsbn());
            eliminarPrestamo(prestamo.getId());
            System.out.println("Se devolvio correctamente el libro: " + prestamo.getLibro().getTitulo());
        } catch (Exception e) {
        }
    }

    public Prestamo buscarPorId(int id) {
        try {
            return dao.BuscarPorId(id);
        } catch (Exception e) {
            return null;
        }
    }

    public void eliminarPrestamo(int id) {
        try {
            Prestamo prestamo = buscarPorId(id);
            dao.eliminar(prestamo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Prestamo> BuscarPrestamosPorClente(String nombre){
        try {
            return dao.BuscarTodoPrestamoCliente(nombre);
        } catch (Exception e) {
            return null;
        }
    }
    
}
