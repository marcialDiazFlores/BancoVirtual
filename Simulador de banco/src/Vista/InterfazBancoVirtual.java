package Vista;
import Controlador.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

import static Vista.BancoVirtual.*;

public class InterfazBancoVirtual extends JFrame {
    private ControladorClientes controladorClientes;
    private ControladorCuentasDeAhorro controladorCuentasDeAhorro;
    private ControladorCuentasCorrientes controladorCuentasCorrientes;
    private ControladorAdministradores controladorAdministradores;

    public InterfazBancoVirtual() {
        // Inicializar controladores
        controladorClientes = new ControladorClientes();
        controladorCuentasDeAhorro = new ControladorCuentasDeAhorro();
        controladorCuentasCorrientes = new ControladorCuentasCorrientes();
        controladorAdministradores = new ControladorAdministradores();

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

        btnGestionCuentasAhorro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionCuentasDeAhorro();
            }
        });

        btnGestionCuentasCorrientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionCuentasCorrientes();
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

        btnActualizarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCliente();
            }
        });

        btnEliminarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }
        });

        btnBuscarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
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

        String[] columnas = {"ID", "Nombre", "Apellido", "Edad", "Email", "RUT", "Teléfono", "¿Cuenta de ahorro?", "¿Cuenta corriente?"};

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

            // Botón de actualizado
            JButton btnActualizar = new JButton("Actualizar registros");
            btnActualizar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para actualizar la lista de clientes
                    actualizarListaClientes(tableModel);
                }
            });

            // Panel para el botón volver
            JPanel buttonPanel = new JPanel();
            JButton btnVolver = new JButton("Volver");

            buttonPanel.add(btnActualizar);
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

    // Método para actualizar la lista de clientes
    private void actualizarListaClientes(DefaultTableModel tableModel) {
        // Lógica para obtener y actualizar la lista de clientes
        Object[][] clientes = controladorClientes.obtenerDatosClientes();

        // Limpiar la tabla
        tableModel.setRowCount(0);

        if (clientes.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay clientes en la base de datos", "Base de datos vacía", JOptionPane.ERROR_MESSAGE);
            gestionClientes();
        } else {
            // Agregar los nuevos datos a la tabla
            for (Object[] cliente : clientes) {
                tableModel.addRow(cliente);
            }
        }
    }

    private void actualizarCliente() {
        setTitle("Actualizar datos de un cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buscarClientePanel = new JPanel(new GridLayout(7, 2));

        JLabel rutLabel = new JLabel("RUT del cliente:");
        JTextField rutField = new JTextField();

        JButton btnBuscar = new JButton("Buscar");
        JButton btnVolver = new JButton("Volver");

        buscarClientePanel.add(rutLabel);
        buscarClientePanel.add(rutField);

        buscarClientePanel.add(btnBuscar);
        buscarClientePanel.add(btnVolver);

        getContentPane().removeAll(); // Limpiar el contenido actual
        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout
        getContentPane().add(buscarClientePanel, BorderLayout.CENTER);

        // Configurar el tamaño y hacer visible la ventana
        setSize(350, 250);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarRut(rutField.getText())){
                    Object[] datos = controladorClientes.buscarCliente(rutField.getText());
                    if(datos.length > 0){
                        JOptionPane.showMessageDialog(buscarClientePanel, "Cliente encontrado en la base de datos", "Búsqueda exitosa", JOptionPane.INFORMATION_MESSAGE);
                        mostrarDatosYActualizar(datos, rutField.getText());
                    }
                    else {
                        JOptionPane.showMessageDialog(buscarClientePanel, "No se encontró al cliente en la base de datos", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(buscarClientePanel, "Ingrese un RUT válido", "RUT inválido", JOptionPane.ERROR_MESSAGE);
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

    private void mostrarDatosYActualizar(Object[] datos, String rut) {
        getContentPane().removeAll();
        setTitle("Actualizar cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);

        // Crear un panel para organizar la interfaz
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Etiqueta para el encabezado
        JLabel actualizarLabel = new JLabel("Actualizar cliente");
        actualizarLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(actualizarLabel, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre", "Apellido", "Edad", "Email", "RUT", "Teléfono"};

        // Datos
        DefaultTableModel tableModel = new DefaultTableModel(null, columnas);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        tableModel.addRow(datos);
        setVisible(true); // Asegura que la ventana sea visible
        table.setVisible(true);

        // Panel para los campos de actualización
        JPanel actualizarClientePanel = new JPanel(new GridLayout(6, 2));

        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField();
        addPlaceholder(nombreField, "(Nombre actual: " + ((String) datos[1]) + ")");

        JLabel apellidoLabel = new JLabel("Apellido:");
        JTextField apellidoField = new JTextField();
        addPlaceholder(apellidoField, "(Apellido actual: " + ((String) datos[2]) + ")");

        JLabel edadLabel = new JLabel("Edad:");
        SpinnerModel edadModel = new SpinnerNumberModel(18, 1, 110, 1);
        JSpinner edadSpinner = new JSpinner(edadModel);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        addPlaceholder(emailField, "(Email actual: " + ((String) datos[4]) + ")");

        JLabel fonoLabel = new JLabel("Teléfono:");
        JTextField fonoField = new JTextField();
        addPlaceholder(fonoField, "(Teléfono actual: " + ((String) datos[6]) + ")");

        actualizarClientePanel.add(nombreLabel);
        actualizarClientePanel.add(nombreField);
        actualizarClientePanel.add(apellidoLabel);
        actualizarClientePanel.add(apellidoField);
        actualizarClientePanel.add(edadLabel);
        actualizarClientePanel.add(edadSpinner);
        actualizarClientePanel.add(emailLabel);
        actualizarClientePanel.add(emailField);
        actualizarClientePanel.add(fonoLabel);
        actualizarClientePanel.add(fonoField);

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnVolver = new JButton("Volver");

        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnVolver);

        // Agregar los paneles al panel principal
        mainPanel.add(actualizarClientePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Agregar el panel principal a la ventana
        getContentPane().add(mainPanel);

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarDatosActualizacionCliente(nombreField.getText(), apellidoField.getText(), (Integer) edadSpinner.getValue(), emailField.getText(), fonoField.getText())){
                    if (controladorClientes.actualizarCliente(nombreField.getText(), apellidoField.getText(), (Integer) edadSpinner.getValue(), emailField.getText(), fonoField.getText(), rut)) {
                        JOptionPane.showMessageDialog(getContentPane(), "Los datos del cliente han sido actualizados", "Actualización completada", JOptionPane.INFORMATION_MESSAGE);
                        gestionClientes();
                    }
                    else {
                        JOptionPane.showMessageDialog(getContentPane(), "No se pudieron actualizar los datos del cliente", "Actualización fallida", JOptionPane.ERROR_MESSAGE);
                        gestionClientes();
                    }
                }
                else {
                    gestionClientes();
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionClientes();
            }
        });

        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);
    }

    private void addPlaceholder(JTextField textField, String placeholder) {
        // Crear un color más tenue para el placeholder
        Color placeholderColor = new Color(150, 150, 150);

        // Establecer un nuevo Document para el JTextField
        textField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                // Verificar si el texto actual es el placeholder
                if (textField.getText().equals(placeholder)) {
                    // Eliminar el placeholder al ingresar un nuevo texto
                    super.remove(0, getLength());
                    super.insertString(0, str, a);
                    textField.setForeground(Color.BLACK);  // Restaurar el color del texto
                } else {
                    super.insertString(offs, str, a);
                }
            }
        });

        // Configurar el color del texto y establecer el placeholder
        textField.setForeground(placeholderColor);
        textField.setText(placeholder);
    }


    private void buscarCliente() {
        setTitle("Buscar cliente por RUT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buscarClientePanel = new JPanel(new GridLayout(7, 2));

        JLabel rutLabel = new JLabel("RUT:");
        JTextField rutField = new JTextField();

        JButton btnBuscar = new JButton("Buscar");
        JButton btnVolver = new JButton("Volver");

        buscarClientePanel.add(rutLabel);
        buscarClientePanel.add(rutField);

        buscarClientePanel.add(btnBuscar);
        buscarClientePanel.add(btnVolver);

        getContentPane().removeAll(); // Limpiar el contenido actual
        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout
        getContentPane().add(buscarClientePanel, BorderLayout.CENTER);

        // Configurar el tamaño y hacer visible la ventana
        setSize(350, 250);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarRut(rutField.getText())){
                    Object[] datos = controladorClientes.buscarCliente(rutField.getText());
                    if(datos.length > 0){
                        JOptionPane.showMessageDialog(buscarClientePanel, "Cliente encontrado en la base de datos", "Búsqueda exitosa", JOptionPane.INFORMATION_MESSAGE);
                        mostrarDatosCliente(datos);
                    }
                    else {
                        JOptionPane.showMessageDialog(buscarClientePanel, "No se encontró al cliente en la base de datos", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(buscarClientePanel, "Ingrese un RUT válido", "RUT inválido", JOptionPane.ERROR_MESSAGE);
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

    private void mostrarDatosCliente(Object[] datos) {
        getContentPane().removeAll();
        setTitle("Datos del cliente:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 150);

        String[] columnas = {"ID", "Nombre", "Apellido", "Edad", "Email", "RUT", "Teléfono", "¿Cuenta de ahorro?", "¿Cuenta corriente?"};

        // Datos

        DefaultTableModel tableModel = new DefaultTableModel(null, columnas);

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        tableModel.addRow(datos);

        // Botón de actualizado
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para actualizar la lista de clientes
                mostrarDatosCliente(controladorClientes.buscarCliente((String) datos[5]));
            }
        });

        // Panel para el botón volver
        JPanel buttonPanel = new JPanel();
        JButton btnVolver = new JButton("Volver");

        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnVolver);

        // Agregar el panel de botones a la parte inferior de la ventana
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDatosCliente(datos);
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionClientes();
            }
        });

        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);
    }

    private void eliminarCliente() {
        setTitle("Eliminar cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buscarClientePanel = new JPanel(new GridLayout(7, 2));

        JLabel rutLabel = new JLabel("RUT del cliente:");
        JTextField rutField = new JTextField();

        JButton btnBuscar = new JButton("Buscar");
        JButton btnVolver = new JButton("Volver");

        buscarClientePanel.add(rutLabel);
        buscarClientePanel.add(rutField);

        buscarClientePanel.add(btnBuscar);
        buscarClientePanel.add(btnVolver);

        getContentPane().removeAll(); // Limpiar el contenido actual
        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout
        getContentPane().add(buscarClientePanel, BorderLayout.CENTER);

        // Configurar el tamaño y hacer visible la ventana
        setSize(350, 250);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarRut(rutField.getText())){
                    Object[] datos = controladorClientes.buscarCliente(rutField.getText());
                    if(datos.length > 0){
                        JOptionPane.showMessageDialog(buscarClientePanel, "Cliente encontrado en la base de datos", "Búsqueda exitosa", JOptionPane.INFORMATION_MESSAGE);
                        if(controladorCuentasDeAhorro.eliminarCuentaDeAhorro((Integer) datos[0])){
                            JOptionPane.showMessageDialog(buscarClientePanel, "La cuenta de ahorro del cliente " + datos[1] + " " + datos[2] + "ha sido eliminada", "Eliminación de cuenta exitosa", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(buscarClientePanel, "No se pudo eliminar la cuenta corriente", "Eliminación fallida", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(buscarClientePanel, "No se encontró al cliente en la base de datos", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(buscarClientePanel, "Ingrese un RUT válido", "RUT inválido", JOptionPane.ERROR_MESSAGE);
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

    private void gestionCuentasDeAhorro() {
        getContentPane().removeAll(); // Limpiar el contenido actual

        setTitle("Gestión de cuentas de ahorro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controladorCuentasDeAhorro = new ControladorCuentasDeAhorro();

        JPanel gestionCuentasDeAhorroPanel = new JPanel(new FlowLayout());

        JButton btnIngresarCuentaDeAhorro = new JButton("Ingresar cuenta de ahorro");
        JButton btnListaDeCuentasDeAhorro = new JButton("Lista de cuentas de ahorro");
        JButton btnActualizarCuentaDeAhorro = new JButton("Actualizar cuenta de ahorro");
        JButton btnEliminarCuentaDeAhorro = new JButton("Eliminar cuenta de ahorro");
        JButton btnBuscarCuentaDeAhorro = new JButton("Buscar cuenta de ahorro por RUT");
        JButton btnVolver = new JButton("Volver");

        gestionCuentasDeAhorroPanel.add(btnIngresarCuentaDeAhorro);
        gestionCuentasDeAhorroPanel.add(btnListaDeCuentasDeAhorro);
        gestionCuentasDeAhorroPanel.add(btnActualizarCuentaDeAhorro);
        gestionCuentasDeAhorroPanel.add(btnEliminarCuentaDeAhorro);
        gestionCuentasDeAhorroPanel.add(btnBuscarCuentaDeAhorro);
        gestionCuentasDeAhorroPanel.add(btnVolver);

        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout
        getContentPane().add(gestionCuentasDeAhorroPanel, BorderLayout.CENTER);

        // Configurar el tamaño y hacer visible la ventana
        setSize(300, 250);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);

        btnIngresarCuentaDeAhorro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingresarCuentaDeAhorro();
            }
        });

        btnListaDeCuentasDeAhorro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaDeCuentasDeAhorro();
            }
        });

        btnActualizarCuentaDeAhorro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCuentaDeAhorro();
            }
        });

        btnEliminarCuentaDeAhorro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCuentaDeAhorro();
            }
        });

        btnBuscarCuentaDeAhorro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCuentaDeAhorro();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaPrincipal();
            }
        });
    }

    private void ingresarCuentaDeAhorro() {
        setTitle("Ingresar cuenta de ahorro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel ingresarCuentaDeAhorroPanel = new JPanel(new GridLayout(7, 2));

        JLabel rutLabel = new JLabel("Ingrese RUT del cliente: ");
        JTextField rutField = new JTextField();

        JLabel saldoLabel = new JLabel("Saldo inicial:");
        SpinnerModel saldoModel = new SpinnerNumberModel(1, 1, 10000000, 1);
        JSpinner saldoSpinner = new JSpinner(saldoModel);

        JLabel tasaInteresLabel = new JLabel("Tasa de interés:");
        SpinnerModel tasaInteresModel = new SpinnerNumberModel(1, 1, 20, 0.1);
        JSpinner tasaInteresSpinner = new JSpinner(tasaInteresModel);

        JLabel topeMinimoLabel = new JLabel("Tope mínimo:");
        SpinnerModel topeMinimoModel = new SpinnerNumberModel(200000, 100000, 10000000, 50000);
        JSpinner topeMinimoSpinner = new JSpinner(topeMinimoModel);

        JButton btnIngresarCuentaDeAhorro = new JButton("Ingresar cuenta de ahorro");
        JButton btnVolver = new JButton("Volver");

        ingresarCuentaDeAhorroPanel.add(rutLabel);
        ingresarCuentaDeAhorroPanel.add(rutField);
        ingresarCuentaDeAhorroPanel.add(saldoLabel);
        ingresarCuentaDeAhorroPanel.add(saldoSpinner);
        ingresarCuentaDeAhorroPanel.add(tasaInteresLabel);
        ingresarCuentaDeAhorroPanel.add(tasaInteresSpinner);
        ingresarCuentaDeAhorroPanel.add(topeMinimoLabel);
        ingresarCuentaDeAhorroPanel.add(topeMinimoSpinner);
        ingresarCuentaDeAhorroPanel.add(btnIngresarCuentaDeAhorro);
        ingresarCuentaDeAhorroPanel.add(btnVolver);

        getContentPane().removeAll(); // Limpiar el contenido actual
        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout
        getContentPane().add(ingresarCuentaDeAhorroPanel, BorderLayout.CENTER);

        // Configurar el tamaño y hacer visible la ventana
        setSize(500, 350);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);

        btnIngresarCuentaDeAhorro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rut = rutField.getText();
                int saldo = (Integer) saldoSpinner.getValue();
                double tasaInteres = (Double) tasaInteresSpinner.getValue();
                int topeMinimo = (Integer) saldoSpinner.getValue();
                if(validarDatosIngresoCuentaDeAhorro(rut, saldo, tasaInteres, topeMinimo)){
                    Object[] datos = controladorClientes.buscarCliente(rutField.getText());
                    if(datos.length > 0){
                        if(controladorClientes.hayCuentaAhorro(rutField.getText())) {
                            JOptionPane.showMessageDialog(ingresarCuentaDeAhorroPanel, "El cliente de RUT " + rutField.getText() + " ya tiene una cuenta de ahorro", "Creación de cuenta fallida", JOptionPane.ERROR_MESSAGE);
                            gestionCuentasDeAhorro();
                        }
                        else {
                            int idCliente = controladorClientes.encontrarIdClientePorRUT(rutField.getText());
                            if(controladorCuentasDeAhorro.crearCuentaDeAhorro(idCliente, (Integer) saldoSpinner.getValue(), (Double) tasaInteresSpinner.getValue(), (Integer) topeMinimoSpinner.getValue())) {
                                JOptionPane.showMessageDialog(ingresarCuentaDeAhorroPanel, "Cuenta de ahorro creada exitosamente para el cliente " + datos[1] + " " + datos[2], "Creación de cuenta exitosa", JOptionPane.INFORMATION_MESSAGE);
                                gestionCuentasDeAhorro();
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(ingresarCuentaDeAhorroPanel, "No se encontró al cliente en la base de datos", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                        gestionCuentasDeAhorro();
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

    private void listaDeCuentasDeAhorro() {
        getContentPane().removeAll();
        setTitle("Lista de cuentas de ahorro en el banco:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 350);

        String[] columnas = {"ID de cuenta", "Nombre", "Apellido", "RUT", "Saldo", "Tasa de interés", "Tope mínimo para retiros"};

        // Tabla de clientes

        DefaultTableModel tableModel = new DefaultTableModel(null, columnas);

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        Object[][] cuentas = controladorCuentasDeAhorro.obtenerDatosCuentasDeAhorro();

        if (cuentas.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay cuentas de ahorro registradas en la base de datos", "Base de datos vacía", JOptionPane.ERROR_MESSAGE);
            gestionClientes();
        }

        else {
            for (Object[] cuenta : cuentas) {
                tableModel.addRow(cuenta);
            }

            // Botón de actualizado
            JButton btnActualizar = new JButton("Actualizar registros");
            btnActualizar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para actualizar la lista de clientes
                    actualizarListaCuentasDeAhorro(tableModel);
                }
            });

            // Panel para el botón volver
            JPanel buttonPanel = new JPanel();
            JButton btnVolver = new JButton("Volver");

            buttonPanel.add(btnActualizar);
            buttonPanel.add(btnVolver);

            // Agregar el panel de botones a la parte inferior de la ventana
            getContentPane().add(buttonPanel, BorderLayout.SOUTH);

            btnVolver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gestionCuentasDeAhorro();
                }
            });

            setLocationRelativeTo(null); // Centrar en la pantalla
            setVisible(true);
        }
    }

    private void actualizarCuentaDeAhorro() {
        setTitle("Actualizar cuenta de ahorro de un cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buscarClientePanel = new JPanel(new GridLayout(7, 2));

        JLabel rutLabel = new JLabel("RUT del cliente:");
        JTextField rutField = new JTextField();

        JButton btnBuscar = new JButton("Buscar");
        JButton btnVolver = new JButton("Volver");

        buscarClientePanel.add(rutLabel);
        buscarClientePanel.add(rutField);

        buscarClientePanel.add(btnBuscar);
        buscarClientePanel.add(btnVolver);

        getContentPane().removeAll(); // Limpiar el contenido actual
        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout
        getContentPane().add(buscarClientePanel, BorderLayout.CENTER);

        // Configurar el tamaño y hacer visible la ventana
        setSize(350, 250);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rut = rutField.getText();
                if(validarRut(rut)){
                    Object[] datos = controladorClientes.buscarCliente(rut);
                    if(datos.length > 0){
                        if (!controladorClientes.hayCuentaAhorro(rut)) {
                            JOptionPane.showMessageDialog(buscarClientePanel, "El cliente no tiene cuenta de ahorro para actualizar", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                            gestionCuentasDeAhorro();
                        }
                        else {
                            JOptionPane.showMessageDialog(buscarClientePanel, "Cliente encontrado en la base de datos", "Búsqueda exitosa", JOptionPane.INFORMATION_MESSAGE);
                            ventanaActualizacionCuentaDeAhorro(datos, rut);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(buscarClientePanel, "No se encontró al cliente en la base de datos", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                        gestionCuentasDeAhorro();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(buscarClientePanel, "Ingrese un RUT válido", "RUT inválido", JOptionPane.ERROR_MESSAGE);
                    gestionCuentasDeAhorro();
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionCuentasDeAhorro();
            }
        });
    }

    private void ventanaActualizacionCuentaDeAhorro(Object[] datos, String rut) {
        getContentPane().removeAll();
        setTitle("Actualizar cuenta de ahorro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);

        // Crear un panel para organizar la interfaz
        JPanel ingresarDatosPanel = new JPanel(new BorderLayout());

        // Etiqueta para el encabezado
        JLabel tituloLabel = new JLabel("Actualizar cliente");
        JLabel ingreseDatosLabel = new JLabel("Ingrese los nuevos datos de la cuenta");
        tituloLabel.setHorizontalAlignment(JLabel.CENTER);
        tituloLabel.setHorizontalAlignment(JLabel.CENTER);
        ingreseDatosLabel.setHorizontalAlignment(JLabel.CENTER);
        ingresarDatosPanel.add(tituloLabel, BorderLayout.NORTH);
        ingresarDatosPanel.add(ingreseDatosLabel, BorderLayout.NORTH);

        // Panel para los campos de actualización
        JPanel actualizarCuentaDeAhorroPanel = new JPanel(new GridLayout(6, 2));

        JLabel saldoLabel = new JLabel("Nuevo saldo:");
        SpinnerModel saldoModel = new SpinnerNumberModel(1, 1, 10000000, 1);
        JSpinner saldoSpinner = new JSpinner(saldoModel);

        JLabel tasaInteresLabel = new JLabel("Tasa de interés:");
        SpinnerModel tasaInteresModel = new SpinnerNumberModel(1, 1, 20, 0.1);
        JSpinner tasaInteresSpinner = new JSpinner(tasaInteresModel);

        JLabel topeMinimoLabel = new JLabel("Tope mínimo:");
        SpinnerModel topeMinimoModel = new SpinnerNumberModel(200000, 100000, 10000000, 50000);
        JSpinner topeMinimoSpinner = new JSpinner(topeMinimoModel);

        actualizarCuentaDeAhorroPanel.add(saldoLabel);
        actualizarCuentaDeAhorroPanel.add(saldoSpinner);
        actualizarCuentaDeAhorroPanel.add(tasaInteresLabel);
        actualizarCuentaDeAhorroPanel.add(tasaInteresSpinner);
        actualizarCuentaDeAhorroPanel.add(topeMinimoLabel);
        actualizarCuentaDeAhorroPanel.add(topeMinimoSpinner);

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnVolver = new JButton("Volver");

        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnVolver);

        // Agregar los paneles al panel principal
        ingresarDatosPanel.add(actualizarCuentaDeAhorroPanel, BorderLayout.CENTER);
        ingresarDatosPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Agregar el panel principal a la ventana
        getContentPane().add(ingresarDatosPanel);

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarDatosActualizacionCuentaDeAhorro((Integer) saldoSpinner.getValue(), (Double) tasaInteresSpinner.getValue(), (Integer) topeMinimoSpinner.getValue())){
                    if (controladorCuentasDeAhorro.actualizarCuentaDeAhorro(rut, (Integer) saldoSpinner.getValue(), (Double) tasaInteresSpinner.getValue(), (Integer) topeMinimoSpinner.getValue())) {
                        JOptionPane.showMessageDialog(getContentPane(), "Los datos de la cuenta de ahorro del cliente " + datos[1] + " " + datos[2] + " han sido actualizados", "Actualización completada", JOptionPane.INFORMATION_MESSAGE);
                        gestionCuentasDeAhorro();
                    }
                    else {
                        JOptionPane.showMessageDialog(getContentPane(), "No se pudieron actualizar los datos del cliente", "Actualización fallida", JOptionPane.ERROR_MESSAGE);
                        gestionCuentasDeAhorro();
                    }
                }
                else {
                    gestionCuentasDeAhorro();
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionCuentasDeAhorro();
            }
        });

        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);
    }

    private void eliminarCuentaDeAhorro() {
        setTitle("Eliminar cuenta de ahorro de un cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buscarClientePanel = new JPanel(new GridLayout(7, 2));

        JLabel rutLabel = new JLabel("RUT del cliente:");
        JTextField rutField = new JTextField();

        JButton btnBuscar = new JButton("Buscar");
        JButton btnVolver = new JButton("Volver");

        buscarClientePanel.add(rutLabel);
        buscarClientePanel.add(rutField);

        buscarClientePanel.add(btnBuscar);
        buscarClientePanel.add(btnVolver);

        getContentPane().removeAll(); // Limpiar el contenido actual
        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout
        getContentPane().add(buscarClientePanel, BorderLayout.CENTER);

        // Configurar el tamaño y hacer visible la ventana
        setSize(350, 250);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rut = rutField.getText();
                if(validarRut(rut)){
                    Object[] datos = controladorClientes.buscarCliente(rut);
                    if(datos.length > 0){
                        if (!controladorClientes.hayCuentaAhorro(rut)) {
                            JOptionPane.showMessageDialog(buscarClientePanel, "El cliente no tiene cuenta de ahorro para eliminar", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                            eliminarCuentaDeAhorro();
                        }
                        else {
                            JOptionPane.showMessageDialog(buscarClientePanel, "Cliente encontrado en la base de datos", "Búsqueda exitosa", JOptionPane.INFORMATION_MESSAGE);
                            if (controladorCuentasDeAhorro.eliminarCuentaDeAhorro((Integer) datos[0])){
                                JOptionPane.showMessageDialog(buscarClientePanel, "Cuenta de ahorro eliminada", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
                                gestionCuentasDeAhorro();
                            }
                            else {
                                JOptionPane.showMessageDialog(buscarClientePanel, "No se pudo eliminar la cuenta de ahorro", "Eliminación fallida", JOptionPane.ERROR_MESSAGE);
                                gestionCuentasDeAhorro();
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(buscarClientePanel, "No se encontró al cliente en la base de datos", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                        eliminarCuentaDeAhorro();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(buscarClientePanel, "Ingrese un RUT válido", "RUT inválido", JOptionPane.ERROR_MESSAGE);
                    eliminarCuentaDeAhorro();
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

    private void buscarCuentaDeAhorro() {
        setTitle("Buscar cuenta de ahorro por RUT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buscarCuentaDeAhorroPanel = new JPanel(new GridLayout(7, 2));

        JLabel rutLabel = new JLabel("RUT:");
        JTextField rutField = new JTextField();

        JButton btnBuscar = new JButton("Buscar");
        JButton btnVolver = new JButton("Volver");

        buscarCuentaDeAhorroPanel.add(rutLabel);
        buscarCuentaDeAhorroPanel.add(rutField);

        buscarCuentaDeAhorroPanel.add(btnBuscar);
        buscarCuentaDeAhorroPanel.add(btnVolver);

        getContentPane().removeAll(); // Limpiar el contenido actual
        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout
        getContentPane().add(buscarCuentaDeAhorroPanel, BorderLayout.CENTER);

        // Configurar el tamaño y hacer visible la ventana
        setSize(350, 250);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarRut(rutField.getText())){
                    if(controladorClientes.hayCuentaAhorro(rutField.getText())){
                        Object[] datos = controladorCuentasDeAhorro.buscarCuentaDeAhorro(rutField.getText());
                        if(datos.length > 0)
                        {
                            JOptionPane.showMessageDialog(buscarCuentaDeAhorroPanel, "Cliente encontrado en la base de datos", "Búsqueda exitosa", JOptionPane.INFORMATION_MESSAGE);
                            mostrarDatosCuentaDeAhorro(datos);
                        }
                        else {
                            JOptionPane.showMessageDialog(buscarCuentaDeAhorroPanel, "No se encontró la cuenta de ahorro", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                            buscarCuentaDeAhorro();
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(buscarCuentaDeAhorroPanel, "El cliente no tiene cuenta de ahorro", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(buscarCuentaDeAhorroPanel, "Ingrese un RUT válido", "RUT inválido", JOptionPane.ERROR_MESSAGE);
                    buscarCuentaDeAhorro();
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

    private void mostrarDatosCuentaDeAhorro(Object[] datos) {
        getContentPane().removeAll();
        setTitle("Datos de la cuenta de ahorro:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 150);

        String[] columnas = {"ID de cuenta", "Nombre", "Apellido", "RUT", "Saldo", "Tasa de interés", "Tope mínimo para retiros"};

        // Datos

        DefaultTableModel tableModel = new DefaultTableModel(null, columnas);

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        tableModel.addRow(datos);

        // Botón de actualizado
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para actualizar la lista de clientes
                mostrarDatosCuentaDeAhorro(datos);
            }
        });

        // Panel para el botón volver
        JPanel buttonPanel = new JPanel();
        JButton btnVolver = new JButton("Volver");

        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnVolver);

        // Agregar el panel de botones a la parte inferior de la ventana
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionCuentasDeAhorro();
            }
        });

        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);
    }

    private void gestionCuentasCorrientes() {
        getContentPane().removeAll(); // Limpiar el contenido actual

        setTitle("Gestión de cuentas corrientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controladorCuentasCorrientes = new ControladorCuentasCorrientes();

        JPanel gestionCuentasCorrientesPanel = new JPanel(new FlowLayout());

        JButton btnIngresarCuentaCorriente = new JButton("Ingresar cuenta corriente");
        JButton btnListaDeCuentasCorriente = new JButton("Lista de cuentas corrientes");
        JButton btnActualizarCuentaCorriente = new JButton("Actualizar cuenta corriente");
        JButton btnEliminarCuentaCorriente = new JButton("Eliminar cuenta corriente");
        JButton btnBuscarCuentaCorriente = new JButton("Buscar cuenta corriente por RUT");
        JButton btnVolver = new JButton("Volver");

        gestionCuentasCorrientesPanel.add(btnIngresarCuentaCorriente);
        gestionCuentasCorrientesPanel.add(btnListaDeCuentasCorriente);
        gestionCuentasCorrientesPanel.add(btnActualizarCuentaCorriente);
        gestionCuentasCorrientesPanel.add(btnEliminarCuentaCorriente);
        gestionCuentasCorrientesPanel.add(btnBuscarCuentaCorriente);
        gestionCuentasCorrientesPanel.add(btnVolver);

        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout
        getContentPane().add(gestionCuentasCorrientesPanel, BorderLayout.CENTER);

        // Configurar el tamaño y hacer visible la ventana
        setSize(300, 250);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);

        btnIngresarCuentaCorriente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingresarCuentaCorriente();
            }
        });

        btnListaDeCuentasCorriente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaDeCuentasCorrientes();
            }
        });

        /*btnActualizarCuentaCorriente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCuentaCorriente();
            }
        });

        btnEliminarCuentaCorriente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCuentaCorriente();
            }
        });

        btnBuscarCuentaCorriente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCuentaCorriente();
            }
        });*/

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaPrincipal();
            }
        });
    }

    private void ingresarCuentaCorriente() {
        setTitle("Ingresar cuenta corriente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel ingresarCuentaCorrientePanel = new JPanel(new GridLayout(7, 2));

        JLabel rutLabel = new JLabel("Ingrese RUT del cliente: ");
        JTextField rutField = new JTextField();

        JLabel saldoLabel = new JLabel("Saldo inicial:");
        SpinnerModel saldoModel = new SpinnerNumberModel(1, 1, 10000000, 1);
        JSpinner saldoSpinner = new JSpinner(saldoModel);

        JLabel sobregiroLabel = new JLabel("Cupo para sobregiro:");
        SpinnerModel sobregiroModel = new SpinnerNumberModel(200000, 100000, 10000000, 50000);
        JSpinner sobregiroSpinner = new JSpinner(sobregiroModel);

        JButton btnIngresarCuentaCorriente = new JButton("Ingresar cuenta corriente");
        JButton btnVolver = new JButton("Volver");

        ingresarCuentaCorrientePanel.add(rutLabel);
        ingresarCuentaCorrientePanel.add(rutField);
        ingresarCuentaCorrientePanel.add(saldoLabel);
        ingresarCuentaCorrientePanel.add(saldoSpinner);
        ingresarCuentaCorrientePanel.add(sobregiroLabel);
        ingresarCuentaCorrientePanel.add(sobregiroSpinner);
        ingresarCuentaCorrientePanel.add(btnIngresarCuentaCorriente);
        ingresarCuentaCorrientePanel.add(btnVolver);

        getContentPane().removeAll(); // Limpiar el contenido actual
        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout
        getContentPane().add(ingresarCuentaCorrientePanel, BorderLayout.CENTER);

        // Configurar el tamaño y hacer visible la ventana
        setSize(500, 350);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);

        btnIngresarCuentaCorriente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rut = rutField.getText();
                int saldo = (Integer) saldoSpinner.getValue();
                int sobregiro = (Integer) sobregiroSpinner.getValue();
                if(validarDatosIngresoCuentaCorriente(rut, saldo, sobregiro)){
                    Object[] datos = controladorClientes.buscarCliente(rutField.getText());
                    if(datos.length > 0){
                        if(controladorClientes.hayCuentaCorriente(rutField.getText())) {
                            JOptionPane.showMessageDialog(ingresarCuentaCorrientePanel, "El cliente de RUT " + rutField.getText() + " ya tiene una cuenta corriente", "Creación de cuenta fallida", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            int idCliente = controladorClientes.encontrarIdClientePorRUT(rutField.getText());
                            if(controladorCuentasCorrientes.crearCuentaCorriente(idCliente, (Integer) saldoSpinner.getValue(), (Integer) sobregiroSpinner.getValue())) {
                                JOptionPane.showMessageDialog(ingresarCuentaCorrientePanel, "Cuenta corriente creada exitosamente para el cliente " + datos[1] + " " + datos[2], "Creación de cuenta exitosa", JOptionPane.INFORMATION_MESSAGE);
                                gestionCuentasCorrientes();
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(ingresarCuentaCorrientePanel, "No se encontró al cliente en la base de datos", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionCuentasCorrientes();
            }
        });
    }

    private void listaDeCuentasCorrientes() {
        getContentPane().removeAll();
        setTitle("Lista de cuentas corrientes en el banco:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 350);

        String[] columnas = {"ID de cuenta", "Nombre", "Apellido", "RUT", "Saldo", "Cupo para sobregiro"};

        // Tabla de clientes

        DefaultTableModel tableModel = new DefaultTableModel(null, columnas);

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        Object[][] cuentas = controladorCuentasCorrientes.obtenerDatosCuentasCorrientes();

        if (cuentas.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay cuentas corrientes registradas en la base de datos", "Base de datos vacía", JOptionPane.ERROR_MESSAGE);
            gestionCuentasCorrientes();
        }

        else {
            for (Object[] cuenta : cuentas) {
                tableModel.addRow(cuenta);
            }

            // Botón de actualizado
            JButton btnActualizar = new JButton("Actualizar registros");
            btnActualizar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para actualizar la lista de clientes
                    actualizarListaCuentasCorrientes(tableModel);
                }
            });

            // Panel para el botón volver
            JPanel buttonPanel = new JPanel();
            JButton btnVolver = new JButton("Volver");

            buttonPanel.add(btnActualizar);
            buttonPanel.add(btnVolver);

            // Agregar el panel de botones a la parte inferior de la ventana
            getContentPane().add(buttonPanel, BorderLayout.SOUTH);

            btnVolver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gestionCuentasCorrientes();
                }
            });

            setLocationRelativeTo(null); // Centrar en la pantalla
            setVisible(true);
        }
    }

    private void actualizarListaCuentasCorrientes(DefaultTableModel tableModel) {
        // Lógica para obtener y actualizar la lista de clientes
        Object[][] cuentas = controladorCuentasCorrientes.obtenerDatosCuentasCorrientes();

        // Limpiar la tabla
        tableModel.setRowCount(0);

        if (cuentas.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay clientes en la base de datos", "Base de datos vacía", JOptionPane.ERROR_MESSAGE);
            gestionClientes();
        } else {
            // Agregar los nuevos datos a la tabla
            for (Object[] cuenta : cuentas) {
                tableModel.addRow(cuenta);
            }
        }
    }

    private boolean validarDatosActualizacionCuentaDeAhorro(int saldo, double tasaInteres, int topeMinimo) {
        if (!validarSaldo(saldo)) {
            JOptionPane.showMessageDialog(getContentPane(), "Saldo inválido", "Actualización fallida", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!validarTasaInteres(tasaInteres)) {
            JOptionPane.showMessageDialog(getContentPane(), "Tasa de interés inválida", "Actualización fallida", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!validarTopeMinimo(topeMinimo)) {
            JOptionPane.showMessageDialog(getContentPane(), "Tope mínimo inválido", "Actualización fallida", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void actualizarListaCuentasDeAhorro(DefaultTableModel tableModel) {
        // Lógica para obtener y actualizar la lista de clientes
        Object[][] cuentas = controladorCuentasDeAhorro.obtenerDatosCuentasDeAhorro();

        // Limpiar la tabla
        tableModel.setRowCount(0);

        if (cuentas.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay clientes en la base de datos", "Base de datos vacía", JOptionPane.ERROR_MESSAGE);
            gestionClientes();
        } else {
            // Agregar los nuevos datos a la tabla
            for (Object[] cuenta : cuentas) {
                tableModel.addRow(cuenta);
            }
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

    private boolean validarDatosActualizacionCliente(String nombre, String apellido, int edad, String email, String fono) {
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

        if(!validarTelefono(fono)){
            mostrarMensajeErrorDeValidacion("Teléfono inválido");
            return false;
        }

        return true;
    }

    private boolean validarDatosIngresoCuentaDeAhorro(String rut, int saldo, double tasaInteres, int topeMinimo) {
        if(!validarRut(rut)){
            mostrarMensajeErrorDeValidacion("RUT inválido");
            return false;
        }

        if(!validarSaldo(saldo)){
            mostrarMensajeErrorDeValidacion("Saldo inválido");
            return false;
        }

        if(!validarTasaInteres(tasaInteres)){
            mostrarMensajeErrorDeValidacion("Tasa de interés inválida");
            return false;
        }

        if(!validarTopeMinimo(topeMinimo)){
            mostrarMensajeErrorDeValidacion("Tope mínimo");
            return false;
        }

        return true;
    }

    private boolean validarDatosIngresoCuentaCorriente(String rut, int saldo, int sobregiro) {
        if(!validarRut(rut)){
            mostrarMensajeErrorDeValidacion("RUT inválido");
            return false;
        }

        if(!validarSaldo(saldo)){
            mostrarMensajeErrorDeValidacion("Saldo inválido");
            return false;
        }

        if(!validarSobregiro(sobregiro)){
            mostrarMensajeErrorDeValidacion("Monto de sobregiro inválido");
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
