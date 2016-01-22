/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC_kontroler;

import Dretve.DretvaDolaska;
import Dretve.DretvaKontrole;
import Dretve.DretvaOdlaska;
import Iterator.AutiIterator;
import Iterator.IteratorPodaci;
import Iterator.ParkiranjaIterator;
import Iterator.ZoneIterator;
import MVC_ispis.ContextIspis;
import MVC_ispis.IspisAutlaKojiNisuMogliParkirati;
import MVC_ispis.IspisDeponiranihAuta;
import MVC_ispis.IspisPetAutomobila;
import MVC_ispis.IspisStanjaParkirnihMjesta;
import MVC_ispis.IspisTeksta;
import MVC_ispis.IspisZaradaKazne;
import MVC_ispis.IspisZaradaParkiranje;
import MVC_podaci.Automobili;
import MVC_podaci.ParkiraniAutiPoZonama;
import MVC_podaci.Zone;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Ivan Hontic
 */
public class ElementKontroler {

    public static List<Automobili> auti = new ArrayList<>();
    public static List<Zone> zone = new ArrayList<>();
    public static List<ParkiraniAutiPoZonama> parkiraniAuti = new ArrayList<>();

    //dretve
    private DretvaDolaska dretvaDolaska = new DretvaDolaska();
    private DretvaOdlaska dretvaOdlaska = new DretvaOdlaska();
    private DretvaKontrole dretvaKontrole = new DretvaKontrole();
    public static boolean pokrenutiDolasci = true;

    public static int vremenskaJedinica;
    public static int intervalDolaska;
    public static int intervalOdlaska;
    public static int brojZona;
    public static int cijenaJedinice;
    public static int intervalKontrole;
    public static int kaznaParkiranja;

    public void pokreni(String[] args) {
        int unosojAutomobila = Integer.parseInt(args[0]);
        kreirajAutomobile(unosojAutomobila);

        brojZona = Integer.parseInt(args[1]);
        int kapacitetZone = Integer.parseInt(args[2]);
        int maksParkiranje = Integer.parseInt(args[3]);
        vremenskaJedinica = Integer.parseInt(args[4]);
        cijenaJedinice = Integer.parseInt(args[7]);
        kreirajZone(brojZona, kapacitetZone, maksParkiranje, vremenskaJedinica, cijenaJedinice);

        intervalDolaska = Integer.parseInt(args[5]);
        intervalOdlaska = Integer.parseInt(args[6]);
        intervalKontrole = Integer.parseInt(args[8]);
        kaznaParkiranja = Integer.parseInt(args[9]);

        //pokretanje dretvi
        try {
            dretvaDolaska.start();
        } catch (Exception e) {
            dretvaDolaska = new DretvaDolaska();
            dretvaDolaska.start();
        }
        try {
            dretvaOdlaska.start();
        } catch (Exception e) {
            dretvaOdlaska = new DretvaOdlaska();
            dretvaOdlaska.start();
        }
        try {
            dretvaKontrole.start();
        } catch (Exception e) {
            dretvaKontrole = new DretvaKontrole();
            dretvaKontrole.start();
        }

        String unos;
        Scanner reader = new Scanner(System.in);
        //unos komandi
        do {
            unos = reader.nextLine();

            if (unos.startsWith("1")) {

            } else if (unos.startsWith("2")) {

            } else if (unos.startsWith("3")) {
                ContextIspis contextIspis = new ContextIspis(new IspisZaradaParkiranje());
                contextIspis.izvrsiIspis(auti, zone, "x");

            } else if (unos.startsWith("4")) {
                ContextIspis contextIspis = new ContextIspis(new IspisZaradaKazne());
                contextIspis.izvrsiIspis(auti, zone, "x");

            } else if (unos.startsWith("5")) {
                ContextIspis contextIspis = new ContextIspis(new IspisAutlaKojiNisuMogliParkirati());
                contextIspis.izvrsiIspis(auti, zone, "x");

            } else if (unos.startsWith("6")) {
                ContextIspis contextIspis = new ContextIspis(new IspisDeponiranihAuta());
                contextIspis.izvrsiIspis(auti, zone, "x");

            } else if (unos.startsWith("7")) {
                ContextIspis contextIspis = new ContextIspis(new IspisPetAutomobila());
                contextIspis.izvrsiIspis(auti, zone, "x");

            } else if (unos.startsWith("8")) {
                ContextIspis contextIspis = new ContextIspis(new IspisStanjaParkirnihMjesta());
                contextIspis.izvrsiIspis(auti, zone, "x");

            } else if (unos.equalsIgnoreCase("q")) {
                //break;
                System.err.println("Tu smo");
            }

        } while (!unos.toLowerCase().equals("q"));

    }

