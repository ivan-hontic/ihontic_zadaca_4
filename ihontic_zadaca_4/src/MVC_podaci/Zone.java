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
public class Zone {
    public int idZone;
    public int kapacitet;
    public int brojZauzetih;
    public int brParkiranih;
    public int vrijemeParkiranja;
    public int cijenaParkiranja;
    public int zaradaParking;
    public int zaradaKazne;
    public int brojAutaBezMjesta;

    public Zone(int idZone, int kapacitet, int brojZauzetih, int brParkiranih, int vrijemeParkiranja, int cijenaParkiranja, int zaradaParking, int zaradaKazne, int brojAutaBezMjesta) {
        this.idZone = idZone;
        this.kapacitet = kapacitet;
        this.brojZauzetih = brojZauzetih;
        this.brParkiranih = brParkiranih;
        this.vrijemeParkiranja = vrijemeParkiranja;
        this.cijenaParkiranja = cijenaParkiranja;
        this.zaradaParking = zaradaParking;
        this.zaradaKazne = zaradaKazne;
        this.brojAutaBezMjesta = brojAutaBezMjesta;
    }

    public int getBrojZauzetih() {
        return brojZauzetih;
    }

    public void setBrojZauzetih(int brojZauzetih) {
        this.brojZauzetih = brojZauzetih;
    }

    

    public int getIdZone() {
        return idZone;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public int getVrijemeParkiranja() {
        return vrijemeParkiranja;
    }

    public int getCijenaParkiranja() {
        return cijenaParkiranja;
    }

    public int getBrParkiranih() {
        return brParkiranih;
    }

    public void setBrPrakiranih(int brParkiranih) {
        this.brParkiranih = brParkiranih;
    }

    public int getZaradaParking() {
        return zaradaParking;
    }

    public void setZaradaParking(int zaradaParking) {
        this.zaradaParking = zaradaParking;
    }

    public int getZaradaKazne() {
        return zaradaKazne;
    }

    public void setZaradaKazne(int zaradaKazne) {
        this.zaradaKazne = zaradaKazne;
    }

    public int getBrojAutaBezMjesta() {
        return brojAutaBezMjesta;
    }

    public void setBrojAutaBezMjesta(int brojAutaBezMjesta) {
        this.brojAutaBezMjesta = brojAutaBezMjesta;
    }
    
    
    
    
    
}
