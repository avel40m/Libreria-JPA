package libreria.persistencias;

import libreria.entidades.Cliente;
import java.util.List;

public class ClienteDAO extends DAO {
    
    public void crear(Cliente objeto){
        conectar();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public void editar(Cliente objeto){
        conectar();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public void eliminar(Cliente objeto){
        conectar();
        em.getTransaction().begin();
        em.remove(objeto);
        em.getTransaction().commit();
        desconectar();
    }
    
    public Cliente buscarPorId(Integer id){
        conectar();
        Cliente cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.id = :id")
                .setParameter("id", id).getSingleResult();
        desconectar();
        return cliente;
    }
    
    public List<Cliente> listarTodoLosCliente(){
        conectar();
        List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c").getResultList();
        desconectar();
        return clientes;
    }
    
    public Cliente BusarPorDNI(long dni){
        conectar();
        Cliente cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.documento = :dni")
                .setParameter("dni", dni).getSingleResult();
        desconectar();
        return cliente;
    }
}
