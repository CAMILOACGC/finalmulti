
package modelos;

/**
 * La clase abstracta `Producto` representa un producto con un ID, nombre, precio y unidades.
 * Esta clase no puede ser instanciada directamente, sino que debe ser extendida por
 * clases concretas como `PlatoP`.
 */
public abstract class Producto {
    protected String ID;
    protected String NOM;
    protected double PREC;
    protected int UNI;

    /**
     * Constructor de la clase `Producto`.
     * @param ID   El ID del producto.
     * @param NOM  El nombre del producto.
     * @param PREC El precio del producto.
     * @param UNI  Las unidades del producto.
     */
    public Producto(String ID, String NOM, double PREC, int UNI) {
        this.ID = ID;
        this.NOM = NOM;
        this.PREC = PREC;
        this.UNI = UNI;
    }

    /**
     * Constructor vacío de la clase `Producto`.
     * Inicializa los atributos con valores por defecto.
     */
    public Producto() {
        this.ID = "";
        this.NOM = "";
        this.PREC = 0.0;
        this.UNI = 0;
    }

    /**
     * Obtiene el ID del producto.
     * @return El ID del producto.
     */
    public String getID() {
        return ID;
    }

    /**
     * Establece el ID del producto.
     * @param ID El ID del producto.
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Obtiene el nombre del producto.
     * @return El nombre del producto.
     */
    public String getNOM() {
        return NOM;
    }

    /**
     * Establece el nombre del producto.
     * @param NOM El nombre del producto.
     */
    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    /**
     * Obtiene el precio del producto.
     * @return El precio del producto.
     */
    public double getPREC() {
        return PREC;
    }

    /**
     * Establece el precio del producto.
     * @param PREC El precio del producto.
     */
    public void setPREC(double PREC) {
        this.PREC = PREC;
    }

    /**
     * Obtiene las unidades del producto.
     * @return Las unidades del producto.
     */
    public int getUNI() {
        return UNI;
    }

    /**
     * Establece las unidades del producto.
     * @param UNI Las unidades del producto.
     */
    public void setUNI(int UNI) {
        this.UNI = UNI;
    }

    /**
     * Calcula el valor total del producto (precio * unidades).
     * @return El valor total del producto.
     */
    public double CalcularValor() {
        return UNI * PREC;
    }

    /**
     * Devuelve una representación en cadena del objeto `Producto`.
     * @return Una cadena que representa al objeto `Producto`.
     */
    @Override
    public String toString() {
        return "Producto{" +
                "ID='" + ID + '\'' +
                ", NOM='" + NOM + '\'' +
                ", PREC=" + PREC +
                ", UNI=" + UNI +
                ", valor=" + CalcularValor() +
                '}';
    }
}
