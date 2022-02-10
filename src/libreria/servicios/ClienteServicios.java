package libreria.servicios;

import java.util.List;
import libreria.persistencias.ClienteDAO;
import libreria.entidades.Cliente;

public class ClienteServicios {
    
    private ClienteDAO dao;
    
    public ClienteServicios(){
        dao = new ClienteDAO();
    }
    
    public void guardar(Cliente cliente){
        try {
            validacioDelCliente(cliente);
            dao.crear(cliente);
            System.out.println("Cliente guardado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void validacioDelCliente(Cliente cliente) throws Exception{
        if (cliente.getApellido() != null && cliente.getApellido().isEmpty()) {
            throw new Exception("El apellido está vacio");
        }
        if (cliente.getNombre() != null && cliente.getNombre().isEmpty()) {
            throw new Exception("El nombre está vacio");
        }
        if (cliente.getTelefono().length() < 8) {
            throw new Exception("El telefono debe tener un minimo de 8 caracteres");
        }
        if (cliente.getDocumento().toString().length() != 8 && cliente.getDocumento() != null) {
            throw new Exception("El D.N.I. debe tener 8 caracteres");
        }
        if (BuscarPorDni(cliente.getDocumento()) != null) {
            throw new Exception("El DNI está registrado con otro cliente");
        }
        
    }
    
    public void editar(Cliente cliente){
        
        try {
            Cliente c = dao.buscarPorId(cliente.getId());
            if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
                c.setNombre(c.getNombre());
            }
            if (cliente.getApellido() == null || cliente.getApellido().isEmpty()) {
                c.setApellido(c.getApellido());
            }
            if (cliente.getTelefono() == null || cliente.getTelefono().isEmpty()) {
                c.setTelefono(c.getTelefono());
            }
            if (cliente.getDocumento() == null ) {
                c.setDocumento(c.getDocumento());
            }
            if (cliente.getApellido() != null) {
                c.setApellido(cliente.getApellido());
            }if (cliente.getNombre() != null) {
                c.setNombre(cliente.getNombre());
            }
            if (cliente.getTelefono() != null) {
                c.setTelefono(cliente.getTelefono());
            }if (cliente.getDocumento() != null) {
                c.setDocumento(cliente.getDocumento());
            }
            dao.editar(c);
            System.out.println("Cliente editado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void eliminar(Integer id){
        try {
            Cliente cliente = dao.buscarPorId(id);
            dao.eliminar(cliente);
            System.out.println("Se elimino el cliente " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Cliente> listarTodoLosClientes(){
        try {
            return dao.listarTodoLosCliente();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Cliente BuscarClientePorId(Integer id){
        try {
            return dao.buscarPorId(id);
        } catch (Exception e) {
            return null;
        }
    }
    
    public Cliente BuscarPorDni(long dni){
        try {
            return dao.BusarPorDNI(dni);
        } catch (Exception e) {
            return null;
        }
    }
}
