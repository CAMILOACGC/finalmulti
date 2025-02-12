/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Camilo
 */
public class PlatoP extends Producto {
    private String CAN;
    private String PRO;
    private String ACOM;
    private String GASEOSA;

    public PlatoP(String CAN, String PRO, String ACOM, String GASEOSA, String ID, String NOM, double PREC, int UNI) {
        super(ID, NOM, PREC, UNI);
        this.CAN = CAN;
        this.PRO = PRO;
        this.ACOM = ACOM;
        this.GASEOSA = GASEOSA;
    }

    public PlatoP(String CAN, String PRO, String ACOM, String GASEOSA) {
        this.CAN = CAN;
        this.PRO = PRO;
        this.ACOM = ACOM;
        this.GASEOSA = GASEOSA;
    }
    
  



    public PlatoP() {
        this.CAN = "";
        this.PRO = "";
        this.ACOM = "";
        this.GASEOSA = "";
    }

    public String getCAN() {
        return CAN;
    }

    public void setCAN(String CAN) {
        this.CAN = CAN;
    }

    public String getPRO() {
        return PRO;
    }

    public void setPRO(String PRO) {
        this.PRO = PRO;
    }

    public String getACOM() {
        return ACOM;
    }

    public void setACOM(String ACOM) {
        this.ACOM = ACOM;
    }

    public String getGASEOSA() {
        return GASEOSA;
    }

    public void setGASEOSA(String GASEOSA) {
        this.GASEOSA = GASEOSA;
    }

    @Override
    public String toString() {
        return "Plato principal" 
                + "para " + CAN +"Personas"
                + "con proteina" + PRO + 
                "Acompañate es " + ACOM 
                + "GASEOSA=" + GASEOSA ;
    }
    
    
}
