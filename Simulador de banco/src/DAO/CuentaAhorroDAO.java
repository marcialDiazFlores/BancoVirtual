package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Cliente;
import Modelo.CuentaDeAhorro;
import db.ConexionBDD;

public class CuentaAhorroDAO implements interfazCuentaAhorroDAO {
    ConexionBDD conn = new ConexionBDD();

    @Override
    public void agregarCuentaDeAhorro(CuentaDeAhorro cuenta) throws SQLException {
        try (Connection connection = conn.conectar()) {

            String query = "INSERT INTO cuentas_ahorro (id, cliente_id, saldo, tasa_interes, tope_minimo) VALUES (DEFAULT, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                // Se obtienen los datos del objeto CuentaAhorro
                int cliente_id = cuenta.getIdCliente();
                int saldo = cuenta.getSaldo();
                float tasa_interes = cuenta.getTasaInteres();
                int tope_minimo = cuenta.getTopeMinimo();

                // Se establecen los parámetros de la consulta
                preparedStatement.setInt(1, cliente_id);
                preparedStatement.setInt(2, saldo);
                preparedStatement.setFloat(3, tasa_interes);
                preparedStatement.setInt(4, tope_minimo);

                // Se ejecuta la consulta preparada
                preparedStatement.executeUpdate();

                // Obtener las claves generadas (en este caso, el ID generado)
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idGenerado = generatedKeys.getInt(1);

                        // Setea el ID en el objeto Cliente
                        cuenta.setId(idGenerado);
                    } else {
                        throw new SQLException("Error al obtener el ID de la cuenta de ahorro");
                    }
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al agregar cuenta de ahorro", e);
        }
    }

    public List<CuentaDeAhorro> obtenerCuentasDeAhorro() {
        List<CuentaDeAhorro> cuentasDeAhorro = new ArrayList<>();

        try (Connection connection = conn.conectar()) {
            String query = "SELECT * FROM cuentas_ahorro";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int cliente_id = resultSet.getInt("cliente_id");
                    int saldo = resultSet.getInt("saldo");
                    float tasa_interes = resultSet.getFloat("tasa_interes");
                    int tope_minimo = resultSet.getInt("tope_minimo");

                    // Crea un objeto CuentaAhorro con los datos obtenidos de la base de datos
                    CuentaDeAhorro cuentaDeAhorro = new CuentaDeAhorro(cliente_id, saldo, tasa_interes, tope_minimo);
                    cuentaDeAhorro.setId(id);

                    // Agrega la cuenta de ahorro a la lista
                    cuentasDeAhorro.add(cuentaDeAhorro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cuentasDeAhorro;
    }

    public void eliminarCuentaDeAhorro(CuentaDeAhorro cuenta) throws SQLException {
        try (Connection connection = conn.conectar()) {
            String query = "DELETE FROM cuentas_ahorro WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Se establecen los parámetros de la consulta
                preparedStatement.setInt(1, cuenta.getId());

                // Se ejecuta la consulta preparada
                int filasActualizadas = preparedStatement.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("Cuenta de ahorro eliminada exitosamente de la base de datos.");
                } else {
                    System.out.println("Error: No se encontró la cuenta de ahorro con ID " + cuenta.getId());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new SQLException("Error al eliminar la cuenta de ahorro de la base de datos", e);
        }
    }

    public boolean tieneCuentaDeAhorro(int idCliente) {
        try (Connection connection = conn.conectar()) {
            String query = "SELECT * FROM cuentas_ahorro WHERE cliente_id = ?";
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