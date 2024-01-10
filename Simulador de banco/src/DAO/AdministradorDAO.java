package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Administrador;
import db.ConexionBDD;

public class AdministradorDAO implements interfazAdministradorDAO {
    ConexionBDD conn = new ConexionBDD();
    public boolean validarLoginAdministrador(String rut, String contrasena) {
        try (Connection connection = conn.conectar()) {
            String query = "SELECT * FROM administradores WHERE rut = ? AND contrasena = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, rut);
                preparedStatement.setString(2, contrasena);

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