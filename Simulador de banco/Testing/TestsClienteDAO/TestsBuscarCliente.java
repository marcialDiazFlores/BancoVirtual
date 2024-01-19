package TestsClienteDAO;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import DAO.ClienteDAO;
import Modelo.Cliente;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestsBuscarCliente {

    private ClienteDAO clienteDAO;

    @BeforeMethod
    public void setUp() {
        clienteDAO = new ClienteDAO();
    }

    @Test
    public void testBuscarCliente_CasoExistente1() throws SQLException {
        // Busca el cliente con el RUT 15.276.984-8
        Cliente cliente = clienteDAO.buscarCliente("15.276.984-8");

        // Verifica que el nombre sea "Maria"
        assertEquals(cliente.getNombre(), "Maria");
    }

    @Test
    public void testBuscarCliente_CasoExistente2() throws SQLException {
        // Busca el cliente con el RUT 14.567.890-1
        Cliente cliente = clienteDAO.buscarCliente("14.567.890-1");

        // Verifica que la edad sea 42
        assertEquals(cliente.getEdad(), 42);
    }

    @Test
    public void testBuscarCliente_CasoExistente3() throws SQLException {
        // Busca el cliente con el RUT 11.887.234-5
        Cliente cliente = clienteDAO.buscarCliente("11.887.234-5");

        // Verifica que el email sea "javier.diaz@yahoo.com"
        assertEquals(cliente.getEmail(), "javier.diaz@yahoo.com");
    }

    @Test
    public void testBuscarCliente_CasoExistente4() throws SQLException {
        // Busca el cliente con el RUT 17.890.123-4
        Cliente cliente = clienteDAO.buscarCliente("17.890.123-4");

        // Verifica que el teléfono sea "+56923456789"
        assertEquals(cliente.getFono(), "+56923456789");
    }

    @Test
    public void testBuscarCliente_CasoNoExistente() throws SQLException {
        // Busca un cliente con un RUT inexistente
        Cliente cliente = clienteDAO.buscarCliente("99.999.999-9");

        // Verifica que el cliente sea null
        assertNull(cliente);
    }

    @Test
    public void testBuscarCliente_CasoRutInvalido() throws SQLException {
        // Busca un cliente con un RUT inválido
        Cliente cliente = clienteDAO.buscarCliente("rutInvalido");

        // Verifica que el cliente sea null
        assertNull(cliente);
    }
}
