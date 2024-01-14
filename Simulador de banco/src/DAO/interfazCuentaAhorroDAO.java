package DAO;

import java.sql.SQLException;
import java.util.List;

import Modelo.Cliente;
import Modelo.CuentaDeAhorro;

public interface interfazCuentaAhorroDAO {
    List<CuentaDeAhorro> obtenerCuentasDeAhorro();
    boolean agregarCuentaDeAhorro(CuentaDeAhorro cuentaDeAhorro) throws SQLException ;
    boolean actualizarCuentaDeAhorro(int idCliente, int saldo, float tasaInteres, int topeMinimo) throws SQLException ;
    boolean eliminarCuentaDeAhorro(int idCliente) throws SQLException ;
}
