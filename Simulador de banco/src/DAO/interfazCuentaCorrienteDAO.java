package DAO;

import java.sql.SQLException;
import java.util.List;

import Modelo.CuentaCorriente;

public interface interfazCuentaCorrienteDAO {
    List<CuentaCorriente> obtenerCuentasCorrientes();
    boolean agregarCuentaCorriente(CuentaCorriente cuenta) throws SQLException;
    boolean actualizarCuentaCorriente(int idCliente, int saldo, int sobregiro) throws SQLException ;

    boolean eliminarCuentaCorriente(int idCliente) throws SQLException;
}