/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC_kontroler;

import MVC_podaci.Automobili;
import MVC_podaci.Zone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan Hontic
 */
public class ElementKonroler {
    
    public static List<Automobili> auti=new ArrayList<>();
    public static List<Zone> zone=new ArrayList<>();
    
    public void pokreni(String[] args){
        int brojAutomobila = Integer.parseInt(args[0]);
        kreirajAutomobile(brojAutomobila);
        
        int brojZona=Integer.parseInt(args[1]);
        int kapacitetZone =Integer.parseInt(args[2]);
        int maksParkiranje = Integer.parseInt(args[3]);
        int vremenskaJedinica = Integer.parseInt(args[4]);
        int cijenaJedinice = Integer.parseInt(args[7]);
        kreirajZone(brojZona, kapacitetZone, maksParkiranje, vremenskaJedinica, cijenaJedinice);
        
        
        ispisiAutomobile();
        ispisiZone();
        
        //System.out.println("EK");
    }
    
    public void kreirajAutomobile(int brojAuta) {
        for (int i=0; i<brojAuta; i++){
            Automobili pomocni = new Automobili(i+1, 0, false);
            auti.add(pomocni);
        }
    }
    public void kreirajZone(int brojZona,int kapacitetZone, int maksParkiranje,int vremenskaJedinica, int cijenaJedinice){
        for (int i=0; i<brojZona; i++){
            int brojZone=i+1;
            Zone pomocni = new Zone(brojZone, brojZone*kapacitetZone, 0, brojZone*maksParkiranje*vremenskaJedinica*1000, (brojZona+1-brojZone)*cijenaJedinice, 0, 0, 0);
            zone.add(pomocni);
        }
        
    }
    
    
    //za provjeru
    public void ispisiAutomobile(){
        for (Automobili a : auti){
            System.out.println("Id auta: "+a.getIdAutomobila());
        }
    }
    public void ispisiZone(){
        for (Zone z : zone){
            System.out.println("idZone: "+z.getIdZone()+" kapacitet: "+z.getKapacitet()+" vr parkiranja: " +z.getVrijemeParkiranja()+" cij parkiranja: "+z.getCijenaParkiranja());
        }
    }
}
