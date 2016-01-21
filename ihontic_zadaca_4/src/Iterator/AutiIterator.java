/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iterator;

import MVC_podaci.Automobili;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan Hontic
 */
public class AutiIterator implements ContainerPodaci{
    
    private List<Automobili> zapisiAuta = new ArrayList<>();

    
    public AutiIterator(List<Automobili> autii) {
        this.zapisiAuta = autii;
    }
    

    @Override
    public IteratorPodaci getIteratorPodaci() {
        return new ZapisiAutomobilaIterator();
    }
    
    private class ZapisiAutomobilaIterator implements IteratorPodaci {

        int index;

        @Override
        public boolean hasNext() {
            if (index < zapisiAuta.size()) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return zapisiAuta.get(index++);
            }
            return null;
        }
        
        
    }
    
}
