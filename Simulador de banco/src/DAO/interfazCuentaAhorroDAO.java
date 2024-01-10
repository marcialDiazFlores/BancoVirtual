package DAO;

import java.sql.SQLException;
import java.util.List;

import Modelo.CuentaDeAhorro;

public interface interfazCuentaAhorroDAO {
    List<CuentaDeAhorro> obtenerCuentasDeAhorro();
    void agregarCuentaDeAhorro(CuentaDeAhorro cuenta) throws SQLException;
    void eliminarCuentaDeAhorro(CuentaDeAhorro cuenta) throws SQLException;
}
