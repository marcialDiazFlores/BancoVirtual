package TestsClienteDAO;
import static org.testng.Assert.*;
import DAO.ClienteDAO;
import Modelo.Cliente;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestsAgregarCliente {
    private ClienteDAO clienteDAO;

    @BeforeMethod
    public void setUp() {
        clienteDAO = new ClienteDAO();
    }

    @Test
    public void testAgregarCliente_ClienteNoExistente() throws SQLException {
        // Crea un objeto Cliente para agregar
        Cliente cliente = new Cliente("Juan", "Pérez", 30, "juan.perez@example.com", "12.345.678-9", "+56987664521");

        // Intenta agregar el cliente
        boolean resultado = clienteDAO.agregarCliente(cliente);

        // Verifica que la operación fue exitosa
        assertTrue(resultado);
    }

    @Test
    public void testAgregarCliente_ClienteNoExistente2() throws SQLException {
        // Crea un objeto Cliente para agregar
        Cliente cliente = new Cliente("Marcial", "Díaz", 27, "marcial.diaz@acl.cl", "19.524.734-k", "+56978030199");

        // Intenta agregar el cliente
        boolean resultado = clienteDAO.agregarCliente(cliente);

        // Verifica que la operación fue exitosa
        assertTrue(resultado);
    }

    @Test
    public void testAgregarCliente_ClienteNoExistente3() throws SQLException {
        // Crea un objeto Cliente para agregar
        Cliente cliente = new Cliente("Valentina", "Díaz", 25, "vale.diaz@gmail.cl", "19.960.607-7", "+56978052399");

        // Intenta agregar el cliente
        boolean resultado = clienteDAO.agregarCliente(cliente);

        // Verifica que la operación fue exitosa
        assertTrue(resultado);
    }



    @Test
    public void testAgregarCliente_ClienteConDatosNulos() throws SQLException {
        // Crea un objeto Cliente para agregar
        Cliente cliente = new Cliente(null, "Díaz", 25, null, "19.960.607-7", "+56978052399");

        // Intenta agregar el cliente
        boolean resultado = clienteDAO.agregarCliente(cliente);

        // Verifica que la operación fue exitosa
        assertFalse(resultado);
    }

    @Test
    public void testAgregarCliente_ClienteConDatosNulos2() throws SQLException {
        // Crea un objeto Cliente para agregar
        Cliente cliente = new Cliente("Valentina", "Díaz", 25, null, "19.960.607-7", null);

        // Intenta agregar el cliente
        boolean resultado = clienteDAO.agregarCliente(cliente);

        // Verifica que la operación fue exitosa
        assertFalse(resultado);
    }
}
