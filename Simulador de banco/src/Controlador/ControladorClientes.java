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

    public Object[][] obtenerDatosClientes() {
        CuentaAhorroDAO ahorroDAO = new CuentaAhorroDAO();
        CuentaCorrienteDAO corrienteDAO = new CuentaCorrienteDAO();
        List<Cliente> listaClientes = clienteDAO.obtenerTodosLosClientes();
        Object[][] data = new Object[listaClientes.size()][9];

        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            data[i][0] = cliente.getId();
            data[i][1] = cliente.getNombre();
            data[i][2] = cliente.getApellido();
            data[i][3] = cliente.getEdad();
            data[i][4] = cliente.getEmail();
            data[i][5] = cliente.getRut();
            data[i][6] = cliente.getFono();
            if(ahorroDAO.tieneCuentaDeAhorro(cliente.getId())){
                data[i][7] = "Sí";
            }
            else {
                data[i][7] = "No";
            }
            if(corrienteDAO.tieneCuentaCorriente(cliente.getId())){
                data[i][8] = "Sí";
            }
            else {
                data[i][8] = "No";
            }
        }

        return data;
    }

    public boolean crearCliente(String nombre, String apellido, int edad, String email, String rut, String fono) {
        try {
            Cliente cliente = new Cliente(nombre, apellido, edad, email, rut, fono);
            //clientes.add(cliente);
            return clienteDAO.agregarCliente(cliente);
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo agregar el cliente. Error: " + e.getMessage());
            return false;
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

    /*public boolean actualizarCliente(String rut) {
        try {
            Cliente cliente = new Cliente(nombre, apellido, );
            *//*
            cliente.setEmail(email);
            cliente.setFono(fono);
            *//*
            clienteDAO.actualizarCliente(cliente, email, fono);
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudieron actualizar los datos del cliente. Error: " + e.getMessage());
        }
    }*/

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

    public boolean eliminarCliente(int id) {
        try {
            ControladorCuentasDeAhorro controladorCuentasDeAhorro = new ControladorCuentasDeAhorro();
            ControladorCuentasCorrientes controladorCuentasCorrientes = new ControladorCuentasCorrientes();
            if (controladorCuentasDeAhorro.tieneCuentaDeAhorro(id)){
                controladorCuentasDeAhorro.eliminarCuentaDeAhorro(id);
            }
            if (controladorCuentasCorrientes.tieneCuentaCorriente(id)){
                controladorCuentasCorrientes.eliminarCuentaCorriente(id);
            }
            return clienteDAO.eliminarCliente(id);
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo eliminar al cliente de la base de datos. Error: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarCliente(String nombre, String apellido, int edad, String email, String fono, String rut) {
        try {
            return clienteDAO.actualizarCliente(nombre, apellido, edad, email, fono, rut);
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo eliminar al cliente de la base de datos. Error: " + e.getMessage());
            return false;
        }
    }

    public Object[] buscarCliente(String rut) {
        Cliente cliente = clienteDAO.buscarCliente(rut);
        CuentaAhorroDAO ahorroDAO = new CuentaAhorroDAO();
        CuentaCorrienteDAO corrienteDAO = new CuentaCorrienteDAO();

        String ahorro;
        String corriente;

        if (cliente != null) {
            if (ahorroDAO.tieneCuentaDeAhorro(cliente.getId())) {
                ahorro = "Sí";
            }
            else {
                ahorro = "No";
            }

            if (corrienteDAO.tieneCuentaCorriente(cliente.getId())) {
                corriente = "Sí";
            }
            else {
                corriente = "No";
            }

            Object[] datos = {cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getEdad(), cliente.getEmail(), cliente.getRut(), cliente.getFono(), ahorro, corriente};
            return datos;
        }
        else {
            Object[] datos = {};
            return datos;
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

    public int encontrarIdClientePorRUT(String rut) {
        actualizarCuentas();
        for (Cliente cliente : clientes) {
            if (cliente.getRut().equals(rut)) {
                return cliente.getId();
            }
        }
        return -1;
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

    public boolean hayCuentaAhorro(String rut){
        Cliente clienteSeleccionado = encontrarClientePorRUT(rut);
        ControladorCuentasDeAhorro controladorCuentasDeAhorro = new ControladorCuentasDeAhorro();

        if (controladorCuentasDeAhorro.tieneCuentaDeAhorro(clienteSeleccionado.getId())){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hayCuentaCorriente(String rut){
        Cliente clienteSeleccionado = encontrarClientePorRUT(rut);
        ControladorCuentasCorrientes controladorCuentasCorrientes = new ControladorCuentasCorrientes();

        if (controladorCuentasCorrientes.tieneCuentaCorriente(clienteSeleccionado.getId())){
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
}
