package Controlador;

import DAO.AdministradorDAO;

public class ControladorAdministradores {
    AdministradorDAO daoAdministradores;
    public ControladorAdministradores() {
        this.daoAdministradores = new AdministradorDAO();
    }

    public boolean login(String rut, String contrasena) {
        boolean login = daoAdministradores.validarLoginAdministrador(rut, contrasena);
        return login;
    }
}
