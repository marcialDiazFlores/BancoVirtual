package Modelo;

// Hereda de la clase CuentaBancaria
public class CuentaDeAhorro extends CuentaBancaria {
    private double tasaInteres;
    private int topeMinimo;

    public CuentaDeAhorro() {

    }

    public CuentaDeAhorro(int idCliente, int saldo, double tasaInteres, int topeMinimo) {
        super(idCliente, saldo);
        this.tasaInteres = tasaInteres;
        this.topeMinimo = topeMinimo;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public int getTopeMinimo() {
        return topeMinimo;
    }

    public void setTopeMinimo(int topeMinimo) {
        this.topeMinimo = topeMinimo;
    }

    @Override
    public String toString() {

        return "ID de cuenta: " + this.getId() + " | " +
                "ID Cliente: " + this.getIdCliente() + " | " +
                "Saldo: $" + this.getSaldo() + " | " +
                "Tope mínimo: $" + this.getTopeMinimo() + " | " +
                "Tasa de interés: " + this.getTasaInteres() + "%";
    }
}
