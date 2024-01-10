package Controlador;

import Modelo.*;
import DAO.*;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import java.util.Collections;
import java.util.Comparator;

public class ControladorClientes {
    private List<Cliente> clientes;
    private ClienteDAO clienteDAO;

    public ControladorClientes() {
        this.clienteDAO = new ClienteDAO();
        actualizarClientes();
    }

    public void actualizarClientes() {
        this.clientes = clienteDAO.obtenerTodosLosClientes();
    }

    public void crearCliente(String nombre, String apellido, String email, String rut, String fono) {
        try {
            Cliente cliente = new Cliente(nombre, apellido, email, rut, fono);
            clientes.add(cliente);
            clienteDAO.agregarCliente(cliente);
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo agregar el cliente. Error: " + e.getMessage());
        }
    }

    public void mostrarDetallesCliente(Cliente cliente){
        if (cliente != null) {
            Cliente clienteSeleccionado = encontrarClientePorId(cliente.getId());
            String cuentaAhorro = (hayCuentaAhorro(cliente.getRut())) ? "Sí" : "No";
            String cuentaCorriente = (hayCuentaCorriente(cliente.getRut())) ? "Sí" : "No";

            // Mostrar detalles del cliente
            System.out.print("ID: " + clienteSeleccionado.getId() + " | ");
            System.out.print("Nombre: " + clienteSeleccionado.getNombre() + " | ");
            System.out.print("Apellido: " + clienteSeleccionado.getApellido() + " | ");
            System.out.print("Rut: " + clienteSeleccionado.getRut() + " | ");
            System.out.print("Email: " + clienteSeleccionado.getEmail() + " | ");
            System.out.println("Teléfono: " + clienteSeleccionado.getFono());
            System.out.print(" | ¿Tiene cuenta de ahorro? " + cuentaAhorro + " | ");
            System.out.println("¿Tiene cuenta corriente? " + cuentaCorriente);
        }
    }

    private void ordenarClientesPorId() {
        Collections.sort(clientes, Comparator.comparingInt(Cliente::getId));
    }

    public void mostrarDetallesClientes(){

        actualizarClientes();

        if (!hayClientes()){
            System.out.println("No hay clientes registrados en este momento");
        }
        else {
            ControladorCuentasDeAhorro cont = new ControladorCuentasDeAhorro();
            agregarCuentasDeAhorro(cont.getCuentasDeAhorro());
            ControladorCuentasCorrientes contC = new ControladorCuentasCorrientes();
            agregarCuentasCorrientes(contC.getCuentasCorrientes());
            ordenarClientesPorId();
            int cantClientes = this.getCantidadClientes();
            for (int i = 0; i < cantClientes; i++) {
                System.out.print("- ");
                mostrarDetallesCliente(clientes.get(i));
                System.out.println();
            }
        }
    }

    public void actualizarCliente(String rut, String email, String fono) {
        try {
            Cliente cliente = encontrarClientePorRUT(rut);
            /*
            cliente.setEmail(email);
            cliente.setFono(fono);
            */
            clienteDAO.actualizarCliente(cliente, email, fono);
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudieron actualizar los datos del cliente. Error: " + e.getMessage());
        }
    }

    public void eliminarCliente(String rut) {
        try {
            Cliente cliente = encontrarClientePorRUT(rut);
            if (cliente != null) {
                ControladorCuentasDeAhorro controladorCuentasDeAhorro = new ControladorCuentasDeAhorro();
                controladorCuentasDeAhorro.eliminarCuentaDeAhorro(cliente.getRut());
                ControladorCuentasCorrientes controladorCuentasCorrientes = new ControladorCuentasCorrientes();
                controladorCuentasCorrientes.eliminarCuentaCorriente(cliente.getRut());
                clientes.remove(cliente);
                clienteDAO.eliminarCliente(cliente.getId());
            }
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo eliminar al cliente de la base de datos. Error: " + e.getMessage());
        }
    }

    public void buscarCliente(String rut) {
        Cliente cliente = encontrarClientePorRUT(rut);
        if (cliente != null){
            System.out.println("Cliente encontrado en la base de datos");
            System.out.println();
            mostrarDetallesCliente(cliente);
        }
        else {
            System.out.println("Cliente no encontrado en la base de datos");
        }
    }

    public Cliente encontrarClientePorRUT(String rut) {
        actualizarCuentas();
        for (Cliente cliente : clientes) {
            if (cliente.getRut().equals(rut)) {
                return cliente;
            }
        }
        return null;
    }
    public void actualizarCuentas() {
        ControladorCuentasDeAhorro controladorCuentasDeAhorro = new ControladorCuentasDeAhorro();
        ControladorCuentasCorrientes controladorCuentasCorrientes = new ControladorCuentasCorrientes();

        if (controladorCuentasDeAhorro.hayCuentasDeAhorro()){
            this.agregarCuentasDeAhorro(controladorCuentasDeAhorro.getCuentasDeAhorro());
        }

        if (controladorCuentasCorrientes.hayCuentasCorrientes()){
            this.agregarCuentasCorrientes(controladorCuentasCorrientes.getCuentasCorrientes());
        }
    }

