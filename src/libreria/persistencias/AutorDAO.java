package libreria.persistencias;

import libreria.entidades.Autor;
import java.util.List;

public class AutorDAO extends DAO {

    public void crear(Autor objeto) {
        conectar();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        desconectar();
    }

    public void editar(Autor objeto) {
        conectar();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
        desconectar();
    }

    public void eliminar(Autor objeto) {
        conectar();
        em.getTransaction().begin();
        em.remove(objeto);
        em.getTransaction().commit();
        desconectar();
    }

    public List<Autor> mostrarTodoLosLibros() {
        conectar();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a").getResultList();
        desconectar();
        return autores;
    }

    public Autor BuscarAutor(Integer id) {
        conectar();
        Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.id = :id").setParameter("id", id)
                .getSingleResult();
        desconectar();

        return autor;
    }

    public List<Autor> BuscarPorNombre(String nombre) {
        conectar();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre")
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList();
        desconectar();
        return autores;
    }

}
