package TestsLogin;

import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import DAO.AdministradorDAO;

public class TestsValidarLoginAdministrador {

    private final AdministradorDAO administradorDAO = new AdministradorDAO();

    @Test
    public void testValidarLoginAdministrador_CasoValido() {
        String rut = "19.960.607-7";
        String contrasena = "Admin1";
        boolean resultado = administradorDAO.validarLoginAdministrador(rut, contrasena);
        assertTrue(resultado, "El login debería ser válido");
    }

    @Test
    public void testValidarLoginAdministrador_CasoValido2() {
        String rut = "9.905.129-9";
        String contrasena = "Admin2";
        boolean resultado = administradorDAO.validarLoginAdministrador(rut, contrasena);
        assertTrue(resultado, "El login debería ser válido");
    }

    @Test
    public void testValidarLoginAdministrador_CasoValido3() {
        String rut = "19.524.734-k";
        String contrasena = "Admin3";
        boolean resultado = administradorDAO.validarLoginAdministrador(rut, contrasena);
        assertTrue(resultado, "El login debería ser válido");
    }

    @Test
    public void testValidarLoginAdministrador_CasoInvalido_ContrasenaIncorrecta() {
        String rut = "18.901.234-5";
        String contrasena = "ContraseñaIncorrecta";
        boolean resultado = administradorDAO.validarLoginAdministrador(rut, contrasena);
        assertFalse(resultado, "El login debería ser inválido");
    }

    @Test
    public void testValidarLoginAdministrador_CasoInvalido_RutIncorrecto() {
        String rut = "99.999.999-9"; // Rut inexistente
        String contrasena = "Admin1";
        boolean resultado = administradorDAO.validarLoginAdministrador(rut, contrasena);
        assertFalse(resultado, "El login debería ser inválido");
    }

    @Test
    public void testValidarLoginAdministrador_CasoInvalido_RutNulo() {
        String rut = null;
        String contrasena = "Admin1";
        boolean resultado = administradorDAO.validarLoginAdministrador(rut, contrasena);
        assertFalse(resultado, "El login debería ser inválido");
    }

    @Test
    public void testValidarLoginAdministrador_CasoInvalido_ContrasenaNula() {
        String rut = "12.345.678-9";
        String contrasena = null;
        boolean resultado = administradorDAO.validarLoginAdministrador(rut, contrasena);
        assertFalse(resultado, "El login debería ser inválido");
    }
}
