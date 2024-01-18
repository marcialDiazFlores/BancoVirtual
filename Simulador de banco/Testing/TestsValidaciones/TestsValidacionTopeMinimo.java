package TestsValidaciones;

import org.testng.annotations.Test;
import static Vista.Validaciones.validarTopeMinimo;
import static org.testng.Assert.*;

public class TestsValidacionTopeMinimo {

    @Test
    public void testValidarTopeMinimo_CasoValido_Positivo() {
        int topeMinimo = 1000000;
        boolean resultado = validarTopeMinimo(topeMinimo);
        assertTrue(resultado, "El tope mínimo debería ser válido");
    }

    @Test
    public void testValidarTopeMinimo_CasoValido_LimiteInferior() {
        int topeMinimo = 0;
        boolean resultado = validarTopeMinimo(topeMinimo);
        assertTrue(resultado, "El tope mínimo debería ser válido");
    }

    @Test
    public void testValidarTopeMinimo_CasoValido_LimiteSuperior() {
        int topeMinimo = 5000000;
        boolean resultado = validarTopeMinimo(topeMinimo);
        assertTrue(resultado, "El tope mínimo debería ser válido");
    }

    @Test
    public void testValidarTopeMinimo_CasoInvalido_Negativo() {
        int topeMinimo = -100;
        boolean resultado = validarTopeMinimo(topeMinimo);
        assertFalse(resultado, "El tope mínimo es inválido");
    }

    @Test
    public void testValidarTopeMinimo_CasoInvalido_LimiteSuperiorExcedido() {
        int topeMinimo = 5000001;
        boolean resultado = validarTopeMinimo(topeMinimo);
        assertFalse(resultado, "El tope mínimo es inválido");
    }
}
