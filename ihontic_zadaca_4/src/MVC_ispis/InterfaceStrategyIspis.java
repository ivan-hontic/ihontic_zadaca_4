/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC_ispis;

import MVC_podaci.Automobili;
import MVC_podaci.Zone;

/**
 *
 * @author Ivan Hontic
 */
public interface InterfaceStrategyIspis {
    
    public void ispisiNaZaslon(Automobili auto, Zone zone, String tekst);
    
}
