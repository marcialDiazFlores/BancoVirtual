package Vista;
import Controlador.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static Vista.BancoVirtual.*;

public class InterfazBancoVirtual extends JFrame {
    private ControladorClientes controladorClientes;
    private ControladorCuentasDeAhorro controladorCuentasDeAhorro;
    private ControladorCuentasCorrientes controladorCuentasCorrientes;
    private ControladorAdministradores controladorAdministradores;

    public InterfazBancoVirtual() {
        // Ventana de Login
        JFrame loginFrame = new JFrame("Bienvenido al BancoVirtual - Módulo de Administración");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(500, 350);
        loginFrame.setLayout(new GridLayout(5, 1));
        loginFrame.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Bienvenido al BancoVirtual");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel subtitleLabel = new JLabel("Módulo de administración de la base de datos");
        subtitleLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JLabel rutLabel = new JLabel("RUT:");
        JTextField rutField = new JTextField();
        JLabel passwordLabel = new JLabel("Contraseña:");
        JPasswordField passwordField = new JPasswordField();

        loginPanel.add(rutLabel);
        loginPanel.add(rutField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);

        JButton loginButton = new JButton("Entrar");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se verifican las credenciales y se abre la ventana principal si son correctas
                if (validarCredenciales(rutField.getText(), String.valueOf(passwordField.getPassword()))) {
                    mostrarMensajeAccesoExitoso();
                    loginFrame.dispose(); // Cerrar la ventana de login
                    abrirVentanaPrincipal();
                }
            }
        });

        loginFrame.add(titleLabel);
        loginFrame.add(subtitleLabel);
        loginFrame.add(loginPanel);
        loginFrame.add(loginButton);
        loginFrame.setVisible(true);
    }

    private void abrirVentanaPrincipal() {
        setTitle("Banco Virtual");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnGestionClientes = new JButton("Gestión de clientes");
        JButton btnGestionCuentasAhorro = new JButton("Gestión de cuentas de ahorro");
        JButton btnGestionCuentasCorrientes = new JButton("Gestión de cuentas corrientes");
        JButton btnSalir = new JButton("Salir");

        getContentPane().setLayout(new FlowLayout());
        getContentPane().removeAll();
        getContentPane().add(btnGestionClientes);
        getContentPane().add(btnGestionCuentasAhorro);
        getContentPane().add(btnGestionCuentasCorrientes);
        getContentPane().add(btnSalir);

        // Configurar el tamaño y hacer visible la ventana
        setSize(300, 150);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);

        btnGestionClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionClientes();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Preguntar al usuario si está seguro
                Object[] options = {"Sí", "No"};
                int respuesta = JOptionPane.showOptionDialog(InterfazBancoVirtual.this,
                        "¿Estás seguro?", "Confirmación", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                if (respuesta == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private void gestionClientes() {
        getContentPane().removeAll(); // Limpiar el contenido actual

        setTitle("Gestión de clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controladorClientes = new ControladorClientes();

        JPanel gestionClientesPanel = new JPanel(new FlowLayout());

        JButton btnIngresarCliente = new JButton("Ingresar cliente");
        JButton btnListaDeClientes = new JButton("Lista de clientes");
        JButton btnActualizarCliente = new JButton("Actualizar datos de un cliente");
        JButton btnEliminarCliente = new JButton("Eliminar cliente");
        JButton btnBuscarCliente = new JButton("Buscar cliente por RUT");
        JButton btnVolver = new JButton("Volver");

        gestionClientesPanel.add(btnIngresarCliente);
        gestionClientesPanel.add(btnListaDeClientes);
        gestionClientesPanel.add(btnActualizarCliente);
        gestionClientesPanel.add(btnEliminarCliente);
        gestionClientesPanel.add(btnBuscarCliente);
        gestionClientesPanel.add(btnVolver);

        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout
        getContentPane().add(gestionClientesPanel, BorderLayout.CENTER);

        // Configurar el tamaño y hacer visible la ventana
        setSize(300, 175);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);

        btnIngresarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingresarCliente();
            }
        });

        btnListaDeClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaDeClientes();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaPrincipal();
            }
        });
    }

    private void ingresarCliente() {
        setTitle("Ingresar cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel ingresarClientePanel = new JPanel(new GridLayout(7, 2));

        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField();

        JLabel apellidoLabel = new JLabel("Apellido:");
        JTextField apellidoField = new JTextField();

        JLabel edadLabel = new JLabel("Edad:");
        SpinnerModel edadModel = new SpinnerNumberModel(18, 1, 110, 1); // Rango de edad de 1 a 120, inicio en 18
        JSpinner edadSpinner = new JSpinner(edadModel);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel rutLabel = new JLabel("RUT:");
        JTextField rutField = new JTextField();

        JLabel fonoLabel = new JLabel("Teléfono:");
        JTextField fonoField = new JTextField();

        JButton btnIngresarCliente = new JButton("Ingresar cliente");
        JButton btnVolver = new JButton("Volver");

        ingresarClientePanel.add(nombreLabel);
        ingresarClientePanel.add(nombreField);
        ingresarClientePanel.add(apellidoLabel);
        ingresarClientePanel.add(apellidoField);
        ingresarClientePanel.add(edadLabel);
        ingresarClientePanel.add(edadSpinner);
        ingresarClientePanel.add(emailLabel);
        ingresarClientePanel.add(emailField);
        ingresarClientePanel.add(rutLabel);
        ingresarClientePanel.add(rutField);
        ingresarClientePanel.add(fonoLabel);
        ingresarClientePanel.add(fonoField);
        ingresarClientePanel.add(btnIngresarCliente);
        ingresarClientePanel.add(btnVolver);

        getContentPane().removeAll(); // Limpiar el contenido actual
        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout
        getContentPane().add(ingresarClientePanel, BorderLayout.CENTER);

        // Configurar el tamaño y hacer visible la ventana
        setSize(500, 350);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);

        btnIngresarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarDatosIngresoCliente(nombreField.getText(), apellidoField.getText(), (Integer) edadSpinner.getValue(), emailField.getText(), rutField.getText(), fonoField.getText())){
                    if(controladorClientes.crearCliente(nombreField.getText(), apellidoField.getText(), (Integer) edadSpinner.getValue(), emailField.getText(), rutField.getText(), fonoField.getText())){
                        JOptionPane.showMessageDialog(ingresarClientePanel, "Cliente ingresado con éxito", "Inserción exitosa", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(ingresarClientePanel, "No se pudo agregar al cliente", "Inserción fallida", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionClientes();
            }
        });
    }

    private void listaDeClientes() {
        getContentPane().removeAll();
        setTitle("Lista de clientes del banco:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 350);

        String[] columnas = {"ID", "Nombre", "Apellido", "Edad", "Email", "RUT", "Teléfono"};

        // Tabla de clientes

        DefaultTableModel tableModel = new DefaultTableModel(null, columnas);

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        Object[][] clientes = controladorClientes.obtenerDatosClientes();

        if (clientes.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay clientes en la base de datos", "Base de datos vacía", JOptionPane.ERROR_MESSAGE);
            gestionClientes();
        }

        else {
            for (Object[] cliente : clientes) {
                tableModel.addRow(cliente);
            }

            // Panel para el botón volver
            JPanel buttonPanel = new JPanel();
            JButton btnVolver = new JButton("Volver");

            buttonPanel.add(btnVolver);

            // Agregar el panel de botones a la parte inferior de la ventana
            getContentPane().add(buttonPanel, BorderLayout.SOUTH);

            btnVolver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gestionClientes();
                }
            });

            setLocationRelativeTo(null); // Centrar en la pantalla
            setVisible(true);
        }
    }

    // Retorna true si las credenciales son válidas, de lo contrario, false.
    private boolean validarCredenciales(String rut, String contrasena) {

        if(!validarRut(rut)){
            mostrarMensajeErrorDeValidacion("RUT inválido");
            return false;
        }

        if(!validarContrasena(contrasena)){
            mostrarMensajeErrorDeValidacion("Contraseña inválida");
            return false;
        }

        controladorAdministradores = new ControladorAdministradores();

        if(!controladorAdministradores.login(rut, contrasena)) {
            mostrarMensajeErrorDeValidacion("Las credenciales no se encuentran en la base de datos");
            mostrarMensajeErrorDeValidacion("Acceso denegado");
            return false;
        }

        return true;
    }

    // Valida datos de entrada para ingresar clientes
    private boolean validarDatosIngresoCliente(String nombre, String apellido, int edad, String email, String rut, String fono) {
        if(!validarNombre(nombre)){
            mostrarMensajeErrorDeValidacion("Nombre inválido");
            return false;
        }

        if(!validarApellido(apellido)){
            mostrarMensajeErrorDeValidacion("Apellido inválido");
            return false;
        }

        if(!validarEdad(edad)){
            mostrarMensajeErrorDeValidacion("Edad inválida");
            return false;
        }

        if(!validarEmail(email)){
            mostrarMensajeErrorDeValidacion("Email inválido");
            return false;
        }

        if(!validarRut(rut)){
            mostrarMensajeErrorDeValidacion("RUT inválido");
            return false;
        }

        if(!validarTelefono(fono)){
            mostrarMensajeErrorDeValidacion("Teléfono inválido");
            return false;
        }

        return true;
    }

    private void mostrarMensajeErrorDeValidacion(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Validación", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarMensajeAccesoExitoso() {
        JOptionPane.showMessageDialog(this, "Sesión iniciada exitosamente", "Acceso concedido", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazBancoVirtual();
            }
        });
    }
}
