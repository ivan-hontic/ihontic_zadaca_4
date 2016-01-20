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
public class ParkiraniAutiPoZonama {
    
    public int idAuta;
    public int idZone;
    public int brojProduzenja;
    public int vrijemeParkiranja;
    public int vrijemeDoKadJeParkiran;

    public ParkiraniAutiPoZonama(int idAuta, int idZone, int brojProduzenja, int vrijemeParkiranja, int vrijemeDoKadJeParkiran) {
        this.idAuta = idAuta;
        this.idZone = idZone;
        this.brojProduzenja = brojProduzenja;
        this.vrijemeParkiranja = vrijemeParkiranja;
        this.vrijemeDoKadJeParkiran = vrijemeDoKadJeParkiran;
    }

    public int getIdAuta() {
        return idAuta;
    }

    public void setIdAuta(int idAuta) {
        this.idAuta = idAuta;
    }

    public int getIdZone() {
        return idZone;
    }

    public void setIdZone(int idZone) {
        this.idZone = idZone;
    }

    public int getBrojProduzenja() {
        return brojProduzenja;
    }

    public void setBrojProduzenja(int brojProduzenja) {
        this.brojProduzenja = brojProduzenja;
    }

    public int getVrijemeParkiranja() {
        return vrijemeParkiranja;
    }

    public void setVrijemeParkiranja(int vrijemeParkiranja) {
        this.vrijemeParkiranja = vrijemeParkiranja;
    }

    public int getVrijemeDoKadJeParkiran() {
        return vrijemeDoKadJeParkiran;
    }

    public void setVrijemeDoKadJeParkiran(int vrijemeDoKadJeParkiran) {
        this.vrijemeDoKadJeParkiran = vrijemeDoKadJeParkiran;
    }
    
    
    
    
}
