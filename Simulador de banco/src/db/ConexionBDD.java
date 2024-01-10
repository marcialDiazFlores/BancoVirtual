package db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDD implements interfazConexionBDD {
    private Connection connection;

    @Override
    public Connection conectar() throws SQLException {
        try {
            // Cargar el controlador JDBC de PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Establecer la conexión con la base de datos PostgreSQL
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Banco", "postgres", "AdminBanco");
            // System.out.println("Conectado a la base de datos");
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el controlador JDBC", e);
        }
    }

}