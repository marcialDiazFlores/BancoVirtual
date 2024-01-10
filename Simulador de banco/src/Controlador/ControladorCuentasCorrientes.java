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

    public void crearCuentaCorriente(int idCliente, int saldo, int sobregiro) {
        ControladorClientes controladorClientes = new ControladorClientes();
        Cliente cliente = controladorClientes.encontrarClientePorId(idCliente);
        try {
            CuentaCorriente cuenta = new CuentaCorriente(idCliente, saldo, sobregiro);
            cuentasCorrientes.add(cuenta);
            controladorClientes.agregarCuentaCorriente(cuenta);
            cuentaCorrienteDAO.agregarCuentaCorriente(cuenta);
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo agregar la cuenta corriente. Error: " + e.getMessage());
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

    public void eliminarCuentaCorriente(String rut) {
        ControladorClientes controladorClientes = new ControladorClientes();
        try {
            Cliente cliente = controladorClientes.encontrarClientePorRUT(rut);
            if (cliente != null) {
                int idCliente = cliente.getId();
                CuentaCorriente cuenta = encontrarCuentaPorIdCliente(idCliente);
                if (cuenta != null) {
                    cuentasCorrientes.remove(cuenta);
                    cuentaCorrienteDAO.eliminarCuentaCorriente(cuenta);
                    controladorClientes.eliminarCuentaCorriente(cuenta);
                }
            }
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo eliminar la cuenta corriente de la base de datos. Error: " + e.getMessage());
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

    public boolean tieneCuentaCorriente(Cliente cliente) {
        return cuentaCorrienteDAO.tieneCuentaCorriente(cliente.getId());
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