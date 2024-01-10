// CuentaCorriente.java
package Modelo;

// Hereda de la clase CuentaBancaria

public class CuentaCorriente extends CuentaBancaria {
    private int sobregiro;

    // Constructor

    public CuentaCorriente() {

    }

    public CuentaCorriente(int idCliente, int saldo, int sobregiro) {
        super(idCliente, saldo);
        this.sobregiro = sobregiro;
    }

    // Getters

    public int getSobregiro() {
        return sobregiro;
    }

    // Setters

    public void setSobregiro(int sobregiro) {
        this.sobregiro = sobregiro;
    }

    @Override
    public String toString() {

        return "ID: " + this.getId() + " | " +
                "ID Cliente: " + this.getIdCliente() + " | " +
                "Saldo: $" + this.getSaldo() + " | " +
                "Cupo de sobregiro: $" + this.getSobregiro();
    }
}
