package TestsClienteDAO;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import DAO.ClienteDAO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestsEliminarCliente {

    private ClienteDAO clienteDAO;

    @BeforeMethod
    public void setUp() {
        clienteDAO = new ClienteDAO();
    }

    @Test
    public void testEliminarCliente_CasoExistente() throws SQLException {
        // Elimina un cliente con un ID existente en la base de datos
        boolean resultado = clienteDAO.eliminarCliente(1);

        // Verifica que la eliminación fue exitosa
        assertTrue(resultado);
    }

    @Test
    public void testEliminarCliente_CasoExistente2() throws SQLException {
        // Elimina un cliente con un ID existente en la base de datos
        boolean resultado = clienteDAO.eliminarCliente(3);

        // Verifica que la eliminación fue exitosa
        assertTrue(resultado);
    }

    @Test
    public void testEliminarCliente_CasoExistente3() throws SQLException {
        // Elimina un cliente con un ID existente en la base de datos
        boolean resultado = clienteDAO.eliminarCliente(5);

        // Verifica que la eliminación fue exitosa
        assertTrue(resultado);
    }

    @Test
    public void testEliminarCliente_CasoNoExistente() throws SQLException {
        // Intenta eliminar un cliente con un ID inexistente en la base de datos
        boolean resultado = clienteDAO.eliminarCliente(999);

        // Verifica que la eliminación no fue exitosa
        assertFalse(resultado);
    }

    @Test
    public void testEliminarCliente_CasoNoExistente2() throws SQLException {
        // Intenta eliminar un cliente con un ID inexistente en la base de datos
        boolean resultado = clienteDAO.eliminarCliente(5);

        // Verifica que la eliminación no fue exitosa
        assertFalse(resultado);
    }

    @Test
    public void testEliminarCliente_CasoIDInvalido() throws SQLException {
        // Intenta eliminar un cliente con un ID inválido (por ejemplo, ID negativo)
        boolean resultado = clienteDAO.eliminarCliente(-1);

        // Verifica que la eliminación no fue exitosa
        assertFalse(resultado);
    }
}
