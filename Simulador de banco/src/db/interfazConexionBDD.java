package db;

import java.sql.Connection;
import java.sql.SQLException;

public interface interfazConexionBDD {
    Connection conectar() throws SQLException;
}