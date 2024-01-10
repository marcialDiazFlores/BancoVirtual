// Cliente.java
package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private CuentaDeAhorro cuentaDeAhorro;
    private CuentaCorriente cuentaCorriente;

    // Constructor

    public Cliente() {
        super();
    }

    public Cliente(String nombre, String apellido, String email, String rut, String fono) {
        super(nombre, apellido, email, rut, fono);
    }

    // Getters
    public CuentaDeAhorro getCuentaDeAhorro() { return cuentaDeAhorro; }
    public CuentaCorriente getCuentaCorriente() { return cuentaCorriente; }

    // Setters

    public void setCuentaDeAhorro(CuentaDeAhorro cuentaDeAhorro) {
        this.cuentaDeAhorro = cuentaDeAhorro;
    }
    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    @Override
    public String toString() {
        String cuentaDeAhorro = (this.cuentaDeAhorro != null) ? "Sí" : "No";
        String cuentaCorriente = (this.cuentaCorriente != null) ? "Sí" : "No";

        return "ID: " + this.getId() + " | " +
                "Nombre: " + this.getNombre() + " | " +
                "Apellido: " + this.getApellido() + " | " +
                "Rut: " + this.getRut() + " | " +
                "Email: " + this.getEmail() + " | " +
                "Teléfono: " + this.getFono() + " | " +
                "¿Tiene cuenta de ahorro? " + cuentaDeAhorro + " | " +
                "¿Tiene cuenta corriente? " + cuentaCorriente;
    }
}
