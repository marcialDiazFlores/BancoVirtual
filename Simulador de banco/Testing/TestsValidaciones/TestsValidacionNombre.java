package TestsValidaciones;

import org.testng.annotations.Test;

import static Vista.Validaciones.validarNombre;
import static org.testng.Assert.*;

public class TestsValidacionNombre {

    @Test
    public void testValidarNombre_CasoValido() {
        String nombre = "Juan"; // El nombre cumple con las restricciones
        assertTrue(validarNombre(nombre), "El nombre debería ser válido");
    }

    @Test
    public void testValidarNombre_CasoValido2() {
        String nombre = "Isabella"; // El nombre cumple con las restricciones
        assertTrue(validarNombre(nombre), "El nombre debería ser válido");
    }

    @Test
    public void testValidarNombre_CasoValido3() {
        String nombre = "Sebastian"; // El nombre cumple con las restricciones
        assertTrue(validarNombre(nombre), "El nombre debería ser válido");
    }

    @Test
    public void testValidarNombre_CasoValido4() {
        String nombre = "Camila"; // El nombre cumple con las restricciones
        assertTrue(validarNombre(nombre), "El nombre debería ser válido");
    }

    @Test
    public void testValidarNombre_CasoValido5() {
        String nombre = "Ethan"; // El nombre cumple con las restricciones
        assertTrue(validarNombre(nombre), "El nombre debería ser válido");
    }

    @Test
    public void testValidarNombre_CasoInvalido_Longitud() {
        String nombre = "Aa"; // Nombre demasiado corto
        assertFalse(validarNombre(nombre), "La longitud del nombre es inválida");
    }

    @Test
    public void testValidarNombre_CasoValido_LongitudMinima() {
        String nombre = "Luz"; // El nombre tiene la longitud mínima permitida
        assertTrue(validarNombre(nombre), "La longitud del nombre es válida");
    }

    @Test
    public void testValidarNombre_CasoInvalido_Numeros() {
        String nombre = "123"; // El nombre contiene numeros
        assertFalse(validarNombre(nombre), "El nombre contiene caracteres no permitidos");
    }

    @Test
    public void testValidarNombre_CasoInvalido_TextoYCaracteres() {
        String nombre = "Juan_123"; // El nombre contiene caracteres no permitidos
        assertFalse(validarNombre(nombre), "El nombre contiene caracteres no permitidos");
    }

    @Test
    public void testValidarNombre_CasoInvalido_Vacio() {
        String nombre = ""; // Nombre vacío
        assertFalse(validarNombre(nombre), "El nombre no debería estar vacío");
    }

    @Test
    public void testValidarNombre_CasoInvalido_Null() {
        String nombre = null; // Nombre es null
        assertFalse(validarNombre(nombre), "El nombre no debería ser null");
    }

    @Test
    public void testValidarNombre_CasoInvalido_CaracteresEspeciales() {
        String nombre = "Maria#"; // El nombre contiene caracteres especiales
        assertFalse(validarNombre(nombre), "El nombre no debería contener caracteres especiales");
    }

    @Test
    public void testValidarNombre_CasoValido_LargoMaximo() {
        String nombre = "Michelangelo"; // Nombre con longitud máxima
        assertTrue(validarNombre(nombre), "El nombre debería ser válido");
    }

    @Test
    public void testValidarNombre_CasoInvalido_LargoExcedido() {
        String nombre = "Michelangeloo"; // Nombre con longitud superior al máximo permitido
        assertFalse(validarNombre(nombre), "La longitud del nombre excede el límite");
    }

    @Test
    public void testValidarNombre_CasoInvalido_ConEspacios() {
        String nombre = "Juan Carlos"; // Nombre con espacios
        assertFalse(validarNombre(nombre), "El nombre lleva espacios");
    }
}
