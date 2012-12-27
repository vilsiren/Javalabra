
package hirvioluola.gui;


import hirvioluola.peli.Taistelu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Nappaimistonkuuntelija implements KeyListener{
    
    private Taistelu taistelu;

    public Nappaimistonkuuntelija(Taistelu taistelu) {
        this.taistelu = taistelu;
    }    

    @Override
    public void keyTyped(KeyEvent e) {     
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_A) {
//            taistelu.setKomento("a");
//        } else if (e.getKeyCode() == KeyEvent.VK_D) {
//            taistelu.setKomento("d");
//        } else if (e.getKeyCode() == KeyEvent.VK_W) {
//            taistelu.setKomento("w");
//        } else if (e.getKeyCode() == KeyEvent.VK_X) {
//            taistelu.setKomento("x");
//        }
        String komento = "" + e.getKeyChar();
        if(taistelu.suunnat.contains(komento)){
            taistelu.setKomento("" + e.getKeyChar());
            taistelu.kierros();
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    
}
