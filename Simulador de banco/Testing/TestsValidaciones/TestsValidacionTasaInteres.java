package TestsValidaciones;

import org.testng.annotations.Test;
import static Vista.Validaciones.validarTasaInteres;
import static org.testng.Assert.*;

public class TestsValidacionTasaInteres {

    @Test
    public void testValidarTasaInteres_CasoValido_Positive() {
        double tasaInteres = 10.5;
        boolean resultado = validarTasaInteres(tasaInteres);
        assertTrue(resultado, "La tasa de interés debería ser válida");
    }

    @Test
    public void testValidarTasaInteres_CasoValido_LimiteInferior() {
        double tasaInteres = 3.01;
        boolean resultado = validarTasaInteres(tasaInteres);
        assertTrue(resultado, "La tasa de interés debería ser válida");
    }

    @Test
    public void testValidarTasaInteres_CasoInvalido_Negativo() {
        double tasaInteres = -5.0;
        boolean resultado = validarTasaInteres(tasaInteres);
        assertFalse(resultado, "La tasa de interés es inválida");
    }

    @Test
    public void testValidarTasaInteres_CasoInvalido_LimiteSuperior() {
        double tasaInteres = 20.01;
        boolean resultado = validarTasaInteres(tasaInteres);
        assertFalse(resultado, "La tasa de interés es inválida");
    }

    @Test
    public void testValidarTasaInteres_CasoValido_LimiteSuperiorAjustado() {
        double tasaInteres = 19.99;
        boolean resultado = validarTasaInteres(tasaInteres);
        assertTrue(resultado, "La tasa de interés debería ser válida");
    }

    @Test
    public void testValidarTasaInteres_CasoInvalido_LimiteSuperiorExcedido() {
        double tasaInteres = 20.1;
        boolean resultado = validarTasaInteres(tasaInteres);
        assertFalse(resultado, "La tasa de interés es inválida");
    }

    @Test
    public void testValidarTasaInteres_CasoInvalido_LimiteInferiorExcedido() {
        double tasaInteres = 2.99;
        boolean resultado = validarTasaInteres(tasaInteres);
        assertFalse(resultado, "La tasa de interés es inválida");
    }
}
