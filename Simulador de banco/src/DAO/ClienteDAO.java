package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Cliente;
import db.ConexionBDD;

public class ClienteDAO implements interfazClienteDAO 
{
    ConexionBDD conn = new ConexionBDD();

    public List<Cliente> obtenerTodosLosClientes() {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = conn.conectar()) {
            String query = "SELECT * FROM clientes";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    int edad = resultSet.getInt("edad");
                    String email = resultSet.getString("email");
                    String rut = resultSet.getString("rut");
                    String fono = resultSet.getString("fono");

                    // Crea un objeto Cliente con los datos obtenidos de la base de datos
                    Cliente cliente = new Cliente(nombre, apellido, edad, email, rut, fono);
                    cliente.setId(id);

                    // Agrega el cliente a la lista
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public Cliente buscarCliente(String rut) {
        Cliente cliente = null;
        try (Connection connection = conn.conectar()) {
            String query = "SELECT * FROM clientes WHERE rut = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, rut);
                 ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nombre = resultSet.getString("nombre");
                    String apellido = resultSet.getString("apellido");
                    int edad = resultSet.getInt("edad");
                    String email = resultSet.getString("email");
                    String RUT = resultSet.getString("rut");
                    String fono = resultSet.getString("fono");

                    // Crea un objeto Cliente con los datos obtenidos de la base de datos
                    cliente = new Cliente(nombre, apellido, edad, email, RUT, fono);
                    cliente.setId(id);
                    return cliente;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public boolean agregarCliente(Cliente cliente) throws SQLException {
        try (Connection connection = conn.conectar()) {

            if (verificarRutExistente(cliente.getRut())) {
                return false;
            }

            String query = "INSERT INTO clientes (id, nombre, apellido, edad, email, rut, fono) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                // Se obtienen los datos del objeto Cliente
                String nombre = cliente.getNombre();
                String apellido = cliente.getApellido();
                int edad = cliente.getEdad();
                String email = cliente.getEmail();
                String rut = cliente.getRut();
                String fono = cliente.getFono();

                // Se establecen los parámetros de la consulta
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, apellido);
                preparedStatement.setInt(3, edad);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, rut);
                preparedStatement.setString(6, fono);

                // Se ejecuta la consulta preparada
                int filasAfectadas = preparedStatement.executeUpdate();

                return filasAfectadas > 0;

                // System.out.println("Inserción exitosa");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al agregar cliente", e);
        }
    }

    public boolean verificarRutExistente(String rut) throws SQLException {
        try (Connection connection = conn.conectar()) {
            String query = "SELECT COUNT(*) FROM clientes WHERE rut = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, rut);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al verificar el RUT existente", e);
        }
        return false;
    }

    public boolean actualizarCliente(Cliente cliente) throws SQLException {
        try (Connection connection = conn.conectar()) {
            String query = "UPDATE clientes SET nombre = ?, apellido = ?, edad = ?, email = ?, rut = ?, fono = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Se establecen los parámetros de la consulta
                preparedStatement.setString(1, cliente.getNombre());
                preparedStatement.setString(2, cliente.getApellido());
                preparedStatement.setInt(3, cliente.getEdad());
                preparedStatement.setString(4, cliente.getEmail());
                preparedStatement.setString(5, cliente.getRut());
                preparedStatement.setString(6, cliente.getFono());
                preparedStatement.setInt(7, cliente.getId());


                // Se ejecuta la consulta preparada
                int filasActualizadas = preparedStatement.executeUpdate();

                return filasActualizadas > 0;
            }
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar cliente", e);
        }
    }


    public void eliminarCliente(int id) throws SQLException {
        try (Connection connection = conn.conectar()) {
            String query = "DELETE FROM clientes WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Se establecen los parámetros de la consulta
                preparedStatement.setInt(1, id);

                // Se ejecuta la consulta preparada
                int filasActualizadas = preparedStatement.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("Cliente eliminado exitosamente de la base de datos.");
                } else {
                    System.out.println("Error: No se encontró el cliente con ID " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new SQLException("Error al eliminar al cliente de la base de datos", e);
        }
    }

    /*public void agregarCliente(Cliente cliente) throws SQLException {
        try (Connection connection = conn.conectar()) {

            String query = "INSERT INTO clientes (id, nombre, apellido, edad, email, rut, fono) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                // Se obtienen los datos del objeto Cliente
                String nombre = cliente.getNombre();
                String apellido = cliente.getApellido();
                int edad = cliente.getEdad();
                String email = cliente.getEmail();
                String rut = cliente.getRut();
                String fono = cliente.getFono();

                // Se establecen los parámetros de la consulta
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, apellido);
                preparedStatement.setInt(3, edad);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, rut);
                preparedStatement.setString(6, fono);

                // Se ejecuta la consulta preparada
                preparedStatement.executeUpdate();

                // Obtener las claves generadas (en este caso, el ID generado)
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idGenerado = generatedKeys.getInt(1);

                        // Setea el ID en el objeto Cliente
                        cliente.setId(idGenerado);

                        // System.out.println("Nombre: " + cliente.getNombre() + ", ID: " + cliente.getId());
                    } else {
                        throw new SQLException("Error al obtener el ID del cliente");
                    }
                }

                // System.out.println("Inserción exitosa");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al agregar cliente", e);
        }
    }*/
    
}