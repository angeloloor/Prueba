public class cliente {
    private int idCliente;
    private String cedula;
    private String nombre;

    // Constructor
    public cliente(int idCliente, String cedula, String nombre) {
        this.idCliente = idCliente;
        this.cedula = cedula;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Cliente [idCliente=" + idCliente + ", cedula=" + cedula + ", nombre=" + nombre + "]";
    }
}