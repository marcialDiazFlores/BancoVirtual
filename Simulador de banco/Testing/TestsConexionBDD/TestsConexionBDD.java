package TestsConexionBDD;

import org.testng.annotations.Test;

import db.ConexionBDD;
import static org.testng.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestsConexionBDD {
    private Connection connection;
    @Test
    public void testConectar_Exito() throws SQLException, ClassNotFoundException {
        // Cargar el controlador JDBC de PostgreSQL
        Class.forName("org.postgresql.Driver");

        // Establecer la conexión con la base de datos PostgreSQL
        String dbUrl = System.getenv("DB_URL");
        String dbUsuario = System.getenv("DB_USUARIO");
        String dbContrasena = System.getenv("DB_CONTRASENA");

        connection = DriverManager.getConnection(dbUrl, dbUsuario, dbContrasena);

        // Verificar que la conexión no sea nula
        assertNotNull(connection);
    }

    @Test(expectedExceptions = SQLException.class)
    public void testConectar_UrlEquivocada() throws SQLException, ClassNotFoundException {
        // Cargar el controlador JDBC de PostgreSQL
        Class.forName("org.postgresql.Driver");

        // Establecer la conexión con la base de datos PostgreSQL
        String dbUrl = System.getenv("UrlEquivocada");
        String dbUsuario = System.getenv("DB_USUARIO");
        String dbContrasena = System.getenv("DB_CONTRASENA");

        connection = DriverManager.getConnection(dbUrl, dbUsuario, dbContrasena);

    }

    @Test(expectedExceptions = SQLException.class)
    public void testConectar_UsuarioEquivocado() throws SQLException, ClassNotFoundException {
        // Cargar el controlador JDBC de PostgreSQL
        Class.forName("org.postgresql.Driver");

        // Establecer la conexión con la base de datos PostgreSQL
        String dbUrl = System.getenv("DB_URL");
        String dbUsuario = System.getenv("UsuarioEquivocado");
        String dbContrasena = System.getenv("DB_CONTRASENA");

        connection = DriverManager.getConnection(dbUrl, dbUsuario, dbContrasena);

    }

    @Test(expectedExceptions = SQLException.class)
    public void testConectar_ContrasenaEquivocada() throws SQLException, ClassNotFoundException {
        // Cargar el controlador JDBC de PostgreSQL
        Class.forName("org.postgresql.Driver");

        // Establecer la conexión con la base de datos PostgreSQL
        String dbUrl = System.getenv("DB_URL");
        String dbUsuario = System.getenv("DB_USUARIO");
        String dbContrasena = System.getenv("contrasena_equivocada");

        connection = DriverManager.getConnection(dbUrl, dbUsuario, dbContrasena);

    }
}
