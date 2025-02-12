/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Camilo
 */
public abstract class Persona {
    protected String ID;
       protected String NOM;
          protected String TEL;

    public Persona(String ID, String NOM, String TEL) {
        this.ID = ID;
        this.NOM = NOM;
        this.TEL = TEL;
    }
    public Persona() {
        this.ID = "";
        this.NOM = "";
        this.TEL = "";
        
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

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    @Override
    public String toString() {
        return  "ID=" + ID 
                + ", NOM=" + NOM 
                + ", TEL=" + TEL ;
    }
          
    
    
}
