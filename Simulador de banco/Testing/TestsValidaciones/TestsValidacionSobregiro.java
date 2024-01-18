package TestsValidaciones;

import org.testng.annotations.Test;
import static Vista.Validaciones.validarSobregiro;
import static org.testng.Assert.*;

public class TestsValidacionSobregiro {

    @Test
    public void testValidarSobregiro_CasoValido_Positive() {
        int sobregiro = 1000;
        boolean resultado = validarSobregiro(sobregiro);
        assertTrue(resultado, "El sobregiro debería ser válido");
    }

    @Test
    public void testValidarSobregiro_CasoValido_LimiteInferior() {
        int sobregiro = 1;
        boolean resultado = validarSobregiro(sobregiro);
        assertTrue(resultado, "El sobregiro debería ser válido");
    }

    @Test
    public void testValidarSobregiro_CasoInvalido_Negativo() {
        int sobregiro = -500;
        boolean resultado = validarSobregiro(sobregiro);
        assertFalse(resultado, "El sobregiro es inválido");
    }

    @Test
    public void testValidarSobregiro_CasoInvalido_LimiteSuperior() {
        int sobregiro = 2000000;
        boolean resultado = validarSobregiro(sobregiro);
        assertFalse(resultado, "El sobregiro es inválido");
    }

    @Test
    public void testValidarSobregiro_CasoValido_LimiteSuperiorAjustado() {
        int sobregiro = 1999999;
        boolean resultado = validarSobregiro(sobregiro);
        assertTrue(resultado, "El sobregiro debería ser válido");
    }

    @Test
    public void testValidarSobregiro_CasoInvalido_LimiteSuperiorExcedido() {
        int sobregiro = 2000001;
        boolean resultado = validarSobregiro(sobregiro);
        assertFalse(resultado, "El sobregiro es inválido");
    }

    @Test
    public void testValidarSobregiro_CasoInvalido_LimiteInferiorExcedido() {
        int sobregiro = 0;
        boolean resultado = validarSobregiro(sobregiro);
        assertFalse(resultado, "El sobregiro es inválido");
    }
}
