package libreria.persistencias;

import libreria.entidades.Editorial;

public class EditorialDAO extends DAO {
    
    public void crear(Editorial objeto){
        conectar();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public void editar(Editorial objeto){
        conectar();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public void eliminar(Editorial objeto){
        conectar();
        em.getTransaction().begin();
        em.remove(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public Editorial BuscarEditorial(Integer id){
        return (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.id = :id").setParameter("id", id)
                .getSingleResult();
    }
}