    public Cliente encontrarClientePorId(int idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    /*public boolean hayCuentaAhorro(int id){
        Cliente clienteSeleccionado = clientes.get(id);
        if (clienteSeleccionado.getCuentaDeAhorro() != null){
            return true;
        }
        else {
            return false;
        }
    }*/

    public boolean hayCuentaAhorro(String rut){
        Cliente clienteSeleccionado = encontrarClientePorRUT(rut);
        ControladorCuentasDeAhorro controladorCuentasDeAhorro = new ControladorCuentasDeAhorro();

        if (controladorCuentasDeAhorro.tieneCuentaDeAhorro(clienteSeleccionado)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hayCuentaCorriente(String rut){
        Cliente clienteSeleccionado = encontrarClientePorRUT(rut);
        ControladorCuentasCorrientes controladorCuentasCorrientes = new ControladorCuentasCorrientes();

        if (controladorCuentasCorrientes.tieneCuentaCorriente(clienteSeleccionado)){
            return true;
        }
        else {
            return false;
        }
    }
    public int getCantidadClientes() {
        if (clientes != null){
            return clientes.size();
        }
        else {
            return 0;
        }
    }

    public boolean hayClientes() {
        return !clientes.isEmpty();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void agregarCuentaDeAhorro(CuentaDeAhorro cuenta) {
        List<Cliente> lista = new ArrayList<>();
        for (int i = 0; i < clientes.size(); i++){
            if (clientes.get(i).getId() == cuenta.getIdCliente()){
                Cliente cliente = clientes.get(i);
                cliente.setCuentaDeAhorro(cuenta);
                lista.add(cliente);
            }
            else {
                lista.add(clientes.get(i));
            }
        }
        this.clientes = lista;
    }

    public void agregarCuentasDeAhorro(List<CuentaDeAhorro> cuentasDeAhorro) {
        for (int i = 0; i < cuentasDeAhorro.size(); i++){
            agregarCuentaDeAhorro(cuentasDeAhorro.get(i));
        }
    }

    public void eliminarCuentaDeAhorro(CuentaDeAhorro cuenta) {
        List<Cliente> lista = new ArrayList<>();
        for (int i = 0; i < clientes.size(); i++){
            if (clientes.get(i).getId() == cuenta.getIdCliente()){
                Cliente cliente = clientes.get(i);
                cliente.setCuentaDeAhorro(null);
                lista.add(cliente);
            }
            else {
                lista.add(clientes.get(i));
            }
        }
        this.clientes = lista;
    }

    public void agregarCuentaCorriente(CuentaCorriente cuenta) {
        List<Cliente> lista = new ArrayList<>();
        for (int i = 0; i < clientes.size(); i++){
            if (clientes.get(i).getId() == cuenta.getIdCliente()){
                Cliente cliente = clientes.get(i);
                cliente.setCuentaCorriente(cuenta);
                lista.add(cliente);
            }
            else {
                lista.add(clientes.get(i));
            }
        }
        this.clientes = lista;
    }

    public void agregarCuentasCorrientes(List<CuentaCorriente> cuentasCorrientes) {
        for (int i = 0; i < cuentasCorrientes.size(); i++){
            agregarCuentaCorriente(cuentasCorrientes.get(i));
        }
    }

    public void eliminarCuentaCorriente(CuentaCorriente cuenta) {
        List<Cliente> lista = new ArrayList<>();
        for (int i = 0; i < clientes.size(); i++){
            if (clientes.get(i).getId() == cuenta.getIdCliente()){
                Cliente cliente = clientes.get(i);
                cliente.setCuentaCorriente(null);
                lista.add(cliente);
            }
            else {
                lista.add(clientes.get(i));
            }
        }
        this.clientes = lista;
    }

    /*

    public void crearCuentaAhorro(int idCliente, int saldoInicial, String tipo, int tasaInteres, int topeMinimo) {
        Cliente cliente = encontrarClientePorId(idCliente);
        if (cliente != null) {
            CuentaAhorro cuenta = new CuentaAhorro(idCliente, saldoInicial, tipo, tasaInteres, topeMinimo);
            cliente.agregarCuenta(cuenta);
            //conn.agregarCuentaAhorro(cuenta.getId(), idCliente, saldoInicial, tasaInteres, topeMinimo);
        } else {
            System.out.println("Error: Cliente no encontrado al crear cuenta de ahorro.");
        }
    }

    public int getIdCliente(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        return clienteSeleccionado.getId();
    }

    public void crearCuentaCorriente(int idCliente, int saldo, String tipo, int sobregiro) {
        Cliente cliente = encontrarClientePorId(idCliente);
        if (cliente != null) {
            CuentaCorriente cuenta = new CuentaCorriente(idCliente, saldo, tipo, sobregiro);
            cliente.agregarCuenta(cuenta);
            //conn.agregarCuentaCorriente(cuenta.getId(), idCliente, saldo, sobregiro);
        } else {
            System.out.println("Error: Cliente no encontrado al crear cuenta corriente.");
        }
    }

    */

    /*

    public void eliminarCliente(int idCliente) {
        Cliente cliente = encontrarClientePorId(idCliente);
        if (cliente != null) {
            clientes.remove(cliente);
            //conn.eliminarCliente(idCliente);
            System.out.println("Cliente eliminado con éxito.");
        } else {
            System.out.println("Error: Cliente no encontrado.");
        }
    }

    public void eliminarCuenta(CuentaBancaria cuenta) {
        for (Cliente cliente : clientes) {
            if (cliente.getCuentas().contains(cuenta)) {
                cliente.eliminarCuenta(cuenta);
                System.out.println("Cuenta eliminada con éxito.");
                return;
            }
        }
        System.out.println("Error: Cuenta no encontrada.");
    }

    public List<CuentaBancaria> getCuentasCliente(Cliente cliente){
        if(cliente.getCuentas() != null){
            return cliente.getCuentas();
        }
        else {
            System.out.println("El cliente no tiene cuentas asociadas para mostrar");
            return null;
        }
    }


    public boolean hayCuentas(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        return !clienteSeleccionado.getCuentas().isEmpty();
    }

    public int getCantidadCuentas(int seleccionCliente) {
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        List<CuentaBancaria> cuentas = clienteSeleccionado.getCuentas();
        if (cuentas != null){
            return cuentas.size();
        }
        else {
            return 0;
        }
    }

    public String nombreYApellido(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);

        String nombreYApellido = clienteSeleccionado.getNombre() + " " + clienteSeleccionado.getApellido();
        return nombreYApellido;
    }

    /*

    public void mostrarDetallesCuenta(int seleccionCliente, int tipo){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        CuentaAhorro cuentaAhorro = clienteSeleccionado.getCuentaAhorro();
        CuentaCorriente cuentaCorriente = clienteSeleccionado.getCuentaCorriente();

        if (tipo == 1){
            mostrarDetallesCuentaAhorro(cuentaAhorro);
        } else if (tipo == 2) {
            mostrarDetallesCuentaCorriente(cuentaCorriente);
        }
        else{
            System.out.println("Opción inválida");
            return;
        }
    }

    public void mostrarDetallesCuentaAhorro(CuentaAhorro cuenta){

        // Mostrar detalles de la cuenta
        System.out.println("\nDetalles de la Cuenta de Ahorro:");
        System.out.println();
        System.out.println("Saldo: $" + cuenta.getSaldo());
        System.out.println("Tasa de interés: " + cuenta.getTasaInteres() + "%");
        System.out.println("Ahorro mínimo: $" + cuenta.getTopeMinimo());
    }

    public void mostrarDetallesCuentaCorriente(CuentaCorriente cuenta){

        // Mostrar detalles de la cuenta
        System.out.println("\nDetalles de la Cuenta Corriente:");
        System.out.println("Saldo: $" + cuenta.getSaldo());
        System.out.println("Sobregiro: $" + cuenta.getSobregiro());
    }

    public void verCuentas(int seleccionCliente){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        List<CuentaBancaria> cuentas = clienteSeleccionado.getCuentas();
        System.out.println();
        for (int i = 0; i < cuentas.size(); i++) {
            String tipo;
            if(cuentas.get(i).getTipo().equals("a")){
                tipo = "Cuenta de Ahorro";
                System.out.println(String.valueOf(i + 1) + ". " + tipo);
                System.out.println();
            } else if (cuentas.get(i).getTipo().equals("c")) {
                tipo = "Cuenta Corriente";
                System.out.println(String.valueOf(i + 1) + ". " + tipo);
                System.out.println();
            }
        }
    }

    public void procesarTransaccion(int seleccionCliente, int tipoC, int tipoT, int monto){
        Cliente clienteSeleccionado = clientes.get(seleccionCliente - 1);
        int idCliente = clienteSeleccionado.getId();
        if(tipoC == 1){
            CuentaAhorro cuenta = clienteSeleccionado.getCuentaAhorro();
            if(tipoT == 1){
                cuenta.depositar(monto);
                System.out.println("El deposito se realizó con éxito");
            } else if (tipoT == 2) {
                cuenta.retirar(monto);
            }
        } else if (tipoC == 2) {
            CuentaCorriente cuenta = clienteSeleccionado.getCuentaCorriente();
            if(tipoT == 1){
                cuenta.depositar(monto);
                System.out.println("El deposito se realizó con éxito");
            } else if (tipoT == 2) {
                cuenta.retirar(monto);
            }
        }
    }

    */
}
