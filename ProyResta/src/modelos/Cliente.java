/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Camilo
 */
public  class Cliente extends Persona{
    private String EMAIL;

    public Cliente(String EMAIL, String ID, String NOM, String TEL) {
        super(ID, NOM, TEL);
        this.EMAIL = EMAIL;
    }

    public Cliente() {
        this.EMAIL = "";
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    @Override
    public String toString() {
        return "Cliente" + "EMAIL=" + EMAIL ;
    }
    
    
    
}
