/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import MVC_kontroler.ElementKonroler;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Ivan Hontic
 */
public class MainProgram {

    public static ElementKonroler ek;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //provjera argumenata
        if (args.length != 10) {
            System.out.println("Pogrešan broj parametara");
            return;
        }
        int pomocnaVarijabla;
        //1. argument
        pomocnaVarijabla = parseInt(args[0]);
        if (pomocnaVarijabla < 10 || pomocnaVarijabla > 100) {
            System.out.println("Krivo uneseni 1. argument(10-100)");
            return;
        }
        
        //2. argument
        pomocnaVarijabla = parseInt(args[1]);
        if (pomocnaVarijabla < 1 || pomocnaVarijabla > 4) {
            System.out.println("Krivo uneseni 2. argument(1-4)");
            return;
        }
        
        //3. argument
        pomocnaVarijabla = parseInt(args[2]);
        if (pomocnaVarijabla < 1 || pomocnaVarijabla > 100) {
            System.out.println("Krivo uneseni 3. argument(1-100)");
            return;
        }
        
        //4. argument
        pomocnaVarijabla = parseInt(args[3]);
        if (pomocnaVarijabla < 1 || pomocnaVarijabla > 10) {
            System.out.println("Krivo uneseni 4. argument(1-10)");
            return;
        }
        
        //5. argument
        pomocnaVarijabla = parseInt(args[4]);
        if (pomocnaVarijabla < 1 || pomocnaVarijabla > 10) {
            System.out.println("Krivo uneseni 5. argument(1-10)");
            return;
        }
        
        //6. argument
        pomocnaVarijabla = parseInt(args[5]);
        if (pomocnaVarijabla < 1 || pomocnaVarijabla > 10) {
            System.out.println("Krivo uneseni 6. argument(1-10)");
            return;
        }
        
        //7. argument
        pomocnaVarijabla = parseInt(args[6]);
        if (pomocnaVarijabla < 1 || pomocnaVarijabla > 10) {
            System.out.println("Krivo uneseni 7. argument(1-10)");
            return;
        }
        
        //8. argument
        pomocnaVarijabla = parseInt(args[7]);
        if (pomocnaVarijabla < 1 || pomocnaVarijabla > 10) {
            System.out.println("Krivo uneseni 8. argument(1-10)");
            return;
        }
        
        //9. argument
        pomocnaVarijabla = parseInt(args[8]);
        if (pomocnaVarijabla < 1 || pomocnaVarijabla > 10) {
            System.out.println("Krivo uneseni 9. argument(1-10)");
            return;
        }
        
        //10. argument
        pomocnaVarijabla = parseInt(args[9]);
        if (pomocnaVarijabla < 10 || pomocnaVarijabla > 100) {
            System.out.println("Krivo uneseni 10. argument(10-100)");
            return;
        }
        
        ek  = new ElementKonroler();
        ek.pokreni(args);

    }

}
