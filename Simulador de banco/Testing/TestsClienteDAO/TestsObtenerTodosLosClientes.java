package TestsClienteDAO;

import Modelo.Cliente;
import org.testng.annotations.BeforeMethod;

import java.sql.SQLException;
import java.util.List;

import DAO.ClienteDAO;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TestsObtenerTodosLosClientes {

    private ClienteDAO clienteDAO;

    @BeforeMethod
    public void setUp() {
        clienteDAO = new ClienteDAO();
    }

    @Test
    public void testObtenerTodosLosClientes_NoNulos() throws SQLException {
        // Ejecuta el método para obtener todos los clientes
        List<Cliente> clientes = clienteDAO.obtenerTodosLosClientes();

        // Verifica que la lista no sea nula
        assertNotNull(clientes);
    }

    @Test
    public void testObtenerTodosLosClientes_CantidadCorrecta() throws SQLException {
        // Ejecuta el método para obtener todos los clientes
        List<Cliente> clientes = clienteDAO.obtenerTodosLosClientes();

        // Verifica que la cantidad de clientes sea la esperada
        assertEquals(5, clientes.size()); // Ajusta según el número de clientes en tu base de datos
    }

    @Test
    public void testObtenerTodosLosClientes_DatosCorrectos() throws SQLException {
        // Ejecuta el método para obtener todos los clientes
        List<Cliente> clientes = clienteDAO.obtenerTodosLosClientes();

        // Verifica que los datos de los clientes sean correctos
        for (Cliente cliente : clientes) {
            assertNotNull(cliente.getId());
            assertNotNull(cliente.getNombre());
            assertNotNull(cliente.getApellido());
            assertNotNull(cliente.getEdad());
            assertNotNull(cliente.getEmail());
            assertNotNull(cliente.getRut());
            assertNotNull(cliente.getFono());
        }
    }

    @Test
    public void testObtenerTodosLosClientes_ApellidoCuartoCliente() throws SQLException {
        // Obtiene la lista de todos los clientes
        List<Cliente> clientes = clienteDAO.obtenerTodosLosClientes();

        // Verifica que el apellido del cuarto cliente sea "Díaz"
        assertEquals(clientes.get(3).getApellido(), "Díaz");
    }

    @Test
    public void testObtenerTodosLosClientes_EdadSegundoCliente() throws SQLException {
        // Obtiene la lista de todos los clientes
        List<Cliente> clientes = clienteDAO.obtenerTodosLosClientes();

        // Verifica que la edad del segundo cliente sea 35
        assertEquals(clientes.get(1).getEdad(), 35);
    }

    @Test
    public void testObtenerTodosLosClientes_TelefonoCuartoCliente() throws SQLException {
        // Obtiene la lista de todos los clientes
        List<Cliente> clientes = clienteDAO.obtenerTodosLosClientes();

        // Verifica que el teléfono del cuarto cliente sea "+56978030199"
        assertEquals(clientes.get(3).getFono(), "+56978030199");
    }
}
