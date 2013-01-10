
package hirvioluola.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Nappaimistonkuuntelija implements KeyListener {
    
    private GraafinenKayttis kayttis;
    
    public Nappaimistonkuuntelija(GraafinenKayttis kayttis){
        this.kayttis = kayttis;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        if(e.getKeyCode() == KeyEvent.VK_Q){
//            kayttis.setKomento("YLÄVASEN");
//        }
//        else if(e.getKeyCode() == KeyEvent.VK_W){
//            kayttis.setKomento("YLÖS");
//        }
//        else if(e.getKeyCode() == KeyEvent.VK_E){
//            kayttis.setKomento("YLÄOIKEA");
//        }
//        else if(e.getKeyCode() == KeyEvent.VK_D){
//            kayttis.setKomento("OIKEA");
//        }
//        else if(e.getKeyCode() == KeyEvent.VK_C){
//            kayttis.setKomento("ALAOIKEA");
//        }
//        else if(e.getKeyCode() == KeyEvent.VK_X){
//            kayttis.setKomento("ALAS");
//        }
//        else if(e.getKeyCode() == KeyEvent.VK_Z){
//            kayttis.setKomento("ALAVASEN");
//        }
//        else if(e.getKeyCode() == KeyEvent.VK_A){
//            kayttis.setKomento("VASEN");
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
}
