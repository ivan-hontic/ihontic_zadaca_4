/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC_ispis;

import Iterator.IteratorPodaci;
import Iterator.ZoneIterator;
import MVC_podaci.Automobili;
import MVC_podaci.Zone;
import java.util.List;

/**
 *
 * @author Ivan Hontic
 */
public class IspisAutlaKojiNisuMogliParkirati implements InterfaceStrategyIspis{

    @Override
    public void ispisiNaZaslon(List<Automobili> auto, List<Zone> zone, String tekst) {
        System.out.println("-----------------Ispis auta koji nisu mogli parkirati-----------------------");
        ZoneIterator zi = new ZoneIterator(zone);
        for (IteratorPodaci iter = zi.getIteratorPodaci(); iter.hasNext();) {
            Zone zapis = (Zone) iter.next();
            System.out.println("idZone: " + zapis.getIdZone() + " Broj automobila: " + zapis.brojAutaBezMjesta );
        }
        System.out.println("--------------------------------------------------------------------------");
    }
    
}