    public void kreirajAutomobile(int unosojAuta) {
        for (int i = 0; i < unosojAuta; i++) {
            Automobili pomocni = new Automobili(i + 1, 0, false);
            auti.add(pomocni);
        }
    }

    public void kreirajZone(int unosojZona, int kapacitetZone, int maksParkiranje, int vremenskaJedinica, int cijenaJedinice) {
        for (int i = 0; i < unosojZona; i++) {
            int unosojZone = i + 1;
            Zone pomocni = new Zone(unosojZone, unosojZone * kapacitetZone, 0, 0, unosojZone * maksParkiranje * vremenskaJedinica * 1000, (unosojZona + 1 - unosojZone) * cijenaJedinice, 0, 0, 0);
            zone.add(pomocni);
        }
    }

    public double generirajVrijednost() {
        Random rn = new Random();
        double x = rn.nextInt(1000);
        x = x / 1000;
        return x;
    }

    public int izracunajCijenu(int zona) {
        //TODO izracun cijene

        return 0;
    }

    //za provjeru
    public void ispisiAutomobile() {
        AutiIterator ai = new AutiIterator(auti);
        for (IteratorPodaci iter = ai.getIteratorPodaci(); iter.hasNext();) {
            Automobili zapis = (Automobili) iter.next();

            System.out.println("ID auta iter : " + zapis.getIdAutomobila());
        }
    }

    public void ispisiZone() {

        ZoneIterator zi = new ZoneIterator(zone);
        for (IteratorPodaci iter = zi.getIteratorPodaci(); iter.hasNext();) {
            Zone zapis = (Zone) iter.next();
            System.out.println("idZone: " + zapis.getIdZone() + " kapacitet: " + zapis.getKapacitet() + " vr parkiranja: " + zapis.getVrijemeParkiranja() + " cij parkiranja: " + zapis.getCijenaParkiranja());
        }
    }

    public void ispisiParkiranje() {
        ParkiranjaIterator pi = new ParkiranjaIterator(parkiraniAuti);
        for (IteratorPodaci iter = pi.getIteratorPodaci(); iter.hasNext();) {
            ParkiraniAutiPoZonama zapis = (ParkiraniAutiPoZonama) iter.next();

            System.out.println("ID auta : " + zapis.idAuta + " zona: " + ((zapis.idZone) + 1));
        }
    }

    public void napuniParking() {
        for (Automobili a : auti) {
            ParkiraniAutiPoZonama pomocni = new ParkiraniAutiPoZonama(a.getIdAutomobila(), 11, 0, 0);
            //System.out.println("Id auta: " + a.getIdAutomobila());
            parkiraniAuti.add(pomocni);
        }
    }

    public void ounosisiPrvog() {
        ParkiranjaIterator pi = new ParkiranjaIterator(parkiraniAuti);
        for (IteratorPodaci iter = pi.getIteratorPodaci(); iter.hasNext();) {
            ParkiraniAutiPoZonama zapis = (ParkiraniAutiPoZonama) iter.next();
            if (zapis.getIdAuta() == 1 || zapis.getIdAuta() == 5) {
                parkiraniAuti.remove(zapis);
            }
        }
    }

    public void dodajPrvog() {
        ParkiraniAutiPoZonama pomocni = new ParkiraniAutiPoZonama(1, 11, 0, 0);
        parkiraniAuti.add(pomocni);
    }

}