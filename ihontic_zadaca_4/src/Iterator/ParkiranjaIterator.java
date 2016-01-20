/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iterator;

import MVC_podaci.ParkiraniAutiPoZonama;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan Hontic
 */
public class ParkiranjaIterator implements ContainerPodaci {

    private List<ParkiraniAutiPoZonama> zapisiParkiranja = new ArrayList<>();

    public ParkiranjaIterator(List<ParkiraniAutiPoZonama> zapisiParkiranjaa) {
        this.zapisiParkiranja = zapisiParkiranjaa;
    }

    @Override
    public IteratorPodaci getIteratorPodaci() {
        return new ZapisiIterator();
    }

    private class ZapisiIterator implements IteratorPodaci {

        int index;

        @Override
        public boolean hasNext() {
            if (index < zapisiParkiranja.size()) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return zapisiParkiranja.get(index++);
            }
            return null;
        }

    }

}
