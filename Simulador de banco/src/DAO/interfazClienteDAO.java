package DAO;

import java.util.List;
import java.sql.SQLException;

import Modelo.Cliente;

public interface interfazClienteDAO {
//    Cliente obtenerClientePorId(int id) throws SQLException ;
    List<Cliente> obtenerTodosLosClientes() throws SQLException ;
    boolean agregarCliente(Cliente cliente) throws SQLException ;
    boolean actualizarCliente(String nombre, String apellido, int edad, String email, String fono, String rut) throws SQLException ;
    boolean eliminarCliente(int id) throws SQLException ;
}