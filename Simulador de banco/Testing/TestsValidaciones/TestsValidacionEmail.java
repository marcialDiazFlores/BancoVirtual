package TestsValidaciones;

import org.testng.annotations.Test;

import static Vista.Validaciones.validarEmail;
import static org.testng.Assert.*;

public class TestsValidacionEmail {

    @Test
    public void testValidarEmail_CasoValido() {
        String email = "usuario@example.com"; // Email válido
        assertTrue(validarEmail(email), "El email debería ser válido");
    }

    @Test
    public void testValidarEmail_CasoValido2() {
        String email = "user123@gmail.com"; // Email válido
        assertTrue(validarEmail(email), "El email debería ser válido");
    }

    @Test
    public void testValidarEmail_CasoValido3() {
        String email = "john.doe@company.org"; // Email válido
        assertTrue(validarEmail(email), "El email debería ser válido");
    }

    @Test
    public void testValidarEmail_CasoValido4() {
        String email = "info@website.net"; // Email válido
        assertTrue(validarEmail(email), "El email debería ser válido");
    }

    @Test
    public void testValidarEmail_CasoInvalido_Null() {
        String email = null; // Email es null
        assertFalse(validarEmail(email), "El email no debería ser null");
    }

    @Test
    public void testValidarEmail_CasoInvalido_Vacio() {
        String email = ""; // Email vacío
        assertFalse(validarEmail(email), "El email no debería estar vacío");
    }

    @Test
    public void testValidarEmail_CasoInvalido_SinArroba() {
        String email = "usuarioexample.com"; // Falta el símbolo @
        assertFalse(validarEmail(email), "El email es inválido");
    }

    @Test
    public void testValidarEmail_CasoInvalido_SinDominio() {
        String email = "usuario@"; // Falta el dominio después de @
        assertFalse(validarEmail(email), "El email es inválido");
    }

    @Test
    public void testValidarEmail_CasoInvalido_ConEspacios() {
        String email = "user name@example.com"; // Contiene espacios
        assertFalse(validarEmail(email), "El email es inválido");
    }

    @Test
    public void testValidarEmail_CasoInvalido_CaracterEspecial() {
        String email = "usuario!@domain.com"; // Contiene un carácter especial no permitido
        assertFalse(validarEmail(email), "El email es inválido");
    }

    @Test
    public void testValidarEmail_CasoInvalido_PuntoInicial() {
        String email = ".usuario@example.com"; // El punto no puede estar al principio
        assertFalse(validarEmail(email), "El email es inválido");
    }

    @Test
    public void testValidarEmail_CasoInvalido_PuntoFinal() {
        String email = "usuario@example.com."; // El punto no puede estar al final
        assertFalse(validarEmail(email), "El email es inválido");
    }
}
