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
public class ContextIspis {
    /*
    private Strategy strategy;

   public Context(Strategy strategy){
      this.strategy = strategy;
   }

   public int executeStrategy(int num1, int num2){
      return strategy.doOperation(num1, num2);
   }
    
    */
    private InterfaceStrategyIspis strategyIspis;

    public ContextIspis (InterfaceStrategyIspis strategyIspis) {
        this.strategyIspis=strategyIspis;
    }
    
    public void izvrsiIspis(Automobili auto, Zone zone, String tekst) {
        strategyIspis.ispisiNaZaslon(auto, zone, tekst);
    }
    
    
    
}
