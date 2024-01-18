package TestsValidaciones;

import org.testng.annotations.Test;

import static Vista.Validaciones.validarEdad;
import static org.testng.Assert.*;

public class TestsValidacionEdad {

    @Test
    public void testValidarEdad_CasoValido() {
        int edad = 25; // La edad cumple con las restricciones
        assertTrue(validarEdad(edad), "La edad debería ser válida");
    }

    @Test
    public void testValidarEdad_CasoValido2() {
        int edad = 50; // La edad cumple con las restricciones
        assertTrue(validarEdad(edad), "La edad debería ser válida");
    }

    @Test
    public void testValidarEdad_CasoValido3() {
        int edad = 105; // La edad cumple con las restricciones
        assertTrue(validarEdad(edad), "La edad debería ser válida");
    }

    @Test
    public void testValidarEdad_CasoValido4() {
        int edad = 18; // La edad cumple con las restricciones (límite inferior)
        assertTrue(validarEdad(edad), "La edad debería ser válida");
    }

    @Test
    public void testValidarEdad_CasoValido5() {
        int edad = 60; // La edad cumple con las restricciones
        assertTrue(validarEdad(edad), "La edad debería ser válida");
    }

    @Test
    public void testValidarEdad_CasoValido6() {
        int edad = 80; // La edad cumple con las restricciones
        assertTrue(validarEdad(edad), "La edad debería ser válida");
    }

    @Test
    public void testValidarEdad_CasoInvalido_MenorQue18() {
        int edad = 16; // Edad menor que 18
        assertFalse(validarEdad(edad), "La edad es inválida");
    }

    @Test
    public void testValidarEdad_CasoInvalido_MayorQue105() {
        int edad = 110; // Edad mayor que 105
        assertFalse(validarEdad(edad), "La edad es inválida");
    }

    @Test
    public void testValidarEdad_CasoInvalido_Negativa() {
        int edad = -5; // Edad negativa
        assertFalse(validarEdad(edad), "La edad es inválida");
    }
}