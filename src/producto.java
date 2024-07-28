public class producto {
    private String tipo_arreglo;
    private double precio;
    private double pago;

    public producto(){

    }

    public producto(String tipo_arreglo,double precio,double pago){
        this.tipo_arreglo = tipo_arreglo;
        this.pago = pago;
        this.precio = precio;
    }

    public String getTipo_arreglo() {
        return tipo_arreglo;
    }

    public void setTipo_arreglo(String tipo_arreglo) {
        this.tipo_arreglo = tipo_arreglo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }
}
