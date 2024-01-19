package TestsClienteDAO;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import DAO.ClienteDAO;

import java.sql.SQLException;

public class TestsVerificarRutExistente {

    private ClienteDAO clienteDAO;

    @BeforeMethod
    public void setUp() {
        clienteDAO = new ClienteDAO();
    }

    @Test
    public void testVerificarRutExistente_CasoExistente() throws SQLException {
        String rutExistente = "19.524.734-k";
        boolean resultado = clienteDAO.verificarRutExistente(rutExistente);
        assertTrue(resultado, "El RUT debería existir en la base de datos");
    }

    @Test
    public void testVerificarRutExistente_CasoExistente2() throws SQLException {
        String rutExistente = "11.887.234-5";
        boolean resultado = clienteDAO.verificarRutExistente(rutExistente);
        assertTrue(resultado, "El RUT debería existir en la base de datos");
    }

    @Test
    public void testVerificarRutExistente_CasoExistente3() throws SQLException {
        String rutExistente = "19.960.607-7";
        boolean resultado = clienteDAO.verificarRutExistente(rutExistente);
        assertTrue(resultado, "El RUT debería existir en la base de datos");
    }

    @Test
    public void testVerificarRutExistente_CasoNoExistente() throws SQLException {
        String rutNoExistente = "99.999.999-9"; // Rut inexistente
        boolean resultado = clienteDAO.verificarRutExistente(rutNoExistente);
        assertFalse(resultado, "El RUT no debería existir en la base de datos");
    }

    @Test
    public void testVerificarRutExistente_CasoLetras() throws SQLException {
        String rutNoExistente = "99.99asd9.999asf-9"; // Rut con letras
        boolean resultado = clienteDAO.verificarRutExistente(rutNoExistente);
        assertFalse(resultado, "El RUT no debería existir en la base de datos");
    }

    @Test
    public void testVerificarRutExistente_CasoRutNulo() throws SQLException {
        String rutNulo = null;
        boolean resultado = clienteDAO.verificarRutExistente(rutNulo);
        assertFalse(resultado, "El RUT nulo no debería existir en la base de datos");
    }

    @Test
    public void testVerificarRutExistente_CasoErrorSQLException() {
        // Proporciona un rut que cause una excepción SQL (por ejemplo, "rutIncorrecto")
        String rutErrorSQL = "rutIncorrecto";
        try {
            boolean resultado = clienteDAO.verificarRutExistente(rutErrorSQL);
            assertFalse(resultado, "Debería haber una excepción SQLException");
        } catch (SQLException e) {
            // Si llegamos aquí, la excepción se manejó correctamente
            assertTrue(true);
        }
    }
}
