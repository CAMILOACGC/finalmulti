/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Camilo
 */
public class Empleado extends Persona{
    private String COD;
    private String DIC;
    private double SAL;
    private int HOR;

    public Empleado(String COD, String DIC, double SAL, int HOR, String ID, String NOM, String TEL) {
        super(ID, NOM, TEL);
        this.COD = COD;
        this.DIC = DIC;
        this.SAL = SAL;
        this.HOR = HOR;
    }

    public Empleado() {
        this.COD = "";
        this.DIC = "";
        this.SAL = 0.0;
        this.HOR = 0;
    }


    

    public String getCOD() {
        return COD;
    }

    public void setCOD(String COD) {
        this.COD = COD;
    }

    public String getDIC() {
        return DIC;
    }

    public void setDIC(String DIC) {
        this.DIC = DIC;
    }

   

    public double getSAL() {
        return SAL;
    }

    public void setSAL(double  SAL) {
        this.SAL = SAL;
    }

    public int getHOR() {
        return HOR;
    }

    public void setHOR(int HOR) {
        this.HOR = HOR;
    }
    
 public double calcularSueldoHo() {
    double sueldoBase;
    double horasExtras = 0;
    double sueldoTotal;

    // Calcular sueldo base y horas extras
    if (HOR > 44) {
        horasExtras = (HOR - 44) * (SAL * 1.25); // 25% adicional por hora extra
        sueldoBase = 44 * SAL; // Las primeras 44 horas se pagan normal
    } else {
        sueldoBase = HOR * SAL; // Todas las horas se pagan normal si no exceden 44
    }

    sueldoTotal = sueldoBase + horasExtras;

    // Aplicar subsidio de transporte si el salario mensual no supera $2,600,000
    if (sueldoTotal * 4 <= 2600000) { // Se multiplica por 4 para obtener el pago mensual
        sueldoTotal += 150000; // Agregar subsidio de transporte
    }

    // Deducción del 4% de salud y 4% de pensión
    double deduccionTotal = sueldoTotal * 0.08; // 8% de deducciones totales
    sueldoTotal -= deduccionTotal; // Restar deducciones

    return sueldoTotal;
}

    @Override
    public String toString() {
        return "Empleado" 
                + "COD=" + COD 
                + ", DIC=" + DIC 
                + ", HOR=" + HOR 
                + ", SAL=" + SAL 
                +",sueldo="+calcularSueldoHo();
    }
 
}
