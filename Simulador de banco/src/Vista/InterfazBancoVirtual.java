package Vista;

import Controlador.*;
import com.sun.management.GarbageCollectionNotificationInfo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
        JFrame loginFrame = new JFrame("Iniciar sesión");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configurar la ventana para que se abra maximizada
        loginFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Deshabilitar la capacidad de cambiar el tamaño de la ventana
        loginFrame.setResizable(false);

        // Usar GridBagLayout para mayor flexibilidad en la disposición de los componentes
        loginFrame.setLayout(new GridBagLayout());

        // Configurar GridBagConstraints para el título
        GridBagConstraints gbcTitulo = new GridBagConstraints();
        gbcTitulo.gridx = 0;
        gbcTitulo.gridy = 0;
        gbcTitulo.insets = new Insets(0, 0, 55, 0); // Márgenes inferiores

        JLabel titleLabel = new JLabel("Bienvenido a BancoVirtual");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        loginFrame.add(titleLabel, gbcTitulo);

        // Configurar GridBagConstraints para el logo
        GridBagConstraints gbcLogo = new GridBagConstraints();
        gbcLogo.gridx = 0;
        gbcLogo.gridy = 1;
        gbcLogo.insets = new Insets(0, 0, 55, 0); // Márgenes inferiores

        // Logo de la aplicación BancoVirtual
        ImageIcon logo = crearIcono("/img/bancoVirtualLogo.png");
        if (logo != null) {
            // Escalar la imagen a un tamaño específico
            ImageIcon scaledLogo = escalarImagen(logo, 150, 150);
            JLabel logoLabel = new JLabel(scaledLogo);
            logoLabel.setHorizontalAlignment(JLabel.CENTER);
            loginFrame.setIconImage(scaledLogo.getImage());
            loginFrame.add(logoLabel, gbcLogo);
        }

        // Configurar GridBagConstraints para el subtítulo
        GridBagConstraints gbcSubtitle = new GridBagConstraints();
        gbcSubtitle.gridx = 0;
        gbcSubtitle.gridy = 2;
        gbcSubtitle.insets = new Insets(0, 0, 50, 0); // Márgenes inferiores

        JLabel subtitleLabel = new JLabel("Administración de la base de datos del banco");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        subtitleLabel.setHorizontalAlignment(JLabel.CENTER);
        loginFrame.add(subtitleLabel, gbcSubtitle);

        // Configurar GridBagConstraints para el panel de login
        GridBagConstraints gbcLoginPanel = new GridBagConstraints();
        gbcLoginPanel.gridx = 0;
        gbcLoginPanel.gridy = 3;
        gbcLoginPanel.insets = new Insets(0, 0, 100, 0); // Márgenes inferiores

        JPanel loginPanel = new JPanel(new GridBagLayout());  // Cambiar a GridBagLayout para mayor flexibilidad

        // Configurar GridBagConstraints para el campo de RUT
        GridBagConstraints gbcRutLabel = new GridBagConstraints();
        gbcRutLabel.gridx = 0;
        gbcRutLabel.gridy = 0;
        gbcRutLabel.anchor = GridBagConstraints.EAST; // Alinear a la derecha
        gbcRutLabel.insets = new Insets(10, 0, 0, 0);

        JLabel rutLabel = new JLabel("Ingrese su RUT (con puntos y guión):");
        rutLabel.setPreferredSize(new Dimension(400, 50));
        rutLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        loginPanel.add(rutLabel, gbcRutLabel);

        GridBagConstraints gbcRutField = new GridBagConstraints();
        gbcRutField.gridx = 1;
        gbcRutField.gridy = 0;
        gbcRutField.fill = GridBagConstraints.HORIZONTAL; // Hacer que el campo ocupe el espacio horizontal disponible
        gbcRutField.insets = new Insets(27, 0, 15, 0); // Añadir márgenes a la izquierda

        JTextField rutField = new JTextField();
        rutField.setFont(new Font("Arial", Font.PLAIN, 15));
        rutField.setPreferredSize(new Dimension(400, 40));  // Establecer el tamaño preferido
        loginPanel.add(rutField, gbcRutField);

