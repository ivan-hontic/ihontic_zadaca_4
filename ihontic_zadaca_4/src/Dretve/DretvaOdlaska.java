/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dretve;

import Iterator.AutiIterator;
import Iterator.IteratorPodaci;
import Iterator.ParkiranjaIterator;
import MVC_ispis.ContextIspis;
import MVC_ispis.IspisTeksta;
import MVC_kontroler.ElementKontroler;
import static MVC_kontroler.ElementKontroler.auti;
import static MVC_kontroler.ElementKontroler.parkiraniAuti;
import MVC_podaci.Automobili;
import MVC_podaci.ParkiraniAutiPoZonama;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ivan Hontic
 */
public class DretvaOdlaska extends Thread {

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {

        double ciklus1 = (Main.MainProgram.ek.vremenskaJedinica / Main.MainProgram.ek.intervalOdlaska) * Main.MainProgram.ek.generirajVrijednost() * 1000;
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
            double ciklus = (Main.MainProgram.ek.vremenskaJedinica / Main.MainProgram.ek.intervalOdlaska) * Main.MainProgram.ek.generirajVrijednost() * 1000;

            //dohvaćanje prvog automobila iz parkiralista
            ParkiraniAutiPoZonama papz = new ParkiraniAutiPoZonama(0, 0, 0, 0);
            ParkiranjaIterator pi = new ParkiranjaIterator(parkiraniAuti);
            for (IteratorPodaci iter = pi.getIteratorPodaci(); iter.hasNext();) {
                ParkiraniAutiPoZonama zapis = (ParkiraniAutiPoZonama) iter.next();
                papz = zapis;
                break;
            }
            if (papz.idAuta == 0) {
                //ispis da nema auta za izaci
                //tekstZaIspis = "ODLAZAK - Vrijeme: " + sdf.format(pocVrijeme.getTime()) + " | Auto: NEMA | Zona: NEMA | Status: Nema auta za izaci!";
                //contextIspis.izvrsiIspis(null, null, tekstZaIspis);
            } else {
                //ima auta - slijedi biranje akcije 0,1 ili 2
                int akcija = (int) (4 * Main.MainProgram.ek.generirajVrijednost());
                if (akcija == 0) {
                    //korisnik dođe ali ništa ne uradi
                    for (IteratorPodaci iter = pi.getIteratorPodaci(); iter.hasNext();) {
                        ParkiraniAutiPoZonama zapis = (ParkiraniAutiPoZonama) iter.next();
                        if (zapis.idAuta == papz.idAuta) {
                            parkiraniAuti.remove(zapis);
                            parkiraniAuti.add(papz);
                            break;
                        }
                    }

                    tekstZaIspis = "-ODLAZAK - Vrijeme: " + sdf.format(pocVrijeme.getTime()) + " | Auto: " + papz.idAuta + " | Zona: " + papz.idZone + " | Status: Korisnikova akcija: ništa!";
                    contextIspis.izvrsiIspis(auti, ElementKontroler.zone, tekstZaIspis);
                } else if (akcija == 1 || akcija == 2) {
                    //auto odlazi sa parkinga
                    for (IteratorPodaci iter = pi.getIteratorPodaci(); iter.hasNext();) {
                        ParkiraniAutiPoZonama zapis = (ParkiraniAutiPoZonama) iter.next();
                        if (zapis.idAuta == papz.idAuta) {
                            parkiraniAuti.remove(zapis);
                            break;
                        }
                    }
                    Main.MainProgram.ek.zone.get(papz.idZone - 1).brojZauzetih--;
                    AutiIterator ai = new AutiIterator(auti);
                    Automobili pomocni = new Automobili(0, 0, false);
                    for (IteratorPodaci iter = ai.getIteratorPodaci(); iter.hasNext();) {
                        Automobili zapis = (Automobili) iter.next();
                        if (zapis.getIdAutomobila() == papz.idAuta) {
                            pomocni = zapis;
                            auti.remove(zapis);
                            pomocni.setParkiran(false);
                            auti.add(pomocni);
                            break;
                        }
                    }
                    tekstZaIspis = "-ODLAZAK - Vrijeme: " + sdf.format(pocVrijeme.getTime()) + " | Auto: " + papz.idAuta + " | Zona: " + papz.idZone + " | Status: Korisnik odlazi sa parkiralista!";
                    contextIspis.izvrsiIspis(auti, Main.MainProgram.ek.zone, tekstZaIspis);

                } else {
                    //vlasnik obnavlja kartu ako zadovoljava preduvjete
                    if (papz.brojProduzenja == (papz.idZone - 1)) {
                        //auto odlazi sa parkinga jer ne može se više produljiti karta
                        for (IteratorPodaci iter = pi.getIteratorPodaci(); iter.hasNext();) {
                            ParkiraniAutiPoZonama zapis = (ParkiraniAutiPoZonama) iter.next();
                            if (zapis.idAuta == papz.idAuta) {
                                parkiraniAuti.remove(zapis);
                                break;
                            }
                        }
                        Main.MainProgram.ek.zone.get(papz.idZone - 1).brojZauzetih--;
                        AutiIterator ai = new AutiIterator(auti);
                        Automobili pomocni = new Automobili(0, 0, false);
                        for (IteratorPodaci iter = ai.getIteratorPodaci(); iter.hasNext();) {
                            Automobili zapis = (Automobili) iter.next();
                            if (zapis.getIdAutomobila() == papz.idAuta) {
                                pomocni = zapis;
                                auti.remove(zapis);
                                pomocni.setParkiran(false);
                                auti.add(pomocni);
                                break;
                            }
                        }
                        tekstZaIspis = "-ODLAZAK - Vrijeme: " + sdf.format(pocVrijeme.getTime()) + " | Auto: " + papz.idAuta + " | Zona: " + papz.idZone + " | Status: Produljenje karte odbijeno!";
                        contextIspis.izvrsiIspis(auti, Main.MainProgram.ek.zone, tekstZaIspis);

                    } else {
                        //produljenje karte - ako karta još vrijedi, tada se produljuje od trenutka kad istice. ako vise ne vredi, tada se produljuje od sada

                        Main.MainProgram.ek.zone.get(papz.idZone - 1).zaradaParking += Main.MainProgram.ek.zone.get(papz.idZone - 1).cijenaParkiranja;
                        for (IteratorPodaci iter = pi.getIteratorPodaci(); iter.hasNext();) {
                            ParkiraniAutiPoZonama zapis = (ParkiraniAutiPoZonama) iter.next();
                            if (papz.getIdAuta() == zapis.getIdAuta()) {
                                parkiraniAuti.remove(zapis);
                                break;
                            }
                        }
                        //Automobili autoKojiUlazi = new Automobili(0, 0, true);
                        AutiIterator ai = new AutiIterator(auti);
                        for (IteratorPodaci iter = ai.getIteratorPodaci(); iter.hasNext();) {
                            Automobili zapis = (Automobili) iter.next();
                            if (zapis.getIdAutomobila() == papz.getIdAuta()) {
                                zapis.brojParkiranja++;
                                break;
                            }
                        }

                        papz.brojProduzenja++;
                        if (papz.vrijemeDoKadJeParkiran > miliPoc) {
                            //karta jos vrijedi
                            papz.vrijemeDoKadJeParkiran += Main.MainProgram.ek.zone.get(papz.idZone - 1).vrijemeParkiranja;
                        } else {
                            //karta vise ne vrijedi
                            papz.vrijemeDoKadJeParkiran = miliPoc + Main.MainProgram.ek.zone.get(papz.idZone - 1).vrijemeParkiranja;
                        }
                        parkiraniAuti.add(papz);

                        tekstZaIspis = "-ODLAZAK - Vrijeme: " + sdf.format(pocVrijeme.getTime()) + " | Auto: " + papz.idAuta + " | Zona: " + papz.idZone + " | Iznos: " + Main.MainProgram.ek.zone.get(papz.idZone - 1).cijenaParkiranja + " | Status: Parking produljen!";
                        contextIspis.izvrsiIspis(auti, Main.MainProgram.ek.zone, tekstZaIspis);

                    }

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
