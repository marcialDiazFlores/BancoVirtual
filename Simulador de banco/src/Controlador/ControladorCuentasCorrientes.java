package Controlador;

import Modelo.*;
import DAO.*;
import java.sql.SQLException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ControladorCuentasCorrientes {
    private List<CuentaCorriente> cuentasCorrientes;
    private CuentaCorrienteDAO cuentaCorrienteDAO;

    public ControladorCuentasCorrientes() {
        this.cuentaCorrienteDAO = new CuentaCorrienteDAO();
        this.cuentasCorrientes = cuentaCorrienteDAO.obtenerCuentasCorrientes();
    }

    public boolean crearCuentaCorriente(int idCliente, int saldo, int sobregiro) {
        ControladorClientes controladorClientes = new ControladorClientes();
        try {
            CuentaCorriente cuenta = new CuentaCorriente(idCliente, saldo, sobregiro);
            cuentasCorrientes.add(cuenta);
            controladorClientes.agregarCuentaCorriente(cuenta);
            if(cuentaCorrienteDAO.agregarCuentaCorriente(cuenta)) {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo agregar la cuenta corriente. Error: " + e.getMessage());
            return false;
        }
    }

    public void mostrarDetallesCuentaCorriente(int i){
        CuentaCorriente cuenta = cuentasCorrientes.get(i);
        if (cuenta != null) {
            ControladorClientes controladorClientes = new ControladorClientes();
            Cliente cliente = controladorClientes.encontrarClientePorId(cuenta.getIdCliente());

            if (cliente != null) {

                // Mostrar detalles de la cuenta corriente

                String detalles = "- ID de la Cuenta Corriente: " + cuenta.getId() + " | ";
                detalles = detalles + "Nombre del cliente: " + cliente.getNombre() + " | ";
                detalles = detalles + "ID del cliente: " + cliente.getId() + " | ";
                detalles = detalles + "Saldo: $" + cuenta.getSaldo() + " | ";
                detalles = detalles + "Cupo de sobregiro: $" + cuenta.getSobregiro();
                System.out.println(detalles);
            }
        }
        else {
            System.out.println("Cuenta no encontrada");
        }
    }

    private void ordenarCuentasCorrientesPorId() {
        Collections.sort(cuentasCorrientes, Comparator.comparingInt(CuentaCorriente::getId));
    }

    public void mostrarDetallesCuentasCorrientes(){
        if (!hayCuentasCorrientes()){
            System.out.println("No hay cuentas corrientes registradas en este momento");
            System.out.println();
        }
        else {
            ordenarCuentasCorrientesPorId();
            int cantCuentasCorrientes = this.getCantidadCuentasCorrientes();
            for (int i = 0; i < cantCuentasCorrientes; i++) {
                mostrarDetallesCuentaCorriente(i);
            }
            System.out.println();
        }
    }

    public Object[][] obtenerDatosCuentasCorrientes() {
        List<CuentaCorriente> listaCuentasCorrientes = cuentaCorrienteDAO.obtenerCuentasCorrientes();
        ControladorClientes controladorClientes = new ControladorClientes();
        Object[][] data = new Object[listaCuentasCorrientes.size()][6];

        for (int i = 0; i < listaCuentasCorrientes.size(); i++) {
            CuentaCorriente cuenta = listaCuentasCorrientes.get(i);
            int idCuenta = cuenta.getId();
            int idCliente = cuenta.getIdCliente();
            data[i][0] = idCuenta;
            data[i][1] = controladorClientes.encontrarNombreClientePorID(idCliente);
            data[i][2] = controladorClientes.encontrarApellidoClientePorID(idCliente);
            data[i][3] = controladorClientes.encontrarRUTClientePorID(idCliente);
            data[i][4] = cuenta.getSaldo();
            data[i][5] = cuenta.getSobregiro();
        }
        return data;
    }

    public boolean actualizarCuentaCorriente(String rut, int saldo, int sobregiro) {
        try {
            ControladorClientes controladorClientes = new ControladorClientes();
            int idCliente = controladorClientes.encontrarClientePorRUT(rut).getId();
            return cuentaCorrienteDAO.actualizarCuentaCorriente(idCliente, saldo, sobregiro);
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo actualizar la cuenta corriente en la base de datos. Error: " + e.getMessage());
            return false;
        }
    }

    public void eliminarCuentaCorriente(String rut) {
        ControladorClientes controladorClientes = new ControladorClientes();
        try {
            Cliente cliente = controladorClientes.encontrarClientePorRUT(rut);
            if (cliente != null) {
                int idCliente = cliente.getId();
                CuentaCorriente cuenta = encontrarCuentaPorIdCliente(idCliente);
                if (cuenta != null) {
                    cuentasCorrientes.remove(cuenta);
                    cuentaCorrienteDAO.eliminarCuentaCorriente(cuenta.getIdCliente());
                    controladorClientes.eliminarCuentaCorriente(cuenta);
                }
            }
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo eliminar la cuenta corriente de la base de datos. Error: " + e.getMessage());
        }
    }

    public boolean eliminarCuentaCorriente(int id) {
        try {
            if (cuentaCorrienteDAO.eliminarCuentaCorriente(id)){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo eliminar la cuenta corriente de la base de datos. Error: " + e.getMessage());
            return false;
        }
    }

    public void buscarCuentaCorriente(String rut){
        ControladorClientes controladorClientes = new ControladorClientes();
        Cliente cliente = controladorClientes.encontrarClientePorRUT(rut);
        if (cliente != null){
            System.out.println();
            System.out.println("Cliente encontrado en la base de datos");
            System.out.println("Nombre: " + cliente.getNombre() + ", Apellido: " + cliente.getApellido());

            CuentaCorriente cuenta = encontrarCuentaPorIdCliente(cliente.getId());

            if (cuenta != null) {
                System.out.println();
                System.out.println("Detalles de la cuenta corriente:");
                System.out.println();
                System.out.println(cuenta);
            }
            else {
                System.out.println("El cliente no tiene cuenta corriente");
            }
        }
        else {
            System.out.println("Cliente no encontrado en la base de datos");
        }
    }

    public CuentaCorriente encontrarCuentaPorIdCliente(int id) {
        if (id > 0) {
            CuentaCorriente cuenta;
            if (cuentasCorrientes != null){
                for (CuentaCorriente cuentaCorriente : cuentasCorrientes) {
                    if (cuentaCorriente.getIdCliente() == id) {
                        cuenta = cuentaCorriente;
                        return cuenta;
                    }
                }
                return null;
            }
            else {
                System.out.println("No hay cuentas registradas en el banco");
                return null;
            }
        }
        else {
            return null;
        }
    }

    public boolean tieneCuentaCorriente(int idCliente) {
        return cuentaCorrienteDAO.tieneCuentaCorriente(idCliente);
    }
    public int getCantidadCuentasCorrientes() {
        if (cuentasCorrientes != null){
            return cuentasCorrientes.size();
        }
        else {
            return 0;
        }
    }

    public boolean hayCuentasCorrientes() {
        if (cuentasCorrientes.size() >= 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<CuentaCorriente> getCuentasCorrientes() {
        return cuentasCorrientes;
    }
}