// Configurar GridBagConstraints para el campo de contraseña
        GridBagConstraints gbcPasswordLabel = new GridBagConstraints();
        gbcPasswordLabel.gridx = 0;
        gbcPasswordLabel.gridy = 1;
        gbcPasswordLabel.anchor = GridBagConstraints.EAST; // Alinear a la derecha
        gbcPasswordLabel.insets = new Insets(-8, 0, 0, 0);

        JLabel passwordLabel = new JLabel("Ingrese su contraseña:");
        passwordLabel.setPreferredSize(new Dimension(400, 50));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        loginPanel.add(passwordLabel, gbcPasswordLabel);

        GridBagConstraints gbcPasswordField = new GridBagConstraints();
        gbcPasswordField.gridx = 1;
        gbcPasswordField.gridy = 1;
        gbcPasswordField.fill = GridBagConstraints.HORIZONTAL; // Hacer que el campo ocupe el espacio horizontal disponible
        gbcPasswordField.insets = new Insets(0, 0, 0, 0); // Añadir márgenes a la izquierda

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordField.setPreferredSize(new Dimension(400, 40));  // Establecer el tamaño preferido
        loginPanel.add(passwordField, gbcPasswordField);

        loginFrame.add(loginPanel, gbcLoginPanel);

        // Configurar GridBagConstraints para el botón de login
        GridBagConstraints gbcLoginButton = new GridBagConstraints();
        gbcLoginButton.gridx = 0;
        gbcLoginButton.gridy = 4;
        gbcLoginButton.insets = new Insets(-30, 0, 0, 0);

        JButton loginButton = new JButton("Iniciar sesión");
        loginButton.setPreferredSize(new Dimension(130, 50));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        loginButton.addActionListener(e -> {
            if (validarCredenciales(rutField.getText(), String.valueOf(passwordField.getPassword()))) {
                mostrarMensajeAccesoExitoso();
                loginFrame.dispose();
                abrirVentanaPrincipal();
            }
        });
        loginFrame.add(loginButton, gbcLoginButton);

        // Hacer visible la ventana
        loginFrame.setVisible(true);
    }

    private void abrirVentanaPrincipal() {
        getContentPane().removeAll();
        setTitle("Banco Virtual");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(2, 2, 10, 10));

        // Panel 1 con GridBagLayout
        JPanel panel1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcImagenClientes = new GridBagConstraints();
        GridBagConstraints gbcBotonClientes = new GridBagConstraints();

        ImageIcon imageClientes = crearIcono("/img/logoClientes.png");
        ImageIcon scaledImageClientes = escalarImagen(imageClientes, 250, 200);
        JLabel labelClientes = new JLabel(scaledImageClientes);
        gbcImagenClientes.insets = new Insets(60, 400, 10, 10);
        panel1.add(labelClientes, gbcImagenClientes);

        JButton btnGestionClientes = new JButton("Gestión de clientes");
        btnGestionClientes.setPreferredSize(new Dimension(180, 35));
        btnGestionClientes.setFont(new Font("Arial", Font.BOLD, 15));
        btnGestionClientes.addActionListener(e -> gestionClientes());
        gbcBotonClientes.insets = new Insets(30, 400, 10, 10);
        gbcBotonClientes.gridy = 1;
        panel1.add(btnGestionClientes, gbcBotonClientes);

        add(panel1);

        // Panel 2 con GridBagLayout
        JPanel panel2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcImagenAhorro = new GridBagConstraints();
        GridBagConstraints gbcBotonAhorro = new GridBagConstraints();

        ImageIcon imageAhorro = crearIcono("/img/logoCuentasDeAhorro.png");
        ImageIcon scaledImageAhorro = escalarImagen(imageAhorro, 200, 200);
        JLabel labelAhorro = new JLabel(scaledImageAhorro);
        gbcImagenAhorro.insets = new Insets(60, 10, 10, 400);
        panel2.add(labelAhorro, gbcImagenAhorro);

        JButton btnGestionCuentasAhorro = new JButton("Gestión de cuentas de ahorro");
        btnGestionCuentasAhorro.setPreferredSize(new Dimension(260, 35));
        btnGestionCuentasAhorro.setFont(new Font("Arial", Font.BOLD, 15));
        btnGestionCuentasAhorro.addActionListener(e -> gestionCuentasDeAhorro());
        gbcBotonAhorro.insets = new Insets(30, 10, 10, 400);
        gbcBotonAhorro.gridy = 1;
        panel2.add(btnGestionCuentasAhorro, gbcBotonAhorro);

        add(panel2);

        // Panel 3 con GridBagLayout
        JPanel panel3 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcImagenCorrientes = new GridBagConstraints();
        GridBagConstraints gbcBotonCorrientes = new GridBagConstraints();

        ImageIcon imageCorrientes = crearIcono("/img/logoCuentasCorrientes.png");
        ImageIcon scaledImageCorrientes = escalarImagen(imageCorrientes, 200, 200);
        JLabel labelCorrientes = new JLabel(scaledImageCorrientes);
        gbcImagenCorrientes.insets = new Insets(30, 400, 10, 10);
        panel3.add(labelCorrientes, gbcImagenCorrientes);

        JButton btnGestionCuentasCorrientes = new JButton("Gestión de cuentas corrientes");
        btnGestionCuentasCorrientes.setPreferredSize(new Dimension(260, 35));
        btnGestionCuentasCorrientes.setFont(new Font("Arial", Font.BOLD, 15));
        btnGestionCuentasCorrientes.addActionListener(e -> gestionCuentasCorrientes());
        gbcBotonCorrientes.insets = new Insets(30, 410, 60, 10);
        gbcBotonCorrientes.gridy = 1;
        panel3.add(btnGestionCuentasCorrientes, gbcBotonCorrientes);

        add(panel3);

        // Panel 4 con GridBagLayout
        JPanel panel4 = new JPanel(new GridBagLayout());
        GridBagConstraints gbcImagenSalir = new GridBagConstraints();
        GridBagConstraints gbcBotonSalir = new GridBagConstraints();

        ImageIcon imageSalir = crearIcono("/img/salir.png");
        ImageIcon scaledImageSalir = escalarImagen(imageSalir, 200, 200);
        JLabel labelSalir = new JLabel(scaledImageSalir);
        gbcImagenSalir.insets = new Insets(30, 10, 10, 400);
        panel4.add(labelSalir, gbcImagenSalir);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setPreferredSize(new Dimension(180, 35));
        btnSalir.setFont(new Font("Arial", Font.BOLD, 15));
        btnSalir.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        gbcBotonSalir.insets = new Insets(30, 10, 60, 400);
        gbcBotonSalir.gridy = 1;
        panel4.add(btnSalir, gbcBotonSalir);

        add(panel4);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    // Método para cargar la imagen desde un archivo
    protected ImageIcon crearIcono(String ruta) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(ruta);

            if (inputStream != null) {
                byte[] bytes = inputStream.readAllBytes();
                return new ImageIcon(bytes);
            } else {
                System.err.println("No se pudo encontrar el archivo de imagen en la ruta " + ruta);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ImageIcon escalarImagen(ImageIcon icono, int ancho, int alto) {
        Image imagen = icono.getImage();
        Image imagenEscalada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }

    private void gestionClientes() {
        getContentPane().removeAll(); // Limpiar el contenido actual

        setTitle("Gestión de clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        controladorClientes = new ControladorClientes();

        JPanel botonesPanel = new JPanel(new GridBagLayout());

        JButton btnIngresarCliente = new JButton("Ingresar cliente");
        JButton btnListaDeClientes = new JButton("Lista de clientes");
        JButton btnActualizarCliente = new JButton("Actualizar datos de un cliente");
        JButton btnEliminarCliente = new JButton("Eliminar cliente");
        JButton btnBuscarCliente = new JButton("Buscar cliente por RUT");
        JButton btnVolver = new JButton("Volver");

        // Establecer un tamaño fijo para los botones
        Dimension buttonSize = new Dimension(180, 35);
        Dimension actualizarButtonSize = new Dimension(220, 35);
        btnIngresarCliente.setPreferredSize(buttonSize);
        btnListaDeClientes.setPreferredSize(buttonSize);
        btnActualizarCliente.setPreferredSize(actualizarButtonSize);
        btnEliminarCliente.setPreferredSize(buttonSize);
        btnBuscarCliente.setPreferredSize(buttonSize);
        btnVolver.setPreferredSize(buttonSize);

        GridBagConstraints gbcBtnIngresarCliente = new GridBagConstraints();
        GridBagConstraints gbcBtnListaDeClientes = new GridBagConstraints();
        GridBagConstraints gbcBtnActualizarCliente = new GridBagConstraints();
        GridBagConstraints gbcBtnEliminarCliente = new GridBagConstraints();
        GridBagConstraints gbcBtnBuscarCliente = new GridBagConstraints();
        GridBagConstraints gbcBtnVolver = new GridBagConstraints();

        GridBagConstraints gbcImagenAgregarClientes = new GridBagConstraints();

        ImageIcon imageAgregarCliente = crearIcono("/img/agregarCliente.png");
        ImageIcon scaledImageAgregarCliente = escalarImagen(imageAgregarCliente, 150, 150);
        JLabel labelAgregarCliente = new JLabel(scaledImageAgregarCliente);

        gbcImagenAgregarClientes.insets = new Insets(0, 10, 10, 10);
        gbcImagenAgregarClientes.gridx = 0;
        gbcImagenAgregarClientes.gridy = 0;

        botonesPanel.add(labelAgregarCliente, gbcImagenAgregarClientes);

        gbcBtnIngresarCliente.gridx = 0;
        gbcBtnIngresarCliente.gridy = 1;
        gbcBtnIngresarCliente.insets = new Insets(15, 10, 5, 10); // Márgenes entre botones

        botonesPanel.add(btnIngresarCliente, gbcBtnIngresarCliente);

        GridBagConstraints gbcImagenListaClientes = new GridBagConstraints();

        ImageIcon imageListaClientes = crearIcono("/img/listaClientes.png");
        ImageIcon scaledImageListaClientes = escalarImagen(imageListaClientes, 150, 150);
        JLabel labelListaClientes = new JLabel(scaledImageListaClientes);

        gbcImagenListaClientes.insets = new Insets(30, 10, 10, 10);
        gbcImagenListaClientes.gridx = 0;
        gbcImagenListaClientes.gridy = 2;

        botonesPanel.add(labelListaClientes, gbcImagenListaClientes);

        gbcBtnListaDeClientes.gridx = 0;
        gbcBtnListaDeClientes.gridy = 3;
        gbcBtnListaDeClientes.insets = new Insets(15, 10, 5, 10);

        botonesPanel.add(btnListaDeClientes, gbcBtnListaDeClientes);

        GridBagConstraints gbcImagenActualizarCliente = new GridBagConstraints();

        ImageIcon imageActualizarCliente = crearIcono("/img/actualizarDatos.png");
        ImageIcon scaledImageActualizarCliente = escalarImagen(imageActualizarCliente, 150, 150);
        JLabel labelActualizarCliente = new JLabel(scaledImageActualizarCliente);

        gbcImagenActualizarCliente.insets = new Insets(30, 10, 10, 10);
        gbcImagenActualizarCliente.gridx = 0;
        gbcImagenActualizarCliente.gridy = 4;

        botonesPanel.add(labelActualizarCliente, gbcImagenActualizarCliente);

        gbcBtnActualizarCliente.gridx = 0;
        gbcBtnActualizarCliente.gridy = 5;
        gbcBtnActualizarCliente.insets = new Insets(15, 10, 5, 10);

        botonesPanel.add(btnActualizarCliente, gbcBtnActualizarCliente);

        GridBagConstraints gbcImagenEliminarCliente = new GridBagConstraints();

        ImageIcon imageEliminarCliente = crearIcono("/img/eliminarCliente.png");
        ImageIcon scaledImageEliminarCliente = escalarImagen(imageEliminarCliente, 150, 150);
        JLabel labelEliminarCliente = new JLabel(scaledImageEliminarCliente);

        gbcImagenEliminarCliente.insets = new Insets(0, 10, 10, 10);
        gbcImagenEliminarCliente.gridx = 1;
        gbcImagenEliminarCliente.gridy = 0;

        botonesPanel.add(labelEliminarCliente, gbcImagenEliminarCliente);

        gbcBtnEliminarCliente.gridx = 1;
        gbcBtnEliminarCliente.gridy = 1;
        gbcBtnEliminarCliente.insets = new Insets(15, 10, 5, 10);

        botonesPanel.add(btnEliminarCliente, gbcBtnEliminarCliente);

        GridBagConstraints gbcImagenBuscarCliente = new GridBagConstraints();

        ImageIcon imageBuscarCliente = crearIcono("/img/buscarCliente.png");
        ImageIcon scaledImageBuscarCliente = escalarImagen(imageBuscarCliente, 150, 150);
        JLabel labelBuscarCliente = new JLabel(scaledImageBuscarCliente);

        gbcImagenBuscarCliente.insets = new Insets(30, 10, 10, 10);
        gbcImagenBuscarCliente.gridx = 1;
        gbcImagenBuscarCliente.gridy = 2;

        botonesPanel.add(labelBuscarCliente, gbcImagenBuscarCliente);

        gbcBtnBuscarCliente.gridx = 1;
        gbcBtnBuscarCliente.gridy = 3;
        gbcBtnBuscarCliente.insets = new Insets(15, 10, 5, 10);

        botonesPanel.add(btnBuscarCliente, gbcBtnBuscarCliente);

        GridBagConstraints gbcImagenVolver = new GridBagConstraints();

        ImageIcon imageVolver = crearIcono("/img/salir.png");
        ImageIcon scaledImageVolver = escalarImagen(imageVolver, 150, 150);
        JLabel labelVolver = new JLabel(scaledImageVolver);

        gbcImagenVolver.insets = new Insets(30, 10, 10, 10);
        gbcImagenVolver.gridx = 1;
        gbcImagenVolver.gridy = 4;

        botonesPanel.add(labelVolver, gbcImagenVolver);

        gbcBtnVolver.gridx = 1;
        gbcBtnVolver.gridy = 5;
        gbcBtnVolver.insets = new Insets(15, 10, 5, 10);

        botonesPanel.add(btnVolver, gbcBtnVolver);

        JPanel contenidoPanel = new JPanel(new GridBagLayout());

        ImageIcon imagenCliente = crearIcono("/img/clientIcon.png");
        ImageIcon scaledImagenCliente = escalarImagen(imagenCliente, 500, 500);
        JLabel labelImagenCliente = new JLabel(scaledImagenCliente);

        GridBagConstraints gbcImagenCliente = new GridBagConstraints();
        gbcImagenCliente.gridx = 0;
        gbcImagenCliente.gridy = 0;
        gbcImagenCliente.insets = new Insets(0, 0, 0, 0);
        contenidoPanel.add(labelImagenCliente, gbcImagenCliente);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, botonesPanel, contenidoPanel);
        splitPane.setDividerLocation(600); // Ancho inicial del panel izquierdo

        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout
        getContentPane().add(splitPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        btnIngresarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contenidoPanel.removeAll();
                contenidoPanel.add(ingresarCliente());
                contenidoPanel.revalidate(); // Revalida el contenido
                contenidoPanel.repaint();
            }
        });

        btnListaDeClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contenidoPanel.removeAll();
                contenidoPanel.add(listaDeClientes());
                contenidoPanel.revalidate(); // Revalida el contenido
                contenidoPanel.repaint();
            }
        });

        btnActualizarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contenidoPanel.removeAll();
                contenidoPanel.add(actualizarCliente());
                contenidoPanel.revalidate(); // Revalida el contenido
                contenidoPanel.repaint();
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

    private JPanel ingresarCliente() {
        setTitle("Gestión de clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel ingresarClientePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints gbcTitulo = new GridBagConstraints();
        GridBagConstraints gbcBoton = new GridBagConstraints();
        GridBagConstraints gbcImagen = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 5); // Márgenes

        int titleFontSize = 25;
        int labelFontSize = 20;
        int fieldFontSize = 15;
        int fieldWidth = 300;
        int fieldHeight = 40;
        int btnWidth = 100;
        int btnHeight = 32;
        int btnFontSize = 16;

        // Crear un borde compuesto
        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(), // Borde grabado para efecto visual
                BorderFactory.createEmptyBorder(-50, 10, 10, 10) // Márgenes internos
        );

        // Añadir un margen superior externo para mover el recuadro hacia abajo
        Border outerMargin = new EmptyBorder(0, 0, 0, 0);
        Border titledBorder = BorderFactory.createCompoundBorder(
                outerMargin,
                border
        );

        ingresarClientePanel.setBorder(titledBorder);

        // Título
        JLabel titleLabel = new JLabel("Ingresar cliente a la base de datos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, titleFontSize));
        gbcTitulo.gridx = 0;
        gbcTitulo.gridy = 0;
        gbcTitulo.gridwidth = 2; // Ocupa dos columnas
        gbcTitulo.anchor = GridBagConstraints.CENTER; // Centrado horizontal
        gbcTitulo.insets = new Insets(100, 5, 20, 5);
        ingresarClientePanel.add(titleLabel, gbcTitulo);

        ImageIcon imagen = crearIcono("/img/agregarCliente.png");
        ImageIcon scaledImagen = escalarImagen(imagen, 100, 100);
        JLabel labelImagen = new JLabel(scaledImagen);

        gbcImagen.insets = new Insets(0, 10, 50, 10);
        gbcImagen.anchor = GridBagConstraints.CENTER;
        gbcImagen.gridwidth = 2;
        gbcImagen.gridx = 0;
        gbcImagen.gridy = 1;

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setFont(new Font("Arial", Font.PLAIN, labelFontSize));
        JTextField nombreField = new JTextField();
        nombreField.setFont(new Font("Arial", Font.PLAIN, fieldFontSize));
        nombreField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setFont(new Font("Arial", Font.PLAIN, labelFontSize));
        JTextField apellidoField = new JTextField();
        apellidoField.setFont(new Font("Arial", Font.PLAIN, fieldFontSize));
        apellidoField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));

        JLabel edadLabel = new JLabel("Edad:");
        edadLabel.setFont(new Font("Arial", Font.PLAIN, labelFontSize));
        SpinnerModel edadModel = new SpinnerNumberModel(18, 1, 110, 1);
        JSpinner edadSpinner = new JSpinner(edadModel);
        edadSpinner.setFont(new Font("Arial", Font.PLAIN, fieldFontSize));
        edadSpinner.setPreferredSize(new Dimension(fieldWidth, fieldHeight));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, labelFontSize));
        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, fieldFontSize));
        emailField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));

        JLabel rutLabel = new JLabel("RUT (con puntos y guión):");
        rutLabel.setFont(new Font("Arial", Font.PLAIN, labelFontSize));
        JTextField rutField = new JTextField();
        rutField.setFont(new Font("Arial", Font.PLAIN, fieldFontSize));
        rutField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));

        JLabel fonoLabel = new JLabel("Teléfono:");
        fonoLabel.setFont(new Font("Arial", Font.PLAIN, labelFontSize));
        JTextField fonoField = new JTextField();
        fonoField.setFont(new Font("Arial", Font.PLAIN, fieldFontSize));
        fonoField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));

        JButton btnIngresarCliente = new JButton("Ingresar");
        btnIngresarCliente.setPreferredSize(new Dimension(btnWidth, btnHeight));
        btnIngresarCliente.setFont(new Font("Arial", Font.PLAIN, btnFontSize));

        ingresarClientePanel.add(labelImagen, gbcImagen);

        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        ingresarClientePanel.add(nombreLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        ingresarClientePanel.add(nombreField, gbc);

        // Apellido
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        ingresarClientePanel.add(apellidoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        ingresarClientePanel.add(apellidoField, gbc);

        // Edad
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        ingresarClientePanel.add(edadLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        ingresarClientePanel.add(edadSpinner, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        ingresarClientePanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        ingresarClientePanel.add(emailField, gbc);

        // RUT
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        ingresarClientePanel.add(rutLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        ingresarClientePanel.add(rutField, gbc);

        // Teléfono
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        ingresarClientePanel.add(fonoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        ingresarClientePanel.add(fonoField, gbc);

        // Botón Ingresar cliente
        gbcBoton.gridx = 0;
        gbcBoton.gridy = 8;
        gbcBoton.gridwidth = 2; // Ocupa dos columnas
        gbcBoton.anchor = GridBagConstraints.CENTER; // Centrado horizontal
        gbcBoton.insets = new Insets(50, 5, 30, 5);
        ingresarClientePanel.add(btnIngresarCliente, gbcBoton);

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

        return ingresarClientePanel;
    }


    private JPanel listaDeClientes() {

        JPanel listaDeClientesPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbcTitle = new GridBagConstraints();
        GridBagConstraints gbcTable = new GridBagConstraints();
        GridBagConstraints gbcBtn = new GridBagConstraints();

        setTitle("Gestión de clientes:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("Clientes que hay en el banco actualmente:");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.insets = new Insets(0, 0, 20, 0);

        listaDeClientesPanel.add(title, gbcTitle);

        ImageIcon imagen = crearIcono("/img/listaClientes.png");
        ImageIcon scaledImagen = escalarImagen(imagen, 60, 60);
        JLabel labelImagen = new JLabel(scaledImagen);

        GridBagConstraints gbcImagen = new GridBagConstraints();

        gbcImagen.insets = new Insets(0, 10, 50, 10);
        gbcImagen.anchor = GridBagConstraints.CENTER;
        gbcImagen.gridwidth = 2;
        gbcImagen.gridx = 0;
        gbcImagen.gridy = 1;

        listaDeClientesPanel.add(labelImagen, gbcImagen);

        String[] columnas = {"ID", "Nombre", "Apellido", "Edad", "Email", "RUT", "Teléfono", "¿Cuenta de ahorro?", "¿Cuenta corriente?"};

        // Tabla de clientes

        DefaultTableModel tableModel = new DefaultTableModel(null, columnas);

        JTable table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int[] anchos = {25, 80, 80, 50, 220, 100, 110, 120, 120};
        for (int i = 0; i < columnas.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        gbcTable.gridx = 0;
        gbcTable.gridy = 2;
        listaDeClientesPanel.add(scrollPane, gbcTable);

        Object[][] clientes = controladorClientes.obtenerDatosClientes();

        if (clientes.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay clientes en la base de datos", "Base de datos vacía", JOptionPane.ERROR_MESSAGE);
        }

        else {
            for (Object[] cliente : clientes) {
                tableModel.addRow(cliente);
            }

            // Establecer un ancho preferido para la tabla
            int anchoTotal = 0;
            for (int ancho : anchos) {
                anchoTotal += ancho;
            }

            // Establecer un alto preferido para la tabla
            int altoTotal = table.getRowHeight() * tableModel.getRowCount();

            table.setPreferredScrollableViewportSize(new Dimension(anchoTotal, altoTotal));
            table.setFont(new Font("Arial", Font.PLAIN, 14));

            // Botón de actualizado
            JButton btnActualizar = new JButton("Actualizar registros");
            btnActualizar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para actualizar la lista de clientes
                    actualizarListaClientes(tableModel);
                    // Establecer un alto preferido para la tabla
                    int altoTotal = table.getRowHeight() * tableModel.getRowCount();

                    // Establecer un ancho preferido para la tabla
                    int anchoTotal = 0;
                    for (int ancho : anchos) {
                        anchoTotal += ancho;
                    }

                    table.setPreferredScrollableViewportSize(new Dimension(anchoTotal, altoTotal));
                    table.setFont(new Font("Arial", Font.PLAIN, 14));
                }
            });

            gbcBtn.gridx = 0;
            gbcBtn.gridy = 3;
            gbcBtn.insets = new Insets(50, 0, 0, 0);

            btnActualizar.setPreferredSize(new Dimension(175, 32));
            btnActualizar.setFont(new Font("Arial", Font.PLAIN, 14));

            listaDeClientesPanel.add(btnActualizar, gbcBtn);

            setVisible(true);
        }

        return listaDeClientesPanel;
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

    private JPanel actualizarCliente() {
        setTitle("Actualizar datos de un cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buscarClientePanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbcTitle = new GridBagConstraints();
        GridBagConstraints gbcImage = new GridBagConstraints();
        GridBagConstraints gbcLabel = new GridBagConstraints();
        GridBagConstraints gbcField = new GridBagConstraints();
        GridBagConstraints gbcBtn = new GridBagConstraints();

        JLabel title = new JLabel("Actualizar datos del cliente");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.gridwidth = 2; // Ocupa dos columnas
        gbcTitle.anchor = GridBagConstraints.CENTER; // Centrado horizontal
        gbcTitle.insets = new Insets(100, 5, 20, 5);

        ImageIcon imagen = crearIcono("/img/actualizarDatos.png");
        ImageIcon scaledImagen = escalarImagen(imagen, 100, 100);
        JLabel labelImagen = new JLabel(scaledImagen);
        gbcImage.gridx = 0;
        gbcImage.gridy = 1;
        gbcImage.gridwidth = 2;
        gbcImage.insets = new Insets(0, 5, 20, 5);

        JLabel rutLabel = new JLabel("Ingrese el RUT del cliente (con puntos y guión):");
        rutLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 2;
        gbcLabel.insets = new Insets(20, 5, 20, 5);

        JTextField rutField = new JTextField();
        rutField.setFont(new Font("Arial", Font.PLAIN, 15));
        rutField.setPreferredSize(new Dimension(300, 40));
        gbcField.gridx = 0;
        gbcField.gridy = 3;
        gbcField.insets = new Insets(0, 5, 20, 5);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setPreferredSize(new Dimension(100, 32));
        btnBuscar.setFont(new Font("Arial", Font.PLAIN, 16));
        gbcBtn.gridx = 0;
        gbcBtn.gridy = 4;
        gbcBtn.gridwidth = 2;
        gbcBtn.insets = new Insets(20, 5, 20, 5);

        // Crear un borde compuesto
        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(), // Borde grabado para efecto visual
                BorderFactory.createEmptyBorder(-50, 10, 10, 10) // Márgenes internos
        );

        // Añadir un margen superior externo para mover el recuadro hacia abajo
        Border outerMargin = new EmptyBorder(0, 0, 0, 0);
        Border titledBorder = BorderFactory.createCompoundBorder(
                outerMargin,
                border
        );

        buscarClientePanel.add(title, gbcTitle);
        buscarClientePanel.add(labelImagen, gbcImage);
        buscarClientePanel.add(rutLabel, gbcLabel);
        buscarClientePanel.add(rutField, gbcField);
        buscarClientePanel.add(btnBuscar, gbcBtn);

        buscarClientePanel.setBorder(titledBorder);

        setVisible(true);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarRut(rutField.getText())){
                    Object[] datos = controladorClientes.buscarCliente(rutField.getText());
                    if(datos.length > 0){
                        JOptionPane.showMessageDialog(buscarClientePanel, "Cliente encontrado en la base de datos", "Búsqueda exitosa", JOptionPane.INFORMATION_MESSAGE);
                        buscarClientePanel.remove(rutLabel);
                        buscarClientePanel.remove(rutField);
                        buscarClientePanel.remove(btnBuscar);
                        buscarClientePanel.add(mostrarDatosYActualizar(datos, rutField.getText(), buscarClientePanel));
                        buscarClientePanel.revalidate(); // Revalida el contenido
                        buscarClientePanel.repaint();
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
        return buscarClientePanel;
    }

    private JPanel mostrarDatosYActualizar(Object[] datos, String rut, JPanel actualizarClientePanel) {
        setTitle("Gestión de clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints gbcNombreLabel = new GridBagConstraints();
        GridBagConstraints gbcNombreField = new GridBagConstraints();
        GridBagConstraints gbcApellidoLabel = new GridBagConstraints();
        GridBagConstraints gbcApellidoField = new GridBagConstraints();
        GridBagConstraints gbcEdadLabel = new GridBagConstraints();
        GridBagConstraints gbcEdadField = new GridBagConstraints();
        GridBagConstraints gbcEmailLabel = new GridBagConstraints();
        GridBagConstraints gbcEmailField = new GridBagConstraints();
        GridBagConstraints gbcFonoLabel = new GridBagConstraints();
        GridBagConstraints gbcFonoField = new GridBagConstraints();
        GridBagConstraints gbcBtn = new GridBagConstraints();
        int labelFontSize = 16;
        int fieldFontSize = 15;
        int btnFontSize = 16;

        // Etiqueta para el encabezado

        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField();
        nombreLabel.setFont(new Font("Arial", Font.PLAIN, labelFontSize));
        nombreField.setFont(new Font("Arial", Font.PLAIN, fieldFontSize));
        nombreField.setPreferredSize(new Dimension(300, 40));
        gbcNombreLabel.gridx = 0;
        gbcNombreLabel.gridy = 2;
        gbcNombreLabel.insets = new Insets(20, 5, 20, 5);
        gbcNombreField.gridx = 1;
        gbcNombreField.gridy = 2;
        gbcNombreField.insets = new Insets(20, 5, 20, 5);
        addPlaceholder(nombreField, "(Nombre actual: " + ((String) datos[1]) + ")");

        JLabel apellidoLabel = new JLabel("Apellido:");
        JTextField apellidoField = new JTextField();
        apellidoLabel.setFont(new Font("Arial", Font.PLAIN, labelFontSize));
        apellidoField.setFont(new Font("Arial", Font.PLAIN, fieldFontSize));
        apellidoField.setPreferredSize(new Dimension(300, 40));
        gbcApellidoLabel.gridx = 0;
        gbcApellidoLabel.gridy = 3;
        gbcApellidoLabel.insets = new Insets(0, 5, 20, 5);
        gbcApellidoField.gridx = 1;
        gbcApellidoField.gridy = 3;
        gbcApellidoField.insets = new Insets(0, 5, 20, 5);
        addPlaceholder(apellidoField, "(Apellido actual: " + ((String) datos[2]) + ")");

        JLabel edadLabel = new JLabel("Edad:");
        edadLabel.setFont(new Font("Arial", Font.PLAIN, labelFontSize));
        SpinnerModel edadModel = new SpinnerNumberModel(18, 1, 110, 1);
        gbcEdadLabel.gridx = 0;
        gbcEdadLabel.gridy = 4;
        gbcEdadLabel.insets = new Insets(0, 5, 20, 5);
        JSpinner edadSpinner = new JSpinner(edadModel);
        edadSpinner.setFont(new Font("Arial", Font.PLAIN, fieldFontSize));
        edadSpinner.setPreferredSize(new Dimension(300, 40));
        gbcEdadField.gridx = 1;
        gbcEdadField.gridy = 4;
        gbcEdadField.insets = new Insets(0, 5, 20, 5);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        emailLabel.setFont(new Font("Arial", Font.PLAIN, labelFontSize));
        emailField.setFont(new Font("Arial", Font.PLAIN, fieldFontSize));
        emailField.setPreferredSize(new Dimension(300, 40));
        gbcEmailLabel.gridx = 0;
        gbcEmailLabel.gridy = 5;
        gbcEmailLabel.insets = new Insets(0, 5, 20, 5);
        gbcEmailField.gridx = 1;
        gbcEmailField.gridy = 5;
        gbcEmailField.insets = new Insets(0, 5, 20, 5);
        addPlaceholder(emailField, "(Email actual: " + ((String) datos[4]) + ")");

        JLabel fonoLabel = new JLabel("Teléfono:");
        JTextField fonoField = new JTextField();
        fonoLabel.setFont(new Font("Arial", Font.PLAIN, labelFontSize));
        fonoField.setFont(new Font("Arial", Font.PLAIN, fieldFontSize));
        fonoField.setPreferredSize(new Dimension(300, 40));
        gbcFonoLabel.gridx = 0;
        gbcFonoLabel.gridy = 6;
        gbcFonoLabel.insets = new Insets(0, 5, 20, 5);
        gbcFonoField.gridx = 1;
        gbcFonoField.gridy = 6;
        gbcFonoField.insets = new Insets(0, 5, 20, 5);
        addPlaceholder(fonoField, "(Teléfono actual: " + ((String) datos[6]) + ")");

        FocusListener focusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String placeholder = (String) textField.getClientProperty("placeholder");

                if (placeholder != null && placeholder.equals(textField.getText())) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String placeholder = (String) textField.getClientProperty("placeholder");

                if (placeholder != null && textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        };

        nombreField.addFocusListener(focusListener);
        apellidoField.addFocusListener(focusListener);
        emailField.addFocusListener(focusListener);
        fonoField.addFocusListener(focusListener);

        actualizarClientePanel.add(nombreLabel, gbcNombreLabel);
        actualizarClientePanel.add(nombreField, gbcNombreField);
        actualizarClientePanel.add(apellidoLabel, gbcApellidoLabel);
        actualizarClientePanel.add(apellidoField, gbcApellidoField);
        actualizarClientePanel.add(edadLabel, gbcEdadLabel);
        actualizarClientePanel.add(edadSpinner, gbcEdadField);
        actualizarClientePanel.add(emailLabel, gbcEmailLabel);
        actualizarClientePanel.add(emailField, gbcEmailField);
        actualizarClientePanel.add(fonoLabel, gbcFonoLabel);
        actualizarClientePanel.add(fonoField, gbcFonoField);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setPreferredSize(new Dimension(150, 32));
        btnActualizar.setFont(new Font("Arial", Font.PLAIN, btnFontSize));
        gbcBtn.gridx = 0;
        gbcBtn.gridy = 7;
        gbcBtn.gridwidth = 2;
        gbcBtn.insets = new Insets(20, 5, 20, 5);

        actualizarClientePanel.add(btnActualizar, gbcBtn);

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

        setVisible(true);

        return actualizarClientePanel;
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

        JLabel rutLabel = new JLabel("RUT (con puntos y guión):");
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

        JLabel rutLabel = new JLabel("RUT del cliente (con puntos y guión):");
        JTextField rutField = new JTextField();

        JButton btnEliminar = new JButton("Eliminar");
        JButton btnVolver = new JButton("Volver");

        buscarClientePanel.add(rutLabel);
        buscarClientePanel.add(rutField);

        buscarClientePanel.add(btnEliminar);
        buscarClientePanel.add(btnVolver);

        getContentPane().removeAll(); // Limpiar el contenido actual
        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout
        getContentPane().add(buscarClientePanel, BorderLayout.CENTER);

        // Configurar el tamaño y hacer visible la ventana
        setSize(350, 250);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarRut(rutField.getText())){
                    Object[] datos = controladorClientes.buscarCliente(rutField.getText());
                    if(datos.length > 0){
                        JOptionPane.showMessageDialog(buscarClientePanel, "Cliente encontrado en la base de datos", "Búsqueda exitosa", JOptionPane.INFORMATION_MESSAGE);
                        if(controladorClientes.eliminarCliente((Integer) datos[0])){
                            JOptionPane.showMessageDialog(buscarClientePanel, "El cliente " + datos[1] + " " + datos[2] + " ha sido eliminado exitosamente", "Eliminación de cuenta exitosa", JOptionPane.INFORMATION_MESSAGE);
                            gestionClientes();
                        }
                        else{
                            JOptionPane.showMessageDialog(buscarClientePanel, "No se pudo eliminar al cliente", "Eliminación fallida", JOptionPane.ERROR_MESSAGE);
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

        JLabel rutLabel = new JLabel("Ingrese RUT del cliente (con puntos y guión): ");
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
                gestionCuentasDeAhorro();
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

        JLabel rutLabel = new JLabel("RUT del cliente (con puntos y guión):");
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
        JLabel tituloLabel = new JLabel("Actualizar cuenta de ahorro");
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

        JLabel rutLabel = new JLabel("RUT del cliente (con puntos y guión):");
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
                gestionCuentasDeAhorro();
            }
        });
    }

    private void buscarCuentaDeAhorro() {
        setTitle("Buscar cuenta de ahorro por RUT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buscarCuentaDeAhorroPanel = new JPanel(new GridLayout(7, 2));

        JLabel rutLabel = new JLabel("RUT (con puntos y guión):");
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
                    Object[] datosCliente = controladorClientes.buscarCliente(rutField.getText());
                    if(datosCliente.length > 0) {
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
                        JOptionPane.showMessageDialog(buscarCuentaDeAhorroPanel, "No se encontró al cliente en la base de datos", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
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
                gestionCuentasDeAhorro();
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

        btnActualizarCuentaCorriente.addActionListener(new ActionListener() {
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
        });

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

        JLabel rutLabel = new JLabel("Ingrese RUT del cliente (con puntos y guión): ");
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

    private void actualizarCuentaCorriente() {
        setTitle("Actualizar cuenta corriente de un cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buscarClientePanel = new JPanel(new GridLayout(7, 2));

        JLabel rutLabel = new JLabel("RUT del cliente (con puntos y guión):");
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
                        if (!controladorClientes.hayCuentaCorriente(rut)) {
                            JOptionPane.showMessageDialog(buscarClientePanel, "El cliente no tiene cuenta corriente para actualizar", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(buscarClientePanel, "Cliente encontrado en la base de datos", "Búsqueda exitosa", JOptionPane.INFORMATION_MESSAGE);
                            ventanaActualizacionCuentaCorriente(datos, rut);
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
                gestionCuentasCorrientes();
            }
        });
    }

    private void ventanaActualizacionCuentaCorriente(Object[] datos, String rut) {
        getContentPane().removeAll();
        setTitle("Actualizar cuenta corriente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);

        // Crear un panel para organizar la interfaz
        JPanel ingresarDatosPanel = new JPanel(new BorderLayout());

        // Etiqueta para el encabezado
        JLabel tituloLabel = new JLabel("Actualizar cuenta corriente");
        JLabel ingreseDatosLabel = new JLabel("Ingrese los nuevos datos de la cuenta");
        tituloLabel.setHorizontalAlignment(JLabel.CENTER);
        tituloLabel.setHorizontalAlignment(JLabel.CENTER);
        ingreseDatosLabel.setHorizontalAlignment(JLabel.CENTER);
        ingresarDatosPanel.add(tituloLabel, BorderLayout.NORTH);
        ingresarDatosPanel.add(ingreseDatosLabel, BorderLayout.NORTH);

        // Panel para los campos de actualización
        JPanel actualizarCuentaCorrientePanel = new JPanel(new GridLayout(6, 2));

        JLabel saldoLabel = new JLabel("Nuevo saldo:");
        SpinnerModel saldoModel = new SpinnerNumberModel(1, 1, 10000000, 1);
        JSpinner saldoSpinner = new JSpinner(saldoModel);

        JLabel sobregiroLabel = new JLabel("Nuevo cupo de sobregiro:");
        SpinnerModel sobregiroModel = new SpinnerNumberModel(200000, 100000, 10000000, 50000);
        JSpinner sobregiroSpinner = new JSpinner(sobregiroModel);

        actualizarCuentaCorrientePanel.add(saldoLabel);
        actualizarCuentaCorrientePanel.add(saldoSpinner);
        actualizarCuentaCorrientePanel.add(sobregiroLabel);
        actualizarCuentaCorrientePanel.add(sobregiroSpinner);

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnVolver = new JButton("Volver");

        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnVolver);

        // Agregar los paneles al panel principal
        ingresarDatosPanel.add(actualizarCuentaCorrientePanel, BorderLayout.CENTER);
        ingresarDatosPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Agregar el panel principal a la ventana
        getContentPane().add(ingresarDatosPanel);

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarDatosActualizacionCuentaCorriente((Integer) saldoSpinner.getValue(), (Integer) sobregiroSpinner.getValue())){
                    if (controladorCuentasCorrientes.actualizarCuentaCorriente(rut, (Integer) saldoSpinner.getValue(), (Integer) sobregiroSpinner.getValue())) {
                        JOptionPane.showMessageDialog(getContentPane(), "Los datos de la cuenta corriente del cliente " + datos[1] + " " + datos[2] + " han sido actualizados", "Actualización completada", JOptionPane.INFORMATION_MESSAGE);
                        gestionCuentasCorrientes();
                    }
                    else {
                        JOptionPane.showMessageDialog(getContentPane(), "No se pudieron actualizar los datos del cliente", "Actualización fallida", JOptionPane.ERROR_MESSAGE);
                        gestionCuentasCorrientes();
                    }
                }
                else {
                    gestionCuentasCorrientes();
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionCuentasCorrientes();
            }
        });

        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);
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

    private void eliminarCuentaCorriente() {
        setTitle("Eliminar cuenta corriente de un cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buscarClientePanel = new JPanel(new GridLayout(7, 2));

        JLabel rutLabel = new JLabel("RUT del cliente (con puntos y guión)");
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
                        if (!controladorClientes.hayCuentaCorriente(rut)) {
                            JOptionPane.showMessageDialog(buscarClientePanel, "El cliente no tiene cuenta corriente para eliminar", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(buscarClientePanel, "Cliente encontrado en la base de datos", "Búsqueda exitosa", JOptionPane.INFORMATION_MESSAGE);
                            if (controladorCuentasCorrientes.eliminarCuentaCorriente((Integer) datos[0])){
                                JOptionPane.showMessageDialog(buscarClientePanel, "Cuenta corriente eliminada", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
                                gestionCuentasCorrientes();
                            }
                            else {
                                JOptionPane.showMessageDialog(buscarClientePanel, "No se pudo eliminar la cuenta de ahorro", "Eliminación fallida", JOptionPane.ERROR_MESSAGE);
                                gestionCuentasCorrientes();
                            }
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
                gestionCuentasCorrientes();
            }
        });
    }

    private void buscarCuentaCorriente() {
        setTitle("Buscar cuenta corriente por RUT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buscarCuentaCorrientePanel = new JPanel(new GridLayout(7, 2));

        JLabel rutLabel = new JLabel("RUT (con puntos y guión):");
        JTextField rutField = new JTextField();

        JButton btnBuscar = new JButton("Buscar");
        JButton btnVolver = new JButton("Volver");

        buscarCuentaCorrientePanel.add(rutLabel);
        buscarCuentaCorrientePanel.add(rutField);

        buscarCuentaCorrientePanel.add(btnBuscar);
        buscarCuentaCorrientePanel.add(btnVolver);

        getContentPane().removeAll(); // Limpiar el contenido actual
        getContentPane().setLayout(new BorderLayout()); // Usar BorderLayout
        getContentPane().add(buscarCuentaCorrientePanel, BorderLayout.CENTER);

        // Configurar el tamaño y hacer visible la ventana
        setSize(350, 250);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setVisible(true);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validarRut(rutField.getText())){
                    Object[] datosCliente = controladorClientes.buscarCliente(rutField.getText());
                    if(datosCliente.length > 0) {
                        if(controladorClientes.hayCuentaCorriente(rutField.getText())){
                            Object[] datos = controladorCuentasCorrientes.buscarCuentaCorriente(rutField.getText());
                            if(datos.length > 0)
                            {
                                JOptionPane.showMessageDialog(buscarCuentaCorrientePanel, "Cliente encontrado en la base de datos", "Búsqueda exitosa", JOptionPane.INFORMATION_MESSAGE);
                                mostrarDatosCuentaCorriente(datos);
                            }
                            else {
                                JOptionPane.showMessageDialog(buscarCuentaCorrientePanel, "No se encontró la cuenta corriente", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                                buscarCuentaCorriente();
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(buscarCuentaCorrientePanel, "El cliente no tiene cuenta corriente", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(buscarCuentaCorrientePanel, "No se encontró al cliente en la base de datos", "Búsqueda fallida", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(buscarCuentaCorrientePanel, "Ingrese un RUT válido", "RUT inválido", JOptionPane.ERROR_MESSAGE);
                    buscarCuentaCorriente();
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

    private void mostrarDatosCuentaCorriente(Object[] datos) {
        getContentPane().removeAll();
        setTitle("Datos de la cuenta corriente:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 150);

        String[] columnas = {"ID de cuenta", "Nombre", "Apellido", "RUT", "Saldo", "Sobregiro"};

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
                mostrarDatosCuentaCorriente(datos);
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

    private boolean validarDatosActualizacionCuentaCorriente(int saldo, int sobregiro) {
        if (!validarSaldo(saldo)) {
            JOptionPane.showMessageDialog(getContentPane(), "Saldo inválido", "Actualización fallida", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!validarSobregiro(sobregiro)) {
            JOptionPane.showMessageDialog(getContentPane(), "Monto de sobregiro inválido", "Actualización fallida", JOptionPane.ERROR_MESSAGE);
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
