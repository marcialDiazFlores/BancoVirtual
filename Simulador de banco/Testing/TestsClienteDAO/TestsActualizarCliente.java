package TestsClienteDAO;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import DAO.ClienteDAO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestsActualizarCliente {

    private ClienteDAO clienteDAO;

    @BeforeMethod
    public void setUp() {
        clienteDAO = new ClienteDAO();
    }

    @Test
    public void testActualizarCliente_CasoExistente() throws SQLException {
        // Actualiza el cliente con el RUT 12.345.678-9
        boolean resultado = clienteDAO.actualizarCliente("NuevoNombre", "NuevoApellido", 35, "nuevo.email@example.com", "+56998765432", "12.345.678-9");

        // Verifica que la actualización fue exitosa
        assertTrue(resultado);
    }

    @Test
    public void testActualizarCliente_CasoExistente2() throws SQLException {
        // Actualiza el cliente con el RUT 14.567.890-1
        boolean resultado = clienteDAO.actualizarCliente("NuevoNombre", "NuevoApellido", 35, "nuevo.email@example.com", "+56998765432", "14.567.890-1");

        // Verifica que la actualización fue exitosa
        assertTrue(resultado);
    }

    @Test
    public void testActualizarCliente_CasoExistente3() throws SQLException {
        // Actualiza el cliente con el RUT 11.887.234-5
        boolean resultado = clienteDAO.actualizarCliente("NuevoNombre", "NuevoApellido", 35, "nuevo.email@example.com", "+56998765432", "11.887.234-5");

        // Verifica que la actualización fue exitosa
        assertTrue(resultado);
    }

    @Test
    public void testActualizarCliente_CasoNoExistente() throws SQLException {
        // Intenta actualizar un cliente con un RUT inexistente
        boolean resultado = clienteDAO.actualizarCliente("NuevoNombre", "NuevoApellido", 35, "nuevo.email@example.com", "+56998765432", "99.999.999-9");

        // Verifica que la actualización no fue exitosa
        assertFalse(resultado);
    }

    @Test
    public void testActualizarCliente_CasoRutInvalido() throws SQLException {
        // Intenta actualizar un cliente con un RUT inválido
        boolean resultado = clienteDAO.actualizarCliente("NuevoNombre", "NuevoApellido", 35, "nuevo.email@example.com", "+56998765432", "rutInvalido");

        // Verifica que la actualización no fue exitosa
        assertFalse(resultado);
    }

}
