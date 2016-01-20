/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC_kontroler;

import Iterator.IteratorPodaci;
import Iterator.ParkiranjaIterator;
import MVC_podaci.Automobili;
import MVC_podaci.ParkiraniAutiPoZonama;
import MVC_podaci.Zone;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Ivan Hontic
 */
public class ElementKonroler {

    public static List<Automobili> auti = new ArrayList<>();
    public static List<Zone> zone = new ArrayList<>();
    public static List<ParkiraniAutiPoZonama> parkiraniAuti = new ArrayList<>();

    public void pokreni(String[] args) {
        int brojAutomobila = Integer.parseInt(args[0]);
        kreirajAutomobile(brojAutomobila);

        int brojZona = Integer.parseInt(args[1]);
        int kapacitetZone = Integer.parseInt(args[2]);
        int maksParkiranje = Integer.parseInt(args[3]);
        int vremenskaJedinica = Integer.parseInt(args[4]);
        int cijenaJedinice = Integer.parseInt(args[7]);
        kreirajZone(brojZona, kapacitetZone, maksParkiranje, vremenskaJedinica, cijenaJedinice);

        //ispisiAutomobile();
        //ispisiZone();
        //System.out.println("EK generated: "+generirajVrijednost());
        napuniParking();
        ispisiParkiranje();
        obrisiPrvog();
        ispisiParkiranje();
        //obrisiPrvog();
        //ispisiParkiranje();
        dodajPrvog();
        ispisiParkiranje();
        //obrisiPrvog();
        dodajPrvog();
        ispisiParkiranje();
    }

    public void kreirajAutomobile(int brojAuta) {
        for (int i = 0; i < brojAuta; i++) {
            Automobili pomocni = new Automobili(i + 1, 0, false);
            auti.add(pomocni);
        }
    }

    public void kreirajZone(int brojZona, int kapacitetZone, int maksParkiranje, int vremenskaJedinica, int cijenaJedinice) {
        for (int i = 0; i < brojZona; i++) {
            int brojZone = i + 1;
            Zone pomocni = new Zone(brojZone, brojZone * kapacitetZone, 0, brojZone * maksParkiranje * vremenskaJedinica * 1000, (brojZona + 1 - brojZone) * cijenaJedinice, 0, 0, 0);
            zone.add(pomocni);
        }
    }

    public double generirajVrijednost() {
        Random rn = new Random();
        double x = rn.nextInt(1000);
        x = x / 1000;
        return x;
    }

    //za provjeru
    public void ispisiAutomobile() {
        for (Automobili a : auti) {
            System.out.println("Id auta: " + a.getIdAutomobila());
        }
    }

    public void ispisiZone() {
        for (Zone z : zone) {
            System.out.println("idZone: " + z.getIdZone() + " kapacitet: " + z.getKapacitet() + " vr parkiranja: " + z.getVrijemeParkiranja() + " cij parkiranja: " + z.getCijenaParkiranja());
        }
    }

    public void ispisiParkiranje() {
        /*
         for (ParkiraniAutiPoZonama papz : parkiraniAuti) {
         System.out.println("Auto: " + papz.getIdAuta() + " Zona: " + papz.idZone);
         }*/
        //ElementIterator it = new ElementIterator(listaDjece);
        ParkiranjaIterator pi = new ParkiranjaIterator(parkiraniAuti);
        for (IteratorPodaci iter = pi.getIteratorPodaci(); iter.hasNext();) {
            ParkiraniAutiPoZonama zapis = (ParkiraniAutiPoZonama) iter.next();

            System.out.println("ID auta : " + zapis.idAuta);
        }
    }

    public void napuniParking() {
        for (Automobili a : auti) {
            ParkiraniAutiPoZonama pomocni = new ParkiraniAutiPoZonama(a.getIdAutomobila(), 11, 0, 0, 0);
            //System.out.println("Id auta: " + a.getIdAutomobila());
            parkiraniAuti.add(pomocni);
        }
    }

    public void obrisiPrvog() {
        /*
                for (ParkiraniAutiPoZonama papz : parkiraniAuti) {

            //System.out.println("T je");
            parkiraniAuti.remove(papz);
            break;

        }*/
        ParkiranjaIterator pi = new ParkiranjaIterator(parkiraniAuti);
        for (IteratorPodaci iter = pi.getIteratorPodaci(); iter.hasNext();) {
            ParkiraniAutiPoZonama zapis = (ParkiraniAutiPoZonama) iter.next();
            if(zapis.getIdAuta()==1 || zapis.getIdAuta()==5) {
              parkiraniAuti.remove(zapis);
            }

            //System.out.println("ID auta : " + zapis.idAuta);
        }
    }

    public void dodajPrvog() {
        ParkiraniAutiPoZonama pomocni = new ParkiraniAutiPoZonama(1, 11, 0, 0, 0);
        //System.out.println("Id auta: " + a.getIdAutomobila());
        parkiraniAuti.add(pomocni);
    }

}
