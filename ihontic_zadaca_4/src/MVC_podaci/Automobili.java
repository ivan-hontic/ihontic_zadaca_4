/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC_podaci;

/**
 *
 * @author Ivan Hontic
 */
public class Automobili {

    private int idAutomobila;
    public int brojParkiranja;
    boolean parkiran;

    public Automobili(int idAutomobila, int brojParkiranja, boolean parkiran) {
        this.idAutomobila = idAutomobila;
        this.brojParkiranja = brojParkiranja;
        this.parkiran = parkiran;
    }

    public void setIdAutomobila(int idAutomobila) {
        this.idAutomobila = idAutomobila;
    }

    public int getIdAutomobila() {
        return idAutomobila;
    }

    public int getBrojParkiranja() {
        return brojParkiranja;
    }

    public void setBrojParkiranja(int brojParkiranja) {
        this.brojParkiranja = brojParkiranja;
    }

    public boolean isParkiran() {
        return parkiran;
    }

    public void setParkiran(boolean parkiran) {
        this.parkiran = parkiran;
    }

}
