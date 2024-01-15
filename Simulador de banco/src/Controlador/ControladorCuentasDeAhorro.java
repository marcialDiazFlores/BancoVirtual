package Controlador;

import Modelo.*;
import DAO.*;
import java.sql.SQLException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ControladorCuentasDeAhorro {
    private List<CuentaDeAhorro> cuentasDeAhorro;
    private CuentaAhorroDAO cuentaDeAhorroDAO;

    public ControladorCuentasDeAhorro() {
        this.cuentaDeAhorroDAO = new CuentaAhorroDAO();
        this.cuentasDeAhorro = cuentaDeAhorroDAO.obtenerCuentasDeAhorro();
    }

    public boolean crearCuentaDeAhorro(int idCliente, int saldo, double tasaInteres, int topeMinimo) {
        ControladorClientes controladorClientes = new ControladorClientes();
        try {
            CuentaDeAhorro cuenta = new CuentaDeAhorro(idCliente, saldo, tasaInteres, topeMinimo);
            cuentasDeAhorro.add(cuenta);
            controladorClientes.agregarCuentaDeAhorro(cuenta);
            return cuentaDeAhorroDAO.agregarCuentaDeAhorro(cuenta);
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo agregar la cuenta de ahorro. Error: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarCuentaDeAhorro(String rut, int saldo, double tasaInteres, int topeMinimo) {
        try {
            ControladorClientes controladorClientes = new ControladorClientes();
            int idCliente = controladorClientes.encontrarClientePorRUT(rut).getId();
            return cuentaDeAhorroDAO.actualizarCuentaDeAhorro(idCliente, saldo, tasaInteres, topeMinimo);
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo actualizar la cuenta de ahorro en la base de datos. Error: " + e.getMessage());
            return false;
        }
    }

    public void mostrarDetallesCuentaDeAhorro(int i){
        CuentaDeAhorro cuenta = cuentasDeAhorro.get(i);
        if (cuenta != null) {
            ControladorClientes controladorClientes = new ControladorClientes();
            Cliente cliente = controladorClientes.encontrarClientePorId(cuenta.getIdCliente());

            if (cliente != null) {

                // Mostrar detalles de la cuenta de ahorro

                String detalles = "- ID de la Cuenta de Ahorro: " + cuenta.getId() + " | ";
                detalles = detalles + "Nombre del cliente: " + cliente.getNombre() + " | ";
                detalles = detalles + "ID del cliente: " + cliente.getId() + " | ";
                detalles = detalles + "Saldo: $" + cuenta.getSaldo() + " | ";
                detalles = detalles + "Monto mínimo para hacer retiros: $" + cuenta.getTopeMinimo() + " | ";
                detalles = detalles + "Tasa de interés: " + cuenta.getTasaInteres() + "%";
                System.out.println(detalles);
            }
        }
        else {
            System.out.println("Cuenta no encontrada");
        }
    }

    private void ordenarCuentasDeAhorroPorId() {
        Collections.sort(cuentasDeAhorro, Comparator.comparingInt(CuentaDeAhorro::getId));
    }

    public void mostrarDetallesCuentasDeAhorro(){
        if (!hayCuentasDeAhorro()){
            System.out.println("No hay cuentas de ahorro registradas en este momento");
        }
        else {
            ordenarCuentasDeAhorroPorId();
            int cantCuentasDeAhorro = this.getCantidadCuentasDeAhorro();
            for (int i = 0; i < cantCuentasDeAhorro; i++) {
                mostrarDetallesCuentaDeAhorro(i);
            }
            System.out.println();
        }
    }

    public Object[][] obtenerDatosCuentasDeAhorro() {
        List<CuentaDeAhorro> listaCuentasDeAhorro = cuentaDeAhorroDAO.obtenerCuentasDeAhorro();
        ControladorClientes controladorClientes = new ControladorClientes();
        Object[][] data = new Object[listaCuentasDeAhorro.size()][7];

        for (int i = 0; i < listaCuentasDeAhorro.size(); i++) {
            CuentaDeAhorro cuenta = listaCuentasDeAhorro.get(i);
            int idCuenta = cuenta.getId();
            int idCliente = cuenta.getIdCliente();
            data[i][0] = idCuenta;
            data[i][1] = controladorClientes.encontrarNombreClientePorID(idCliente);
            data[i][2] = controladorClientes.encontrarApellidoClientePorID(idCliente);
            data[i][3] = controladorClientes.encontrarRUTClientePorID(idCliente);
            data[i][4] = cuenta.getSaldo();
            data[i][5] = truncar(cuenta.getTasaInteres());
            data[i][6] = cuenta.getTopeMinimo();
        }
        return data;
    }

    public void eliminarCuentaDeAhorro(String rut) {
        ControladorClientes controladorClientes = new ControladorClientes();
        try {
            Cliente cliente = controladorClientes.encontrarClientePorRUT(rut);
            if (cliente != null) {
                int idCliente = cliente.getId();
                CuentaDeAhorro cuenta = encontrarCuentaPorIdCliente(idCliente);
                if (cuenta != null) {
                    cuentasDeAhorro.remove(cuenta);
                    cuentaDeAhorroDAO.eliminarCuentaDeAhorro(cuenta.getIdCliente());
                    controladorClientes.eliminarCuentaDeAhorro(cuenta);
                }
            }
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo eliminar la cuenta de ahorro de la base de datos. Error: " + e.getMessage());
        }
    }

    public boolean eliminarCuentaDeAhorro(int idCliente) {
        try {
            if (cuentaDeAhorroDAO.eliminarCuentaDeAhorro(idCliente)){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            // Manejo de la excepción con mensaje de error
            System.err.println("No se pudo eliminar la cuenta de ahorro de la base de datos. Error: " + e.getMessage());
            return false;
        }
    }

    public double truncar(double n){
        return Math.round(n * 10.0) / 10.0;
    }

    public void buscarCuentaDeAhorro(String rut){
        ControladorClientes controladorClientes = new ControladorClientes();
        Cliente cliente = controladorClientes.encontrarClientePorRUT(rut);
        if (cliente != null){
            System.out.println();
            System.out.println("Cliente encontrado en la base de datos");
            System.out.println("Nombre: " + cliente.getNombre() + ", Apellido: " + cliente.getApellido());

            CuentaDeAhorro cuenta = encontrarCuentaPorIdCliente(cliente.getId());

            if (cuenta != null) {
                System.out.println();
                System.out.println("Detalles de la cuenta de ahorro:");
                System.out.println();
                System.out.println(cuenta.toString());
            }
            else {
                System.out.println("El cliente no tiene cuenta de ahorro");
            }
        }
        else {
            System.out.println("Cliente no encontrado en la base de datos");
        }
    }

    public CuentaDeAhorro encontrarCuentaPorIdCliente(int id) {
        if (id > 0) {
            CuentaDeAhorro cuenta = new CuentaDeAhorro();
            if (cuentasDeAhorro != null){
                for (CuentaDeAhorro cuentaDeAhorro : cuentasDeAhorro) {
                    if (cuentaDeAhorro.getIdCliente() == id) {
                        cuenta = cuentaDeAhorro;
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

    public boolean tieneCuentaDeAhorro(int idCliente) {
        return cuentaDeAhorroDAO.tieneCuentaDeAhorro(idCliente);
    }

    public CuentaDeAhorro encontrarCuentaPorRUT(String rut, List<Cliente> listaClientes) {
        CuentaDeAhorro cuenta = new CuentaDeAhorro();
        if (listaClientes != null){
            for (Cliente cliente : listaClientes) {
                if (cliente.getRut() == rut) {
                    cuenta = cliente.getCuentaDeAhorro();
                }
            }
            if (cuenta != null) {
                return cuenta;
            }
            else {
                System.out.println("No se encontró la cuenta de ahorro");
                return null;
            }
        }
        else {
            System.out.println("No hay cuentas registradas en el banco");
            return null;
        }
    }
    public int getCantidadCuentasDeAhorro() {
        if (cuentasDeAhorro != null){
            return cuentasDeAhorro.size();
        }
        else {
            return 0;
        }
    }

    public boolean hayCuentasDeAhorro() {
        if (cuentasDeAhorro.size() >= 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<CuentaDeAhorro> getCuentasDeAhorro() {
        return cuentasDeAhorro;
    }
}