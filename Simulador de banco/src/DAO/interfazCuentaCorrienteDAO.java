package DAO;

import java.sql.SQLException;
import java.util.List;

import Modelo.CuentaCorriente;

public interface interfazCuentaCorrienteDAO {
    List<CuentaCorriente> obtenerCuentasCorrientes();
    void agregarCuentaCorriente(CuentaCorriente cuenta) throws SQLException;
    boolean eliminarCuentaCorriente(int idCliente) throws SQLException;
}