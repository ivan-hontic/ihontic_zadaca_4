/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dretve;

/**
 *
 * @author Ivan Hontic
 */
public class DretvaOdlaska extends Thread{
    
    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {

        System.out.println("Dretva Odlaska");

        /*
        while (true) {
            //vrijeme obrade dretve za točno određivanje ciklusa
            
            Date pocVrijeme = new Date();
            long miliPoc = pocVrijeme.getTime();
            long miliUK = 0;
            
            

            
            Date krajVrijeme = new Date();
            long miliKraj = krajVrijeme.getTime();
            miliUK = miliKraj - miliPoc;
            long ciklus = Main.MainProgram.ec.brojSekundi * 1000;
            try {
                if (ciklus > miliUK) {
                    ciklus = ciklus - miliUK;
                } else {
                    ciklus = miliUK - ciklus;
                }
        sleep(ciklus);
            } catch (InterruptedException ex) {
                Main.MainProgram.ec.dretvaPokrenuta = false; //close thread
            }
            
        }*/
    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
