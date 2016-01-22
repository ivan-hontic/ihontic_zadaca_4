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
        int brojAutomobila = Integer.parseInt(args[0]);
        kreirajAutomobile(brojAutomobila);

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
                if (pokrenutiDolasci) {
                    pokrenutiDolasci = false;
                    try {
                        dretvaDolaska.interrupt();
                    } catch (Exception e) {
                    }
                    ContextIspis contextIspis = new ContextIspis(new IspisTeksta());
                    contextIspis.izvrsiIspis(auti, zone, "Parkiralište je sada zatvoreno!");
                } else {
                    ContextIspis contextIspis = new ContextIspis(new IspisTeksta());
                    contextIspis.izvrsiIspis(auti, zone, "Parkiralište je već zatvoreno!");
                }

            } else if (unos.startsWith("2")) {
                if (pokrenutiDolasci) {
                    ContextIspis contextIspis = new ContextIspis(new IspisTeksta());
                    contextIspis.izvrsiIspis(auti, zone, "Parkiralište je već otvoreno!");
                } else {
                    pokrenutiDolasci = true;
                    try {
                        dretvaDolaska.start();
                    } catch (Exception e) {
                        dretvaDolaska = new DretvaDolaska();
                        dretvaDolaska.start();
                    }
                    ContextIspis contextIspis = new ContextIspis(new IspisTeksta());
                    contextIspis.izvrsiIspis(auti, zone, "Parkiralište je sada otvoreno!");
                }

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

            } else if (unos.equals("Q")) {
                System.exit(0);

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

}
