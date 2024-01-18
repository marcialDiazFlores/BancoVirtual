package TestsValidaciones;

import org.testng.annotations.Test;
import static Vista.Validaciones.validarTelefono;
import static org.testng.Assert.*;

public class TestsValidacionTelefono {

    @Test
    public void testValidarTelefono_CasoValido_ConPrefijo() {
        String telefono = "+56912345678";
        boolean resultado = validarTelefono(telefono);
        assertTrue(resultado, "El teléfono debería ser válido");
    }

    @Test
    public void testValidarTelefono_CasoValido_SinPrefijo() {
        String telefono = "989570203"; // Prefijo ausente
        boolean resultado = validarTelefono(telefono);
        assertTrue(resultado, "El teléfono debería ser válido");
    }

    @Test
    public void testValidarTelefono_CasoValido_SinPrefijo2() {
        String telefono = "89570203"; // Prefijo ausente
        boolean resultado = validarTelefono(telefono);
        assertTrue(resultado, "El teléfono debería ser válido");
    }

    @Test
    public void testValidarTelefono_CasoInvalido_ConEspacios() {
        String telefono = "+56 9 1234 5678";
        boolean resultado = validarTelefono(telefono);
        assertFalse(resultado, "El teléfono debería ser inválido");
    }

    @Test
    public void testValidarTelefono_CasoInvalido_OtroFormato() {
        String telefono = "+569 8957 0203";
        boolean resultado = validarTelefono(telefono);
        assertFalse(resultado, "El teléfono es inválido");
    }

    @Test
    public void testValidarTelefono_CasoInvalido_LongitudIncorrecta() {
        String telefono = "+569123"; // Longitud menor de la requerida
        boolean resultado = validarTelefono(telefono);
        assertFalse(resultado, "El teléfono es inválido");
    }

    @Test
    public void testValidarTelefono_CasoInvalido_CaracteresInvalidos() {
        String telefono = "+56a912345678"; // Carácter no numérico
        boolean resultado = validarTelefono(telefono);
        assertFalse(resultado, "El teléfono es inválido");
    }

    @Test
    public void testValidarTelefono_CasoInvalido_ConEspacios2() {
        String telefono = "+56 9 123456 78"; // Espacios en un lugar incorrecto
        boolean resultado = validarTelefono(telefono);
        assertFalse(resultado, "El teléfono es inválido");
    }

    @Test
    public void testValidarTelefono_CasoInvalido_ConPrefijoInvalido() {
        String telefono = "+540112345678"; // Prefijo no válido para Chile
        boolean resultado = validarTelefono(telefono);
        assertFalse(resultado, "El teléfono es inválido");
    }

    @Test
    public void testValidarTelefono_CasoInvalido_PrefijoIncorrecto() {
        String telefono = "+57912345678"; // Prefijo incorrecto para Chile
        boolean resultado = validarTelefono(telefono);
        assertFalse(resultado, "El teléfono es inválido");
    }
}