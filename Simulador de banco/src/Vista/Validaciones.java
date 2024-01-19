package Vista;

import Controlador.*;

import java.util.Scanner;


public class Validaciones {
    private static ControladorClientes controladorClientes;
    private static ControladorCuentasDeAhorro controladorCuentasDeAhorro;
    private static ControladorCuentasCorrientes controladorCuentasCorrientes;

    private static int edadMinima = Integer.parseInt(System.getenv("edadMinima"));
    private static int edadMaxima = Integer.parseInt(System.getenv("edadMaxima"));
    private static int saldoMinimo = Integer.parseInt(System.getenv("saldoMinimo"));
    private static int saldoMaximo = Integer.parseInt(System.getenv("saldoMaximo"));
    private static int sobregiroMinimo = Integer.parseInt(System.getenv("sobregiroMinimo"));
    private static int sobregiroMaximo = Integer.parseInt(System.getenv("sobregiroMaximo"));
    private static int topeMinimoMinimo = Integer.parseInt(System.getenv("topeMinimoMinimo"));
    private static int topeMinimoMaximo = Integer.parseInt(System.getenv("topeMinimoMaximo"));

    private static int tasaInteresMinimo = Integer.parseInt(System.getenv("tasaInteresMinimo"));
    private static int tasaInteresMaximo = Integer.parseInt(System.getenv("tasaInteresMaximo"));

    private static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\nMenú principal:");
                System.out.println("\n1. Gestión de clientes");
                System.out.println("2. Gestión de cuentas de ahorro");
                System.out.println("3. Gestión de cuentas corrientes");
                System.out.println("4. Salir");

                System.out.print("\nSeleccione una opción: ");
                int opcion = scanner.nextInt();

