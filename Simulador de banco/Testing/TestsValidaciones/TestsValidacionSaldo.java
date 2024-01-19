package TestsValidaciones;

import org.testng.annotations.Test;
import static Vista.Validaciones.validarSaldo;
import static org.testng.Assert.*;

public class TestsValidacionSaldo {

    @Test
    public void testValidarSaldo_CasoValido_Positive() {
        int saldo = 1000;
        boolean resultado = validarSaldo(saldo);
        assertTrue(resultado, "El saldo debería ser válido");
    }

    @Test
    public void testValidarSaldo_CasoValido_LimiteInferior() {
        int saldo = 0;
        boolean resultado = validarSaldo(saldo);
        assertTrue(resultado, "El saldo debería ser válido");
    }

    @Test
    public void testValidarSaldo_CasoInvalido_Negativo() {
        int saldo = -500;
        boolean resultado = validarSaldo(saldo);
        assertFalse(resultado, "El saldo es inválido");
    }

    @Test
    public void testValidarSaldo_CasoInvalido_LimiteSuperior() {
        int saldo = 50000000;
        boolean resultado = validarSaldo(saldo);
        assertFalse(resultado, "El saldo es inválido");
    }

    @Test
    public void testValidarSaldo_CasoValido_LimiteSuperiorAjustado() {
        int saldo = 49999999;
        boolean resultado = validarSaldo(saldo);
        assertTrue(resultado, "El saldo debería ser válido");
    }

    @Test
    public void testValidarSaldo_CasoInvalido_LimiteSuperiorExcedido() {
        int saldo = 50000001;
        boolean resultado = validarSaldo(saldo);
        assertFalse(resultado, "El saldo es inválido");
    }

    @Test
    public void testValidarSaldo_CasoInvalido_LimiteInferiorExcedido() {
        int saldo = -1;
        boolean resultado = validarSaldo(saldo);
        assertFalse(resultado, "El saldo es inválido");
    }
}

