package TestsValidaciones;

import org.testng.annotations.Test;
import static Vista.Validaciones.validarContrasena;
import static org.testng.Assert.*;

public class TestsValidacionContrasena {

    @Test
    public void testValidarContrasena_CasoValido() {
        String contrasena = "Abc123";
        boolean resultado = validarContrasena(contrasena);
        assertTrue(resultado, "La contraseña debería ser válida");
    }

    @Test
    public void testValidarContrasena_CasoValido2() {
        String contrasena = "A1bC2dE3fG";
        boolean resultado = validarContrasena(contrasena);
        assertTrue(resultado, "La contraseña debería ser válida");
    }



    @Test
    public void testValidarContrasena_CasoValido_CaracteresEspeciales() {
        String contrasena = "A1bC@dE";
        boolean resultado = validarContrasena(contrasena);
        assertTrue(resultado, "La contraseña debería ser válida");
    }

    @Test
    public void testValidarContrasena_CasoInvalido_SinMayuscula() {
        String contrasena = "abc123";
        boolean resultado = validarContrasena(contrasena);
        assertFalse(resultado, "La contraseña es inválida");
    }

    @Test
    public void testValidarContrasena_CasoInvalido_SinNumero() {
        String contrasena = "Abcdef";
        boolean resultado = validarContrasena(contrasena);
        assertFalse(resultado, "La contraseña es inválida");
    }

    @Test
    public void testValidarContrasena_CasoInvalido_LongitudMuyLarga() {
        String contrasena = "A1bC2dE3fG4hI5";
        boolean resultado = validarContrasena(contrasena);
        assertFalse(resultado, "La contraseña es inválida");
    }

    @Test
    public void testValidarContrasena_CasoInvalido_LongitudMuyCorta() {
        String contrasena = "A1b";
        boolean resultado = validarContrasena(contrasena);
        assertFalse(resultado, "La contraseña es inválida");
    }

    @Test
    public void testValidarContrasena_CasoInvalido_Null() {
        String contrasena = null;
        boolean resultado = validarContrasena(contrasena);
        assertFalse(resultado, "La contraseña es inválida");
    }

    @Test
    public void testValidarContrasena_CasoInvalido_SoloNumeros() {
        String contrasena = "123456";
        boolean resultado = validarContrasena(contrasena);
        assertFalse(resultado, "La contraseña es inválida");
    }
}
