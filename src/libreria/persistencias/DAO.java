package libreria.persistencias;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public abstract class DAO {
    
    protected final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("JPAPU");
    protected EntityManager em = EMF.createEntityManager();
    
    public void conectar(){
        if (!em.isOpen()) {
            em = EMF.createEntityManager();
        }
    }
    
    public void desconectar(){
        if (em.isOpen()) {
            em.close();
        }
    }
}
