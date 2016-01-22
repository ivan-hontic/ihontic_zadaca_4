/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dretve;

import Iterator.AutiIterator;
import Iterator.IteratorPodaci;
import MVC_ispis.ContextIspis;
import MVC_ispis.IspisTeksta;
import MVC_kontroler.ElementKontroler;
import static MVC_kontroler.ElementKontroler.auti;
import MVC_podaci.Automobili;
import MVC_podaci.ParkiraniAutiPoZonama;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ivan Hontic
 */
public class DretvaDolaska extends Thread {

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {

        ContextIspis contextIspis = new ContextIspis(new IspisTeksta());
        String tekstZaIspis = "";

        while (Main.MainProgram.ek.pokrenutiDolasci) {
            //pocetno vrijeme obrade dretve za točno određivanje ciklusa
            Date pocVrijeme = new Date();
            long miliPoc = pocVrijeme.getTime();
            long miliUK = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

            //određivanje ciklusa dretve prema formuli
            double ciklus = (Main.MainProgram.ek.vremenskaJedinica / Main.MainProgram.ek.intervalDolaska) * Main.MainProgram.ek.generirajVrijednost() * 1000;

            //dohvaćanje prvog automobila iz skupa automobila za parkiranje
            Automobili autoKojiUlazi = new Automobili(0, 0, true);
            AutiIterator ai = new AutiIterator(auti);
            for (IteratorPodaci iter = ai.getIteratorPodaci(); iter.hasNext();) {
                Automobili zapis = (Automobili) iter.next();
                if (!zapis.isParkiran()) {
                    zapis.setParkiran(true);
                    zapis.brojParkiranja++;
                    autoKojiUlazi = zapis;
                    break;
                }
            }
            if (autoKojiUlazi.getIdAutomobila() == 0) {
                //ispis da nema auta za uci
                //tekstZaIspis = "DOLAZAK - Vrijeme: " + sdf.format(pocVrijeme.getTime()) + " | Auto: " + autoKojiUlazi.getIdAutomobila() + " | Zona: NEMA | Iznos: 0 | Status: Nema auta za uci!";
                //contextIspis.izvrsiIspis(null, null, tekstZaIspis);
            } else {
                //odabir zone
                int zona = (int) ((Main.MainProgram.ek.brojZona * Main.MainProgram.ek.generirajVrijednost()));
                if(zona==Main.MainProgram.ek.brojZona) {
                    zona--;
                }
                if (ElementKontroler.zone.get(zona).getKapacitet() == ElementKontroler.zone.get(zona).getBrojZauzetih()) {
                    //nema mjesta u zoni
                    for (IteratorPodaci iter = ai.getIteratorPodaci(); iter.hasNext();) {
                        Automobili zapis = (Automobili) iter.next();
                        if (zapis.getIdAutomobila() == autoKojiUlazi.getIdAutomobila()) {
                            auti.remove(zapis);
                            autoKojiUlazi.setParkiran(false);
                            autoKojiUlazi.brojParkiranja--;
                            auti.add(autoKojiUlazi);
                            break;
                        }
                    }
                    Main.MainProgram.ek.zone.get(zona).brojAutaBezMjesta++;
                    tekstZaIspis = "DOLAZAK - Vrijeme: " + sdf.format(pocVrijeme.getTime()) + " | Auto: " + autoKojiUlazi.getIdAutomobila() + " | Zona: " + (zona + 1) + "| Iznos: 0 | Status: Sva parkirna mjesta u zoni su zauzeta!";
                    contextIspis.izvrsiIspis(auti, Main.MainProgram.ek.zone, tekstZaIspis);
                } else {
                    //ima mjesta u zoni
                    //int cijena = (Main.MainProgram.ek.brojZona - zona) * Main.MainProgram.ek.cijenaJedinice;

                    Main.MainProgram.ek.zone.get(zona).brojZauzetih++;
                    //Main.MainProgram.ek.zone.get(zona).brPaukiranih++;
                    Main.MainProgram.ek.zone.get(zona).zaradaParking += Main.MainProgram.ek.zone.get(zona).cijenaParkiranja;

                    ParkiraniAutiPoZonama pom = new ParkiraniAutiPoZonama(autoKojiUlazi.getIdAutomobila(), (zona+1), 0, miliPoc + Main.MainProgram.ek.zone.get(zona).getVrijemeParkiranja());
                    Main.MainProgram.ek.parkiraniAuti.add(pom);

                    tekstZaIspis = "DOLAZAK - Vrijeme: " + sdf.format(pocVrijeme.getTime()) + " | Auto: " + autoKojiUlazi.getIdAutomobila() + " | Zona: " + (zona + 1) + "| Iznos:" + Main.MainProgram.ek.zone.get(zona).cijenaParkiranja + " | Status: Auto je parkiran!";
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
