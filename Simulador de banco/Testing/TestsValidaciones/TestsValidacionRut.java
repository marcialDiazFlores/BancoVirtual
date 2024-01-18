package TestsValidaciones;

import org.testng.annotations.Test;
import static Vista.Validaciones.validarRut;
import static org.testng.Assert.*;

public class TestsValidacionRut {

    @Test
    public void testValidarRut_CasoValido_ConPuntosYGuion() {
        String rut = "12.345.678-5";
        boolean resultado = validarRut(rut);
        assertTrue(resultado, "El Rut debería ser válido");
    }

    @Test
    public void testValidarRut_CasoValido_SinPuntosNiGuion() {
        String rut = "123456785";
        boolean resultado = validarRut(rut);
        assertTrue(resultado, "El Rut debería ser válido");
    }

    @Test
    public void testValidarRut_CasoInvalido_DigitoVerificadorIncorrecto() {
        String rut = "12.345.678-9"; // Dígito verificador incorrecto
        boolean resultado = validarRut(rut);
        assertFalse(resultado, "El Rut es inválido");
    }

    @Test
    public void testValidarRut_CasoInvalido_CaracteresInvalidos() {
        String rut = "12.3a5.678-5"; // Caracter no numérico
        boolean resultado = validarRut(rut);
        assertFalse(resultado, "El Rut es inválido");
    }

    @Test
    public void testValidarRut_CasoInvalido_LongitudIncorrecta() {
        String rut = "12.345.678-50"; // Longitud mayor de la requerida
        boolean resultado = validarRut(rut);
        assertFalse(resultado, "El Rut es inválido");
    }

    @Test
    public void testValidarRut_CasoInvalido_FormatoIncorrecto() {
        String rut = "123-45678"; // Formato incorrecto
        boolean resultado = validarRut(rut);
        assertFalse(resultado, "El Rut es inválido");
    }

    @Test
    public void testValidarRut_CasoInvalido_Null() {
        String rut = null;
        boolean resultado = validarRut(rut);
        assertFalse(resultado, "El Rut es inválido");
    }

    @Test
    public void testValidarRut_CasoInvalido_Empty() {
        String rut = "";
        boolean resultado = validarRut(rut);
        assertFalse(resultado, "El Rut es inválido");
    }

    @Test
    public void testValidarRut_CasoInvalido_Espacios() {
        String rut = "12. 345.678-5"; // Espacios en el Rut
        boolean resultado = validarRut(rut);
        assertFalse(resultado, "El Rut es inválido");
    }

    @Test
    public void testValidarRut_CasoInvalido_Cero() {
        String rut = "0";
        boolean resultado = validarRut(rut);
        assertFalse(resultado, "El Rut es inválido");
    }
}
