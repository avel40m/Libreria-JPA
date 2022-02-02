package libreria.servicios;

import java.util.List;
import libreria.persistencias.AutorDAO;
import libreria.entidades.Autor;

public class AutorServicios {
    
    private AutorDAO dao;
    
    public AutorServicios(){
        dao = new AutorDAO();
    }
    
    public void guardar(Autor autor){
        try {
            dao.crear(autor);
            System.out.println("Autor creado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void editar(Integer id,Autor autor){
        try {
            Autor a = dao.BuscarAutor(id);
            
            if (autor.getNombre() != null) {
                a.setNombre(autor.getNombre());
            }
            if (autor.getAlta() != null) {
                a.setAlta(autor.getAlta());
            }
            dao.editar(a);
            System.out.println("Autor editado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void eliminar(Integer id){
        try {
            Autor a = dao.BuscarAutor(id);
            dao.eliminar(a);
            System.out.println("Autor " + id + " se elimino");
        } catch (Exception e) {
            System.out.println("No se encontro el id");
        }
    }
    
    public List<Autor> BuscarAutor(String nombre){
        try {
            List<Autor> autores = dao.BuscarPorNombre(nombre);
            return autores;
        } catch (Exception e) {
            System.out.println("No se encontro el nombre");
            return null;
        }
    }
    
}
