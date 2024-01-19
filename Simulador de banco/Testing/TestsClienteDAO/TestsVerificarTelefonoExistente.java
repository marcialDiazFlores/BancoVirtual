
package TestsClienteDAO;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import DAO.ClienteDAO;

import java.sql.SQLException;

public class TestsVerificarTelefonoExistente {

    private ClienteDAO clienteDAO;

    @BeforeMethod
    public void setUp() {
        clienteDAO = new ClienteDAO();
    }

    @Test
    public void testVerificarTelefonoExistente_CasoExistente() throws SQLException {
        String telefonoExistente = "+56998765432";
        boolean resultado = clienteDAO.verificarTelefonoExistente(telefonoExistente);
        assertTrue(resultado, "El teléfono debería existir en la base de datos");
    }

    @Test
    public void testVerificarTelefonoExistente_CasoExistente2() throws SQLException {
        String telefonoExistente = "+56987654321";
        boolean resultado = clienteDAO.verificarTelefonoExistente(telefonoExistente);
        assertTrue(resultado, "El teléfono debería existir en la base de datos");
    }

    @Test
    public void testVerificarTelefonoExistente_CasoExistente3() throws SQLException {
        String telefonoExistente = "+56998765432";
        boolean resultado = clienteDAO.verificarTelefonoExistente(telefonoExistente);
        assertTrue(resultado, "El teléfono debería existir en la base de datos");
    }

    @Test
    public void testVerificarTelefonoExistente_CasoNoExistente() throws SQLException {
        String telefonoNoExistente = "+56900000000"; // Teléfono inexistente
        boolean resultado = clienteDAO.verificarTelefonoExistente(telefonoNoExistente);
        assertFalse(resultado, "El teléfono no debería existir en la base de datos");
    }

    @Test
    public void testVerificarTelefonoExistente_CasoTelefonoInvalido() throws SQLException {
        String telefonoInvalido = "+56 9 1234 5678"; // Teléfono con formato inválido
        boolean resultado = clienteDAO.verificarTelefonoExistente(telefonoInvalido);
        assertFalse(resultado, "El teléfono no debería existir en la base de datos");
    }

    @Test
    public void testVerificarTelefonoExistente_CasoTelefonoNulo() throws SQLException {
        String telefonoNulo = null;
        boolean resultado = clienteDAO.verificarTelefonoExistente(telefonoNulo);
        assertFalse(resultado, "El teléfono nulo no debería existir en la base de datos");
    }
}
