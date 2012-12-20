
package hirvioluola.gui;


import hirvioluola.peli.Taistelu;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Nappaimistonkuuntelija implements KeyListener{
    
    private Taistelu taistelu;

    public Nappaimistonkuuntelija(Taistelu taistelu) {
        this.taistelu = taistelu;
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            taistelu.setKomento('a');
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            taistelu.setKomento('o');
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            taistelu.setKomento('w');
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            taistelu.setKomento('s');
        }


    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
