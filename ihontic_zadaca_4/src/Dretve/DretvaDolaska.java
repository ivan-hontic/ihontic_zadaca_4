/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dretve;

import Iterator.AutiIterator;
import Iterator.IteratorPodaci;
import MVC_kontroler.ElementKonroler;
import static MVC_kontroler.ElementKonroler.auti;
import MVC_podaci.Automobili;
import MVC_podaci.ParkiraniAutiPoZonama;
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

        System.out.println("Dretva Dolaska");
        Main.MainProgram.ek.ispisiZone();

        while (Main.MainProgram.ek.pokrenutiDolasci) {
            //pocetno vrijeme obrade dretve za točno određivanje ciklusa
            Date pocVrijeme = new Date();
            long miliPoc = pocVrijeme.getTime();
            long miliUK = 0;

            //određivanje ciklusa dretve prema formuli
            double ciklus = (Main.MainProgram.ek.vremenskaJedinica / Main.MainProgram.ek.intervalDolaska) * Main.MainProgram.ek.generirajVrijednost() * 1000;

            Automobili autoKojiUlazi = new Automobili(0, 0, true);
            AutiIterator ai = new AutiIterator(auti);
            for (IteratorPodaci iter = ai.getIteratorPodaci(); iter.hasNext();) {
                Automobili zapis = (Automobili) iter.next();
                if (!zapis.isParkiran()) {
                    zapis.setParkiran(true);
                    zapis.brojParkiranja++;
                    autoKojiUlazi = zapis;
                    //System.out.println("ID auta iter : " + zapis.getIdAutomobila());
                    //System.out.println("ID auta iter : " + autoKojiUlazi.getIdAutomobila());
                    break;
                }
            }
            if (autoKojiUlazi.getIdAutomobila() == 0) {
                //ispis da nema auta za uci
                System.out.println("Nema auta za uci!");
            } else {
                //odabir zone
                int zona = (int) ((Main.MainProgram.ek.brojZona * Main.MainProgram.ek.generirajVrijednost()));
                if (ElementKonroler.zone.get(zona).getKapacitet() == ElementKonroler.zone.get(zona).getBrojZauzetih()) {
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
                    System.out.println("Nema mjesta više u zoni!");
                } else {
                    //ima mjesta
                    //((brojZona + 1 - i) * cijenaJedinice)
                    int cijena = (Main.MainProgram.ek.brojZona - zona) * Main.MainProgram.ek.cijenaJedinice;
                    System.out.println("Cijena jedinice: " + cijena);
                    System.out.println("Vrijem: " + pocVrijeme);
                    System.out.println("Mili poc: " + miliPoc);
                    Main.MainProgram.ek.zone.get(zona).brojZauzetih++;
                    Main.MainProgram.ek.zone.get(zona).brParkiranih++;
                    Main.MainProgram.ek.zone.get(zona).zaradaParking += Main.MainProgram.ek.zone.get(zona).cijenaParkiranja;

                    ParkiraniAutiPoZonama pom = new ParkiraniAutiPoZonama(autoKojiUlazi.getIdAutomobila(), zona, 0, miliUK + Main.MainProgram.ek.zone.get(zona).getVrijemeParkiranja());
                    Main.MainProgram.ek.parkiraniAuti.add(pom);
                    System.out.println("_______________");
                    
                    Main.MainProgram.ek.ispisiParkiranje();
                    System.out.println("_______________");

                }

            }

            System.out.println("Ciklus dretve: " + ciklus);

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
