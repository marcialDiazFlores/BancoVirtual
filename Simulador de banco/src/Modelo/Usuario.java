package Modelo;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String rut;
    private String contrasena;
    private String fono;

    // Constructor

    public Usuario () {

    }

    public Usuario(String nombre, String apellido, String email, String rut, String fono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.rut = rut;
        this.fono = fono;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getRut() {
        return rut;
    }
    public String getContrasena() { return contrasena; }

    public String getFono() {
        return fono;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; };

    public void setFono(String fono) {
        this.fono = fono;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + " | " +
                "Nombre: " + this.getNombre() + " | " +
                "Apellido: " + this.getApellido() + " | " +
                "Rut: " + this.getRut() + " | " +
                "Email: " + this.getEmail() + " | " +
                "Teléfono: " + this.getFono();
    }
}
