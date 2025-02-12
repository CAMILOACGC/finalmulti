
package modelos;

public abstract class Producto {
  protected String ID;
   protected String NOM;
   protected double PREC;
   protected int UNI;

    public Producto(String ID, String NOM, double PREC, int UNI) {
        this.ID = ID;
        this.NOM = NOM;
        this.PREC = PREC;
        this.UNI = UNI;
    }

   
    public Producto() {
        this.ID = "";
        this.NOM ="";
        this.PREC = 0.0;
        this.UNI=0;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    public double getPREC() {
        return PREC;
    }

    public void setPREC(double PREC) {
        this.PREC = PREC;
    }

    public int getUNI() {
        return UNI;
    }

    public void setUNI(int UNI) {
        this.UNI = UNI;
    }
    
 public double CalcularValor()
 {
     double valor;
     valor=UNI*PREC;
     return valor;
     
 }
 
    @Override
    public String toString() {
        return "Producto" 
                + "ID=" + ID 
                + ", NOM=" + NOM 
                + ", PREC=" + PREC
                +"valor = "+CalcularValor();
    }
   
    
    
    
}
