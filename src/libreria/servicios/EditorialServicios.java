package libreria.servicios;

import libreria.entidades.Editorial;
import libreria.persistencias.EditorialDAO;

public class EditorialServicios {

    private EditorialDAO dao;

    public EditorialServicios() {
        dao = new EditorialDAO();
    }
    
    public void crear(Editorial editorial){
        try {
            dao.crear(editorial);
            System.out.println("Se creo la editorial");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void editar(Integer id,Editorial editorial){
        try {
            Editorial e = dao.BuscarEditorial(id);
            
            if (editorial.getNombre() != null) {
                e.setNombre(editorial.getNombre());
            }
            if (editorial.getAlta() != null) {
                e.setAlta(editorial.getAlta());
            }
            dao.editar(e);
            System.out.println("Se modifico correctamente");
        } catch (Exception e) {
            System.out.println("No se encotro el id");
        }
    }
    
    public void eliminar(Integer id){
        try {
            Editorial editorial = dao.BuscarEditorial(id);
            dao.eliminar(editorial);
            System.out.println("Se elimino correctamente el id " + id);
        } catch (Exception e) {
            System.out.println("No se encontro el id");
        }
    }
}
