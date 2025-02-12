/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.Calendar;

/**
 *
 * @author Camilo
 */
public class Fecha {
     private int dd,mm,aa;

    public Fecha(int dd, int mm, int aa) {
        Calendar fecha= Calendar.getInstance();
        this.dd = fecha.get(Calendar.DAY_OF_MONTH);
       this.mm = fecha.get(Calendar.MONTH)+1;
        this.aa = fecha.get(Calendar.YEAR);
    }
    
        public Fecha() {
         this.dd = 1;
       this.mm = 0;
        this.aa = 2000;
        }
    public int getDd() {
        return dd;
    }

    public void setDd(int dd) {
        this.dd = dd;
    }

    public int getMm() {
        return mm;
    }

    public void setMm(int mm) {
        this.mm = mm;
    }

    public int getAa() {
        return aa;
    }

    public void setAa(int aa) {
        this.aa = aa;
    }
        @Override
    public String toString() {
        return dd + "/" + mm + "/" + aa;
    }
    
}
