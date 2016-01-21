/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iterator;

import MVC_podaci.Zone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan Hontic
 */
public class ZoneIterator implements ContainerPodaci{
    
    private List<Zone> zapisiZona = new ArrayList<>();

    
    public ZoneIterator(List<Zone> zonee) {
        this.zapisiZona = zonee;
    }
    

    @Override
    public IteratorPodaci getIteratorPodaci() {
        return new ZapisiZonaIterator();
    }
    
    private class ZapisiZonaIterator implements IteratorPodaci {

        int index;

        @Override
        public boolean hasNext() {
            if (index < zapisiZona.size()) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return zapisiZona.get(index++);
            }
            return null;
        }
        
        
    }
    
}
