// CuentaBancaria.java
package Modelo;

public class CuentaBancaria {
    private int id;
    private int idCliente;
    private int saldo;

    // Constructor, getters y setters

    // Constructor

    public CuentaBancaria() {

    }

    public CuentaBancaria(int idCliente, int saldo) {
        this.idCliente = idCliente;
        this.saldo = saldo;
    }

    // Getters

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getSaldo() {
        return saldo;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
