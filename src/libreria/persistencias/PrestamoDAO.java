
package libreria.persistencias;

import java.util.List;
import libreria.entidades.Prestamo;

public class PrestamoDAO extends DAO {
    
    public void guardar(Prestamo objeto){
        conectar();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public void editar(Prestamo objeto){
        conectar();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public void eliminar(Prestamo objeto){
        conectar();
        em.getTransaction().begin();
        if (!em.contains(objeto)) {
            objeto = em.merge(objeto);
        }
        em.remove(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public Prestamo BuscarPorId(Integer id){
        conectar();
        Prestamo prestamo = (Prestamo) em.createQuery("SELECT p FROM Prestamo p WHERE p.id = :id")
                .setParameter("id", id).getSingleResult();
        desconectar();
        return prestamo;
    }
    
    public List<Prestamo> BuscarTodoPrestamoCliente(String nombre){
        conectar();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p WHERE p.cliente.nombre = :nombre")
                .setParameter("nombre", nombre).getResultList();
        desconectar();
        return prestamos;
    }
    
}
