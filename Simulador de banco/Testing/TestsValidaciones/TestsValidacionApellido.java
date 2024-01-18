package TestsValidaciones;

import org.testng.annotations.Test;

import static Vista.Validaciones.validarApellido;
import static org.testng.Assert.*;

public class TestsValidacionApellido {

    @Test
    public void testValidarApellido_CasoValido() {
        String apellido = "Smith"; // El apellido cumple con las restricciones
        assertTrue(validarApellido(apellido), "El apellido debería ser válido");
    }

    @Test
    public void testValidarApellido_CasoValido2() {
        String apellido = "González"; // El apellido cumple con las restricciones
        assertTrue(validarApellido(apellido), "El apellido debería ser válido");
    }

    @Test
    public void testValidarApellido_CasoValido3() {
        String apellido = "Rodríguez"; // El apellido cumple con las restricciones
        assertTrue(validarApellido(apellido), "El apellido debería ser válido");
    }

    @Test
    public void testValidarApellido_CasoValido4() {
        String apellido = "López"; // El apellido cumple con las restricciones
        assertTrue(validarApellido(apellido), "El apellido debería ser válido");
    }

    @Test
    public void testValidarApellido_CasoValido5() {
        String apellido = "Ramírez"; // El apellido cumple con las restricciones
        assertTrue(validarApellido(apellido), "El apellido debería ser válido");
    }

    @Test
    public void testValidarApellido_CasoInvalido_Longitud() {
        String apellido = "Aa"; // Apellido demasiado corto
        assertFalse(validarApellido(apellido), "La apellido es inválido");
    }

    @Test
    public void testValidarApellido_CasoValido_LongitudMinima() {
        String apellido = "Díaz"; // El apellido tiene la longitud mínima permitida
        assertTrue(validarApellido(apellido), "La apellido es válido");
    }

    @Test
    public void testValidarApellido_CasoInvalido_Numeros() {
        String apellido = "123"; // El apellido contiene numeros
        assertFalse(validarApellido(apellido), "El apellido contiene caracteres no permitidos");
    }

    @Test
    public void testValidarApellido_CasoInvalido_TextoYCaracteres() {
        String apellido = "Juan_123"; // El apellido contiene caracteres no permitidos
        assertFalse(validarApellido(apellido), "El apellido contiene caracteres no permitidos");
    }

    @Test
    public void testValidarApellido_CasoInvalido_Vacio() {
        String apellido = ""; // Apellido vacío
        assertFalse(validarApellido(apellido), "El apellido no debería estar vacío");
    }

    @Test
    public void testValidarApellido_CasoInvalido_Null() {
        String apellido = null; // Apellido es null
        assertFalse(validarApellido(apellido), "El apellido no debería ser null");
    }

    @Test
    public void testValidarApellido_CasoInvalido_CaracteresEspeciales() {
        String apellido = "Contreras#"; // El apellido contiene caracteres especiales
        assertFalse(validarApellido(apellido), "El apellido no debería contener caracteres especiales");
    }

    @Test
    public void testValidarApellido_CasoValido_LargoMaximo() {
        String apellido = "Ballesteros"; // Apellido con longitud máxima
        assertTrue(validarApellido(apellido), "El apellido debería ser válido");
    }

    @Test
    public void testValidarApellido_CasoInvalido_LargoExcedido() {
        String apellido = "Ballesteross"; // Apellido con longitud superior al máximo permitido
        assertFalse(validarApellido(apellido), "La apellido del apellido excede el límite");
    }

    @Test
    public void testValidarApellido_CasoInvalido_ConEspacios() {
        String apellido = "Díaz Flores"; // Apellido con espacios
        assertFalse(validarApellido(apellido), "El apellido lleva espacios");
    }
}
