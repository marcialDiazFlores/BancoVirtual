package DAO;

import java.util.List;
import java.sql.SQLException;

import Modelo.Administrador;

public interface interfazAdministradorDAO {
    boolean validarLoginAdministrador(String rut, String contrasena);
}