                if (opcion >= 1 && opcion <= 4) {
                    switch (opcion) {
                        case 1:
                            menuClientes(scanner);
                            break;
                        case 2:
                            menuCuentasDeAhorro(scanner);
                            break;
                        case 3:
                            menuCuentasCorrientes(scanner);
                            break;
                        case 4:
                            System.out.println("Saliendo del programa...");
                            System.exit(0);
                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                    }
                }
                else {
                    System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();  // Limpiar el búfer de entrada
            }
        }
    }

    private static void menuClientes(Scanner scanner) {

        while (true) {
            try {
                System.out.println("\nGestionar clientes:");
                System.out.println("\n1. Ingresar cliente");
                System.out.println("2. Lista de clientes");
                System.out.println("3. Actualizar datos de un cliente");
                System.out.println("4. Eliminar cliente");
                System.out.println("5. Buscar cliente");
                System.out.println("6. Volver al menú principal");

                System.out.print("\nSeleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        ingresarCliente(scanner);
                        break;
                    case 2:
                        listaClientes(scanner);
                        break;
                    case 3:
                        actualizarCliente();
                        break;
                    case 4:
                        eliminarCliente();
                        break;
                    case 5:
                        buscarCliente();
                        break;
                    case 6:
                        mostrarMenu();
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();  // Limpiar el búfer de entrada
            }
        }
    }

    private static void ingresarCliente (Scanner scanner) {

        System.out.println("\nIngrese los datos del nuevo cliente:");
        System.out.println();

        String nombre = capturarNombre();
        String apellido = capturarApellido();
        String email = capturarEmail();
        String rut = capturarRut();
        String fono = capturarTelefono();

        try {
            int cantClientes = controladorClientes.getCantidadClientes();
            //controladorClientes.crearCliente(nombre, apellido, edad, email, rut, fono);
            System.out.println("\nCliente creado con éxito.");
        } catch (Exception e) {
            System.out.println("Error al crear el cliente: " + e.getMessage());
        }
    }

    private static void listaClientes(Scanner scanner) {
        System.out.println();

        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Clientes disponibles: ");
            System.out.println();
            controladorClientes.mostrarDetallesClientes();
            try {
                // Opciones adicionales
                System.out.println("1. Volver al menú anterior");
                System.out.println("2. Volver al menú principal");
                System.out.println();

                System.out.print("Seleccione una opción: ");
                int opcionCliente = scanner.nextInt();

                if (opcionCliente <= 4 && opcionCliente >= 1) {
                    switch (opcionCliente) {
                        case 1:
                            // Volver al menú anterior
                            menuClientes(scanner);
                            break;
                        case 2:
                            // Volver al menú principal
                            mostrarMenu();
                            break;
                        default:
                            System.out.println("Opción no válida. Volviendo al menú anterior.");
                    }
                }
                else {
                    System.out.println("Opción no válida. Volviendo al menú anterior.");
                }
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();  // Limpiar el búfer de entrada
            }
        }
    }

    private static void actualizarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Buscar al cliente por su RUT
            System.out.println("Ingrese el rut del cliente: ");
            System.out.println();
            String rut = scanner.next();

            if (controladorClientes.encontrarClientePorRUT(rut) != null) {
                String nombreCliente = controladorClientes.encontrarClientePorRUT(rut).getNombre();

                System.out.println("Cliente encontrado");
                System.out.println("Nombre: " + nombreCliente + ", ID: " + controladorClientes.encontrarClientePorRUT(rut).getId());

                System.out.println("\nIngrese los nuevos datos del cliente:");
                System.out.println();
                String email = capturarEmail();
                String fono = capturarTelefono();

                //controladorClientes.actualizarCliente(rut, email, fono);

                // Opciones adicionales
                System.out.println("\n1. Volver a gestión de clientes");
                System.out.println("2. Volver al menú principal");

                System.out.println();

                System.out.print("Seleccione una opción: ");
                int opcionCliente = scanner.nextInt();

                if (opcionCliente == 1 | opcionCliente == 2){
                    switch (opcionCliente) {
                        case 1:
                            // Volver al menú anterior
                            menuClientes(scanner);
                            break;
                        case 2:
                            // Volver al menú principal
                            mostrarMenu();
                            break;
                        default:
                            System.out.println("Opción no válida. Volviendo al menú anterior.");
                    }
                }
                else {
                    System.out.println("Opción no válida. Volviendo al menú anterior.");
                }
            }

            else {
                System.out.println("Cliente no encontrado, intente nuevamente");
                menuClientes(scanner);
            }
        }
    }

    private static void eliminarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Ingrese el rut del cliente: ");
            System.out.println();
            String rut = scanner.next();

            if (controladorClientes.encontrarClientePorRUT(rut) != null) {
                String nombreCliente = controladorClientes.encontrarClientePorRUT(rut).getNombre();

                System.out.println("Cliente encontrado");
                System.out.println("Nombre: " + nombreCliente + ", ID: " + controladorClientes.encontrarClientePorRUT(rut).getId());

                System.out.println("\nIngrese 1 para borrar al cliente de la base de datos");
                System.out.println("\nIngrese 0 para volver al menú de gestión de clientes");

                int seleccion = scanner.nextInt();

                if (seleccion == 1) {
                    controladorClientes.eliminarCliente(rut);
                    menuClientes(scanner);
                }

                else {
                    menuClientes(scanner);
                }
            }

            else {
                System.out.println("Cliente no encontrado, intente nuevamente");
                menuClientes(scanner);
            }
        }
    }

    private static void buscarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Ingrese el rut del cliente: ");
            System.out.println();
            String rut = scanner.next();
            controladorClientes.buscarCliente(rut);
        }
    }

    private static void menuCuentasDeAhorro(Scanner scanner) {

        while (true) {
            try {
                System.out.println("\nGestionar cuentas de ahorro:");
                System.out.println("\n1. Crear cuenta de ahorro");
                System.out.println("2. Lista de cuentas de ahorro");
                System.out.println("3. Eliminar cuenta de ahorro");
                System.out.println("4. Buscar cuenta de ahorro");
                System.out.println("5. Volver al menú principal");

                System.out.print("\nSeleccione una opción: ");
                int opcion = scanner.nextInt();

                if (opcion >= 1 && opcion <= 5) {
                    switch (opcion) {
                        case 1:
                            crearCuentaDeAhorro(scanner);
                            break;
                        case 2:
                            listaCuentasDeAhorro(scanner);
                            break;
                        case 3:
                            eliminarCuentaDeAhorro(scanner);
                            break;
                        case 4:
                            buscarCuentaDeAhorro(scanner);
                            break;
                        case 5:
                            mostrarMenu();
                            break;
                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                    }
                }
                else {
                    System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();  // Limpiar el búfer de entrada
            }
        }
    }

    private static void crearCuentaDeAhorro (Scanner scanner) {

        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Buscar al cliente por su RUT
            System.out.println("Ingrese el rut del cliente: ");
            System.out.println();
            String rut = scanner.next();

            if (controladorClientes.encontrarClientePorRUT(rut) != null) {
                String nombreCliente = controladorClientes.encontrarClientePorRUT(rut).getNombre();

                System.out.println("Cliente encontrado");
                System.out.println("Nombre: " + nombreCliente + ", ID: " + controladorClientes.encontrarClientePorRUT(rut).getId());

                if (controladorClientes.hayCuentaAhorro(rut)){
                    System.out.println("El cliente ya tiene una cuenta de ahorro.");
                    menuCuentasDeAhorro(scanner);
                }

                else {

                    int idCliente = controladorClientes.encontrarClientePorRUT(rut).getId();

                    System.out.println("\nIngrese los datos de la cuenta de ahorro");
                    System.out.println();

                    System.out.print("Saldo inicial: ");
                    int saldo = scanner.nextInt();

                    System.out.print("Tasa de interés: ");
                    float tasa = scanner.nextFloat();

                    System.out.print("Saldo mínimo para hacer retiros: ");
                    int tope = scanner.nextInt();

                    try {
                        controladorCuentasDeAhorro.crearCuentaDeAhorro(idCliente, saldo, tasa, tope);
                        System.out.println("\nCuenta de ahorro creada con éxito.");
                    } catch (Exception e) {
                        System.out.println("Error al crear la cuenta de ahorro: " + e.getMessage());
                    }
                }
            }

            else {
                System.out.println("Cliente no encontrado, intente nuevamente");
                menuCuentasDeAhorro(scanner);
            }
        }
    }

    private static void listaCuentasDeAhorro(Scanner scanner) {
        System.out.println();

        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay cuentas de ahorro en la base de datos.");
        } else {
            // Mostrar la lista de cuentas de ahorro
            System.out.println("Cuentas de ahorro en el banco: ");
            System.out.println();
            controladorCuentasDeAhorro.mostrarDetallesCuentasDeAhorro();
            try {
                // Opciones adicionales
                System.out.println("1. Volver al menú anterior");
                System.out.println("2. Volver al menú principal");
                System.out.println();

                System.out.print("Seleccione una opción: ");
                int opcionCliente = scanner.nextInt();

                switch (opcionCliente) {
                    case 1:
                        // Volver al menú anterior
                        menuCuentasDeAhorro(scanner);
                        break;
                    case 2:
                        // Volver al menú principal
                        mostrarMenu();
                        break;
                    default:
                        System.out.println("Opción no válida. Volviendo al menú anterior.");
                }
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();  // Limpiar el búfer de entrada
            }
        }
    }

    private static void eliminarCuentaDeAhorro(Scanner scanner) {
        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados en el banco.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Ingrese el rut del cliente para eliminar su cuenta de ahorro: ");
            System.out.println();
            String rut = scanner.next();

            if (controladorClientes.encontrarClientePorRUT(rut) != null) {
                String nombreCliente = controladorClientes.encontrarClientePorRUT(rut).getNombre();

                System.out.println("Cliente encontrado");
                System.out.println("Nombre: " + nombreCliente + ", ID: " + controladorClientes.encontrarClientePorRUT(rut).getId());

                if (controladorClientes.hayCuentaAhorro(rut)) {
                    System.out.println();
                    System.out.println("Datos de la cuenta:");
                    System.out.println("ID: " + controladorClientes.encontrarClientePorRUT(rut).getCuentaDeAhorro().getId() + ", Saldo: " + controladorClientes.encontrarClientePorRUT(rut).getCuentaDeAhorro().getSaldo());

                    System.out.println("\nIngrese 1 para borrar la cuenta de ahorro del cliente");
                    System.out.println("\nIngrese 0 para volver al menú de gestión de clientes");

                    int seleccion = scanner.nextInt();

                    if (seleccion == 1) {
                        controladorCuentasDeAhorro.eliminarCuentaDeAhorro(rut);
                        menuCuentasDeAhorro(scanner);
                    }

                    else {
                        menuCuentasDeAhorro(scanner);
                    }
                }
                else {
                    System.out.println("El cliente seleccionado no posee una cuenta de ahorro");
                    menuCuentasDeAhorro(scanner);
                }
            }

            else {
                System.out.println("Cliente no encontrado");
                menuCuentasDeAhorro(scanner);
            }
        }
    }

    private static void buscarCuentaDeAhorro(Scanner scanner) {
        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados en el banco.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Ingrese el rut del cliente: ");
            System.out.println();
            String rut = scanner.next();
            controladorCuentasDeAhorro.buscarCuentaDeAhorro(rut);
            menuCuentasDeAhorro(scanner);
        }
    }

    private static void menuCuentasCorrientes(Scanner scanner){
        while (true) {
            try {
                System.out.println("\nGestionar cuentas corrientes:");
                System.out.println("\n1. Crear cuenta corriente");
                System.out.println("2. Lista de cuentas corrientes");
                System.out.println("3. Eliminar cuenta corriente");
                System.out.println("4. Buscar cuenta corriente");
                System.out.println("5. Volver al menú principal");

                System.out.print("\nSeleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        crearCuentaCorriente(scanner);
                        break;
                    case 2:
                        listaCuentasCorrientes(scanner);
                        break;
                    case 3:
                        eliminarCuentaCorriente(scanner);
                        break;
                    case 4:
                        buscarCuentaCorriente(scanner);
                        break;
                    case 5:
                        mostrarMenu();
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();  // Limpiar el búfer de entrada
            }
        }
    }

    private static void crearCuentaCorriente (Scanner scanner) {

        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados.");
        } else {
            // Buscar al cliente por su RUT
            System.out.println("Ingrese el rut del cliente: ");
            System.out.println();
            String rut = scanner.next();

            if (controladorClientes.encontrarClientePorRUT(rut) != null) {
                String nombreCliente = controladorClientes.encontrarClientePorRUT(rut).getNombre();

                System.out.println("Cliente encontrado");
                System.out.println("Nombre: " + nombreCliente + ", ID: " + controladorClientes.encontrarClientePorRUT(rut).getId());

                if (controladorClientes.hayCuentaCorriente(rut)){
                    System.out.println("El cliente ya tiene una cuenta corriente.");
                    menuCuentasCorrientes(scanner);
                }

                else {

                    int idCliente = controladorClientes.encontrarClientePorRUT(rut).getId();

                    System.out.println("\nIngrese los datos de la cuenta corriente");
                    System.out.println();

                    int saldo = capturarSaldo();
                    int sobregiro = capturarSobregiro();

                    try {
                        controladorCuentasCorrientes.crearCuentaCorriente(idCliente, saldo, sobregiro);
                        System.out.println("\nCuenta corriente creada con éxito.");
                    } catch (Exception e) {
                        System.out.println("Error al crear la cuenta corriente: " + e.getMessage());
                    }
                }
            }

            else {
                System.out.println("Cliente no encontrado, intente nuevamente");
                menuCuentasCorrientes(scanner);
            }
        }
    }

    private static void listaCuentasCorrientes(Scanner scanner) {
        System.out.println();

        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay cuentas corrientes en la base de datos.");
        } else {
            // Mostrar la lista de cuentas corrientes
            System.out.println("Cuentas corrientes en el banco: ");
            System.out.println();
            controladorCuentasCorrientes.mostrarDetallesCuentasCorrientes();
            try {
                // Opciones adicionales
                System.out.println("1. Volver al menú anterior");
                System.out.println("2. Volver al menú principal");
                System.out.println();

                System.out.print("Seleccione una opción: ");
                int opcionCliente = scanner.nextInt();

                if (opcionCliente >= 1 && opcionCliente <= 4) {
                    switch (opcionCliente) {
                        case 1:
                            // Volver al menú anterior
                            menuCuentasCorrientes(scanner);
                            break;
                        case 2:
                            // Volver al menú principal
                            mostrarMenu();
                            break;
                        default:
                            System.out.println("Opción no válida. Volviendo al menú anterior.");
                    }
                }
                else {
                    System.out.println("Opción no válida. Volviendo al menú anterior.");
                }
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();  // Limpiar el búfer de entrada
            }
        }
    }

    private static void eliminarCuentaCorriente(Scanner scanner) {
        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados en el banco.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Ingrese el rut del cliente para eliminar su cuenta corriente: ");
            System.out.println();
            String rut = scanner.next();

            if (controladorClientes.encontrarClientePorRUT(rut) != null) {
                String nombreCliente = controladorClientes.encontrarClientePorRUT(rut).getNombre();

                System.out.println("Cliente encontrado");
                System.out.println("Nombre: " + nombreCliente + ", ID: " + controladorClientes.encontrarClientePorRUT(rut).getId());

                if (controladorClientes.hayCuentaCorriente(rut)) {
                    System.out.println();
                    System.out.println("Datos de la cuenta:");
                    System.out.println("ID: " + controladorClientes.encontrarClientePorRUT(rut).getCuentaCorriente().getId() + ", Saldo: " + controladorClientes.encontrarClientePorRUT(rut).getCuentaCorriente().getSaldo());

                    System.out.println("\nIngrese 1 para borrar la cuenta de ahorro del cliente");
                    System.out.println("\nIngrese 0 para volver al menú de gestión de clientes");

                    int seleccion = scanner.nextInt();

                    if (seleccion == 1) {
                        controladorCuentasCorrientes.eliminarCuentaCorriente(rut);
                        menuCuentasCorrientes(scanner);
                    }

                    else {
                        menuCuentasCorrientes(scanner);
                    }
                }
                else {
                    System.out.println("El cliente seleccionado no posee una cuenta corriente");
                    menuCuentasCorrientes(scanner);
                }
            }

            else {
                System.out.println("Cliente no encontrado");
                menuCuentasCorrientes(scanner);
            }
        }
    }

    private static void buscarCuentaCorriente(Scanner scanner) {
        if (!controladorClientes.hayClientes()) {
            System.out.println("No hay clientes registrados en el banco.");
        } else {
            // Mostrar la lista de clientes con números asociados
            System.out.println("Ingrese el rut del cliente: ");
            System.out.println();
            String rut = scanner.next();
            controladorCuentasCorrientes.buscarCuentaCorriente(rut);
            menuCuentasCorrientes(scanner);
        }
    }

    public static void main(String[] args) {
        controladorClientes = new ControladorClientes();
        controladorCuentasDeAhorro = new ControladorCuentasDeAhorro();
        controladorCuentasCorrientes = new ControladorCuentasCorrientes();
        ControladorAdministradores controladorAdministradores = new ControladorAdministradores();

        if (controladorCuentasDeAhorro.hayCuentasDeAhorro()){
            controladorClientes.agregarCuentasDeAhorro(controladorCuentasDeAhorro.getCuentasDeAhorro());
        }

        if (controladorCuentasCorrientes.hayCuentasCorrientes()){
            controladorClientes.agregarCuentasCorrientes(controladorCuentasCorrientes.getCuentasCorrientes());
        }

        Scanner scanner = new Scanner(System.in);
        String rut;
        String contrasena;

        System.out.println("\n¡Hola! Bienvenid@ al módulo de administración del Banco Virtual");
        System.out.println("Ingrese su rut: ");
        System.out.println();
        rut = capturarRut();
        System.out.println();
        System.out.println("Ingrese su contraseña: ");
        contrasena = capturarContrasena();

        if (controladorAdministradores.login(rut, contrasena)) {
            System.out.println("\nInicio de sesión exitoso");
            mostrarMenu();
        }
        else {
            System.out.println("\nLos datos ingresados no corresponden a una cuenta autorizada");
            System.out.println("Cerrando el programa...");
        }
    }

    public static boolean validarNombre(String nombre) {
        if (nombre != null) {
            String regex = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+$";
            return !nombre.isEmpty() && nombre.matches(regex) && (nombre.length() >= 3) && (nombre.length() <= 12);
        }
        else {
            return false;
        }
    }

    public static boolean validarApellido(String apellido) {
        if (apellido != null) {
            String regex = "^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+$";
            return !apellido.isEmpty() && apellido.matches(regex) && (apellido.length() >= 3) && (apellido.length() <= 11);
        }
        else {
            return false;
        }
    }

    public static boolean validarEdad(int edad) {
        return edad >= edadMinima && edad <= edadMaxima;
    }

    public static boolean validarEmail(String email) {
        if (email != null) {
            String regex = "^[A-Za-z0-9]+([_.+-][A-Za-z0-9]+)*@[A-Za-z0-9]+([.-][A-Za-z0-9]+)*$";
            return email.matches(regex) && email.length() >= 5 && email.length() <= 30;
        }
        else {
            return false;
        }
    }
    public static boolean validarRut(String rut) {

        boolean validacion = false;
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }

    public static boolean validarTelefono(String telefono) {
        if (telefono != null) {
            String regex = "^(\\+?56)?\\d{6,10}$";
            return telefono.matches(regex) && telefono.length() >= 8 && telefono.length() <= 16;
        }
        else {
            return false;
        }
    }

    public static boolean validarSaldo(int saldo) {
        return saldo >= saldoMinimo && saldo < saldoMaximo;
    }
    public static boolean validarTasaInteres(double tasaInteres) {
        return tasaInteres > tasaInteresMinimo && tasaInteres < tasaInteresMaximo;
    }
    public static boolean validarTopeMinimo(int topeMinimo) {
        return topeMinimo >= topeMinimoMinimo && topeMinimo <= topeMinimoMaximo;
    }

    public static boolean validarSobregiro(int sobregiro) {
        return sobregiro > sobregiroMinimo && sobregiro < sobregiroMaximo;
    }

    public static boolean validarContrasena(String contrasena) {
        if (contrasena != null) {
            // Al menos un número, una letra mayúscula y longitud entre 5 y 10 caracteres
            String regex = "^(?=.*[0-9])(?=.*[A-Z]).{5,10}$";
            return contrasena.matches(regex);
        }
        else {
            return false;
        }
    }

    private static String capturarNombre() {
        Scanner scanner = new Scanner(System.in);
        String nombre;
        do {
            System.out.print("Nombre: ");
            nombre = scanner.next();
            if (!validarNombre(nombre)) {
                System.out.println("Nombre no válido.");
                System.out.println("Ingrese un nombre válido.");
            }
        } while (!validarNombre(nombre));
        return nombre;
    }

    private static String capturarApellido() {
        Scanner scanner = new Scanner(System.in);
        String apellido;
        do {
            System.out.print("Apellido: ");
            apellido = scanner.next();
            if (!validarApellido(apellido)) {
                System.out.println("Apellido no válido.");
                System.out.println("Ingrese un apellido válido.");
            }
        } while (!validarApellido(apellido));
        return apellido;
    }

    private static String capturarEmail() {
        Scanner scanner = new Scanner(System.in);
        String email;
        do {
            System.out.print("Email: ");
            email = scanner.next();
            if (!validarEmail(email)) {
                System.out.println("Email no válido.");
                System.out.println("Ingrese un email válido.");
            }
        } while (!validarEmail(email));
        return email;
    }

    private static String capturarRut() {
        Scanner scanner = new Scanner(System.in);
        String rut;
        do {
            System.out.print("RUT: ");
            rut = scanner.next();
            if (!validarRut(rut)) {
                System.out.println("RUT no válido.");
                System.out.println("Ingrese un RUT válido.");
            }
        } while (!validarRut(rut));
        return rut;
    }

    private static String capturarTelefono() {
        Scanner scanner = new Scanner(System.in);
        String telefono;
        do {
            System.out.print("Teléfono: ");
            telefono = scanner.next();
            if (!validarTelefono(telefono)) {
                System.out.println("Teléfono no válido.");
                System.out.println("Ingrese un teléfono válido.");
            }
        } while (!validarTelefono(telefono));
        return telefono;
    }

    private static int capturarSaldo() {
        Scanner scanner = new Scanner(System.in);
        int saldo;
        do {
            System.out.println("Saldo inicial");
            saldo = scanner.nextInt();
            if (!validarSaldo(saldo)) {
                System.out.println("Saldo inicial no válido.");
                System.out.println("Ingrese un saldo inicial válido.");
            }
        } while (!validarSaldo(saldo));
        return saldo;
    }

    private static int capturarSobregiro() {
        Scanner scanner = new Scanner(System.in);
        int sobregiro;
        do {
            System.out.println("Cupo de sobregiro");
            sobregiro = scanner.nextInt();
            if (!validarSobregiro(sobregiro)) {
                System.out.println("Cupo de sobregiro no válido.");
                System.out.println("Ingrese un cupo de sobregiro válido.");
            }
        } while (!validarSobregiro(sobregiro));
        return sobregiro;
    }

    private static String capturarContrasena() {
        Scanner scanner = new Scanner(System.in);
        String contrasena;
        do {
            System.out.print("Contraseña: ");
            contrasena = scanner.next();
            if (!validarContrasena(contrasena)) {
                System.out.println("RUT no válido.");
                System.out.println("Ingrese una contraseña de entre 5 y 10 caracteres");
                System.out.println("La contraseña debe tener al por lo menos una letra mayúscula y un número");
            }
        } while (!validarContrasena(contrasena));
        return contrasena;
    }
}
