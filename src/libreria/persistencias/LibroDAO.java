package libreria.persistencias;

import libreria.entidades.Libro;
import java.util.List;

public class LibroDAO extends DAO {

    public void guardar(Libro objeto) {
        conectar();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        desconectar();
    }

    public void modificar(Libro objeto) {
        conectar();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
        desconectar();
    }

    public void eliminar(Libro objeto) {
        conectar();
        em.getTransaction().begin();
        em.remove(objeto);
        em.getTransaction().commit();
        desconectar();
    }

    public Libro buscarPorISBN(String isbn) {
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :id")
                .setParameter("id", isbn)
                .getSingleResult();
        desconectar();
        return libro;
    }

    public List<Libro> BuscarPorTitulo(String titulo) {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
                .setParameter("titulo", "%" + titulo + "%")
                .getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> BuscarPorAutor(String autor) {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre LIKE :autor")
                .setParameter("autor", "%" + autor + "%")
                .getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> BuscarPorEditorial(String nombre) {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.editorial.nombre LIKE :editorial")
                .setParameter("editorial", "%" + nombre + "%")
                .getResultList();
        desconectar();
        return libros;
    }
}
