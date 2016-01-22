/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC_ispis;

import Iterator.AutiIterator;
import Iterator.IteratorPodaci;
import static MVC_kontroler.ElementKontroler.auti;
import MVC_podaci.Automobili;
import MVC_podaci.Zone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan Hontic
 */
public class IspisPetAutomobila implements InterfaceStrategyIspis {

    //ispis 5 automobila s najvećim brojem parkiranja
    @Override
    public void ispisiNaZaslon(List<Automobili> auto, List<Zone> zone, String tekst) {

        List<Automobili> pomocni = new ArrayList<>();
        Automobili pomocni2 = new Automobili(0, 0, true);
        
        
        AutiIterator ai = new AutiIterator(auto);
        for (IteratorPodaci iter = ai.getIteratorPodaci(); iter.hasNext();) {
            Automobili zapis = (Automobili) iter.next();
            pomocni.add(zapis);
        }

        System.out.println("-----------------Ispis 5 automobila s najviše parkiranja-----------------------");
        for (int i = 0; i < 5; i++) {
            pomocni2 = new Automobili(0, 0, true);
            ai = new AutiIterator(pomocni);
            for (IteratorPodaci iter = ai.getIteratorPodaci(); iter.hasNext();) {
                Automobili zapis = (Automobili) iter.next();
                if (pomocni2.getIdAutomobila() == 0) {
                    pomocni2 = zapis;
                }
                if (pomocni2.brojParkiranja < zapis.brojParkiranja) {
                    pomocni2 = zapis;
                }
            }
            pomocni.remove(pomocni2);
            System.out.println("ID auta: " + pomocni2.getIdAutomobila()+ " Broj parkiranja: "+pomocni2.getBrojParkiranja());
        }
        System.out.println("--------------------------------------------------------------------------");

    }

}
