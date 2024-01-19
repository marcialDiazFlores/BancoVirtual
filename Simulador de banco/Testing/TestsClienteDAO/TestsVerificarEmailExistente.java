package TestsClienteDAO;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import DAO.ClienteDAO;

import java.sql.SQLException;

public class TestsVerificarEmailExistente {

    private ClienteDAO clienteDAO;

    @BeforeMethod
    public void setUp() {
        clienteDAO = new ClienteDAO();
    }

    @Test
    public void testVerificarEmailExistente_CasoExistente() throws SQLException {
        String emailExistente = "marcial.diaz@acl.cl";
        boolean resultado = clienteDAO.verificarEmailExistente(emailExistente);
        assertTrue(resultado, "El email debería existir en la base de datos");
    }

    @Test
    public void testVerificarEmailExistente_CasoExistente2() throws SQLException {
        String emailExistente = "vale.diaz@gmail.cl";
        boolean resultado = clienteDAO.verificarEmailExistente(emailExistente);
        assertTrue(resultado, "El email debería existir en la base de datos");
    }

    @Test
    public void testVerificarEmailExistente_CasoExistente3() throws SQLException {
        String emailExistente = "javier.diaz@yahoo.com";
        boolean resultado = clienteDAO.verificarEmailExistente(emailExistente);
        assertTrue(resultado, "El email debería existir en la base de datos");
    }

    @Test
    public void testVerificarEmailExistente_CasoNoExistente() throws SQLException {
        String emailNoExistente = "correo.inexistente@example.com"; // Email inexistente
        boolean resultado = clienteDAO.verificarEmailExistente(emailNoExistente);
        assertFalse(resultado, "El email no debería existir en la base de datos");
    }

    @Test
    public void testVerificarEmailExistente_CasoNoExistente2() throws SQLException {
        String emailInvalido = "correoInvalido.com";
        boolean resultado = clienteDAO.verificarEmailExistente(emailInvalido);
        assertFalse(resultado, "El email no debería existir en la base de datos");
    }

    @Test
    public void testVerificarEmailExistente_CasoEmailNulo() throws SQLException {
        String emailNulo = null;
        boolean resultado = clienteDAO.verificarEmailExistente(emailNulo);
        assertFalse(resultado, "El email nulo no debería existir en la base de datos");
    }
}
