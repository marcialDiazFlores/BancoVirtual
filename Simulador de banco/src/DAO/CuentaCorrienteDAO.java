package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.CuentaCorriente;
import Modelo.CuentaDeAhorro;
import db.ConexionBDD;

public class CuentaCorrienteDAO implements interfazCuentaCorrienteDAO {
    ConexionBDD conn = new ConexionBDD();

    @Override
    public boolean agregarCuentaCorriente(CuentaCorriente cuenta) throws SQLException {
        try (Connection connection = conn.conectar()) {

            String query = "INSERT INTO cuentas_corrientes (id, cliente_id, saldo, sobregiro) VALUES (DEFAULT, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                // Se obtienen los datos del objeto CuentaCorriente
                int cliente_id = cuenta.getIdCliente();
                int saldo = cuenta.getSaldo();
                int sobregiro = cuenta.getSobregiro();

                // Se establecen los parámetros de la consulta
                preparedStatement.setInt(1, cliente_id);
                preparedStatement.setInt(2, saldo);
                preparedStatement.setInt(3, sobregiro);

                // Se ejecuta la consulta preparada
                preparedStatement.executeUpdate();

                // Obtener las claves generadas (en este caso, el ID generado)
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idGenerado = generatedKeys.getInt(1);

                        // Setea el ID en el objeto Cliente
                        cuenta.setId(idGenerado);
                        return true;
                    } else {
                        throw new SQLException("Error al obtener el ID de la cuenta corriente");
                    }
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al agregar cuenta corriente", e);
        }
    }

    public CuentaCorriente obtenerCuentaCorriente(int clienteId) {
        CuentaCorriente cuentaCorriente = new CuentaCorriente();

        try (Connection connection = conn.conectar()) {
            String query = "SELECT * FROM cuentas_corrientes WHERE cliente_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, clienteId);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int cliente_id = resultSet.getInt("cliente_id");
                    int saldo = resultSet.getInt("saldo");
                    int sobregiro = resultSet.getInt("sobregiro");

                    // Crea un objeto CuentaAhorro con los datos obtenidos de la base de datos
                    cuentaCorriente = new CuentaCorriente(cliente_id, saldo, sobregiro);
                    cuentaCorriente.setId(id);
                    return cuentaCorriente;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return cuentaCorriente;
        }
        return cuentaCorriente;
    }

    public List<CuentaCorriente> obtenerCuentasCorrientes() {
        List<CuentaCorriente> cuentasCorrientes = new ArrayList<>();

        try (Connection connection = conn.conectar()) {
            String query = "SELECT * FROM cuentas_corrientes ORDER BY id ASC";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int cliente_id = resultSet.getInt("cliente_id");
                    int saldo = resultSet.getInt("saldo");
                    int sobregiro = resultSet.getInt("sobregiro");

                    // Crea un objeto CuentaAhorro con los datos obtenidos de la base de datos
                    CuentaCorriente cuentaCorriente = new CuentaCorriente(cliente_id, saldo, sobregiro);
                    cuentaCorriente.setId(id);

                    // Agrega la cuenta de ahorro a la lista
                    cuentasCorrientes.add(cuentaCorriente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cuentasCorrientes;
    }

    public boolean actualizarCuentaCorriente(int idCliente, int saldo, int sobregiro) throws SQLException {
        try (Connection connection = conn.conectar()) {
            String query = "UPDATE cuentas_corrientes SET saldo = ?, sobregiro = ? WHERE cliente_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Se establecen los parámetros de la consulta
                preparedStatement.setInt(1, saldo);
                preparedStatement.setInt(2, sobregiro);
                preparedStatement.setInt(3, idCliente);

                // Se ejecuta la consulta preparada
                int filasActualizadas = preparedStatement.executeUpdate();

                return filasActualizadas > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException("Error al actualizar cliente", e);
        }
    }

    public boolean eliminarCuentaCorriente(int idCliente) throws SQLException {
        try (Connection connection = conn.conectar()) {
            String query = "DELETE FROM cuentas_corrientes WHERE cliente_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Se establecen los parámetros de la consulta
                preparedStatement.setInt(1, idCliente);

                // Se ejecuta la consulta preparada
                int filasActualizadas = preparedStatement.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("Cuenta corriente eliminada exitosamente de la base de datos.");
                    return true;
                } else {
                    System.out.println("Error: No se encontró la cuenta corriente con ID " + idCliente);
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new SQLException("Error al eliminar la cuenta corriente de la base de datos", e);
        }
    }

    public boolean tieneCuentaCorriente(int idCliente) {
        try (Connection connection = conn.conectar()) {
            String query = "SELECT * FROM cuentas_corrientes WHERE cliente_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idCliente);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // Retorna true si hay resultados, false si no hay resultados
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }
}