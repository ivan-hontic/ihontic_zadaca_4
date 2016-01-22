/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dretve;

import Iterator.IteratorPodaci;
import Iterator.ParkiranjaIterator;
import MVC_ispis.ContextIspis;
import MVC_ispis.IspisTeksta;
import MVC_kontroler.ElementKontroler;
import static MVC_kontroler.ElementKontroler.auti;
import static MVC_kontroler.ElementKontroler.parkiraniAuti;
import MVC_podaci.ParkiraniAutiPoZonama;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ivan Hontic
 */
public class DretvaKontrole extends Thread {

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {

        double ciklus1 = (Main.MainProgram.ek.vremenskaJedinica / Main.MainProgram.ek.intervalKontrole) * 1000;
        //kako nebi prvi auto odmah izašao, nego tek nakon što prođe ciklus1
        try {
            sleep((long) ciklus1);
        } catch (InterruptedException ex) {

        }

        ContextIspis contextIspis = new ContextIspis(new IspisTeksta());
        String tekstZaIspis = "";

        while (true) {
            //pocetno vrijeme obrade dretve za točno određivanje ciklusa
            Date pocVrijeme = new Date();
            long miliPoc = pocVrijeme.getTime();
            long miliUK = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

            //određivanje ciklusa dretve prema formuli
            double ciklus = (Main.MainProgram.ek.vremenskaJedinica / Main.MainProgram.ek.intervalKontrole) * 1000;

            //prolaz kroz parkiraliste i ispis kazni
            ParkiraniAutiPoZonama papz = new ParkiraniAutiPoZonama(0, 0, 0, 0);
            ParkiranjaIterator pi = new ParkiranjaIterator(parkiraniAuti);
            for (IteratorPodaci iter = pi.getIteratorPodaci(); iter.hasNext();) {
                ParkiraniAutiPoZonama zapis = (ParkiraniAutiPoZonama) iter.next();
                papz = zapis;
                if (papz.vrijemeDoKadJeParkiran < miliPoc) {
                    //karta ne vrijedi - deponizacija
                    parkiraniAuti.remove(papz);
                    int zona = papz.getIdZone() - 1;
                    ElementKontroler.zone.get(zona).brojZauzetih--;
                    int kaznaIznos = ((ElementKontroler.brojZona + 1 - zona) * ElementKontroler.cijenaJedinice * ElementKontroler.kaznaParkiranja);
                    ElementKontroler.zone.get(zona).zaradaKazne += kaznaIznos;

                    ElementKontroler.zone.get(zona).brPaukiranih++;

                    tekstZaIspis = "KONTROLA - Vrijeme: " + sdf.format(pocVrijeme.getTime()) + " | Auto: " + papz.idAuta + " | Zona: " + papz.idZone + " | Iznos: " + kaznaIznos + " | Status: Auto je odvezen na deponij!";
                    contextIspis.izvrsiIspis(auti, Main.MainProgram.ek.zone, tekstZaIspis);
                }

            }

            //vrijeme kraja obrade dretve za točno određivanje ciklusa
            Date krajVrijeme = new Date();
            long miliKraj = krajVrijeme.getTime();
            miliUK = miliKraj - miliPoc;

            try {
                if (ciklus > miliUK) {
                    ciklus = ciklus - miliUK;
                } else {
                    ciklus = miliUK - ciklus;
                }
                sleep((long) ciklus);
            } catch (InterruptedException ex) {
                Main.MainProgram.ek.pokrenutiDolasci = false; //close thread
            }

        }
    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

}
